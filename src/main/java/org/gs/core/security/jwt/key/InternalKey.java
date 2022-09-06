package org.gs.core.security.jwt.key;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.gs.core.rest.client.PublicKeyClient;
import org.gs.exception.PublicKeyBlacklistException;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import io.quarkus.runtime.StartupEvent;

@Singleton
public class InternalKey {

    private static final Logger LOGGER = Logger.getLogger(InternalKey.class);

    @Inject
    @RestClient
    PublicKeyClient publicKeyClient;

    private final Map<String, PublicKey> publicKeyMap = new HashMap<>(); 
    private final Set<String> blackListKey = new HashSet<>();
    
    void onStart(@Observes StartupEvent ev) {
        LOGGER.info("Fetching public key from App-Account service...");
        publicKeyClient.getLatestPublicKey()
            .stream()
            .forEach(t -> {
                var publicKey = decodePublicKey(t.getPublicKey());
                if(publicKey != null) {
                    LOGGER.info("Fetch Key with id " + t.getId());
                    publicKeyMap.put(t.getId(), publicKey);
                }
            });
    }

    public PublicKey fetchNewKey(String keyId) {
        Objects.requireNonNull(keyId, "keyId can't be null");
        var newKey = publicKeyClient.getPublicKey(keyId);
        if(newKey == null) {
            blackListKey.add(keyId);
            throw new PublicKeyBlacklistException();
        }
        var publicKey = decodePublicKey(newKey.getPublicKey());
        publicKeyMap.put(keyId, publicKey);
        return publicKey;
    }

    public PublicKey getPublicKey(String id) {
        return publicKeyMap.get(id);
    }

    public boolean isKeyBlacklist(String keyId) {
        return blackListKey.contains(keyId);
    }

    public void addBlacklistKey(String keyId) {
        blackListKey.add(keyId);
    }

    private PublicKey decodePublicKey(String encodedKey) {
        try {
            var bytes = Base64.getDecoder().decode(encodedKey);
            var x059PublicKey = new X509EncodedKeySpec(bytes);
            var keyFactory = KeyFactory.getInstance("RSA");

            return keyFactory.generatePublic(x059PublicKey);
        }catch (Exception e) {
            return null;
        }
    }
    
}
