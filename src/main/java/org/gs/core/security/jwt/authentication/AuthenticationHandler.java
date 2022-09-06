package org.gs.core.security.jwt.authentication;

import org.gs.core.security.jwt.key.InternalKey;
import org.gs.core.security.jwt.key.ServiceKey;
import org.gs.core.security.jwt.tool.JwtTools;
import org.gs.core.security.mechanism.DimataIdentity;
import org.gs.core.util.CheckUtil;
import org.gs.exception.ExceptionCode;
import org.gs.exception.PublicKeyException;
import org.gs.exception.UnauthorizedAccessException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import io.quarkus.security.identity.SecurityIdentity;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.security.Key;

@ApplicationScoped
public class AuthenticationHandler {
    
    @Inject
    ServiceKey serviceKey;
    @Inject
    InternalKey internalKey;

    @ConfigProperty(name = "dimata.jwt.aud")
    String audience;
    @ConfigProperty(name = "dimata.jwt.iss")
    String issuer;

    public SecurityIdentity serviceAuth(String token) {
        var builder = Jwts.parserBuilder()
            .setSigningKey(serviceKey.getKey())
            .build();
        var claims = validateJwt(builder, token);
        return DimataIdentity.loginAsService(claims);
    }

    public SecurityIdentity internalAuth(String token) {
        var claims = internalValidateAndGetClaims(token, true);
        return DimataIdentity.loginAsUser(claims);
    }

    public SecurityIdentity internalAuthSecondAttemp(String token) {
        var claims = internalValidateAndGetClaims(token, false);
        return DimataIdentity.loginAsUser(claims);
    }

    //-----------------------------

    private Claims internalValidateAndGetClaims(String token, boolean firstAttemp) {
        var builder = Jwts.parserBuilder()
            .setSigningKeyResolver(new SignInResolver(internalKey, firstAttemp))
            .build();
        return validateJwt(builder, token);
    }

    private Claims validateJwt(JwtParser parser, String token) {
        var extractToken = JwtTools.unWrapBearerToken(token);
        try {
            var claims = parser
                .parseClaimsJws(extractToken)
                .getBody();
            validateClaims(claims);
            return claims;
        }catch (ExpiredJwtException exp) {
            throw new UnauthorizedAccessException(ExceptionCode.JWT_IS_EXPIRED);
        }catch (SignatureException sig) {
            throw new UnauthorizedAccessException(ExceptionCode.JWT_SIG_NOT_VALID);
        }
    }

    private void validateClaims(Claims claims) {
        if(claims.getIssuer() == null || !claims.getIssuer().equals(issuer)) {
            throw new UnauthorizedAccessException(ExceptionCode.JWT_UNAUTHENTICATION, "issuer");
        }
        if(claims.getAudience() == null || !claims.getAudience().contains(audience)) {
            throw new UnauthorizedAccessException(ExceptionCode.JWT_UNAUTHENTICATION, "audience");
        }
    }

    //------------------ SIGNIN RESOLVER

    private static class SignInResolver extends SigningKeyResolverAdapter {

        private final InternalKey key;
        private final boolean firstAttemp;

        private SignInResolver(InternalKey key, boolean firstAttemp) {
            this.key = key;
            this.firstAttemp = firstAttemp;
        }

        @Override
        public Key resolveSigningKey(JwsHeader header, Claims claims) {
            var keyId = header.getKeyId();
            if(CheckUtil.isStringBlank(keyId)) {
                throw new UnauthorizedAccessException(ExceptionCode.JWT_KEY_ID_NOT_FOUND);
            }
            if(firstAttemp) {
                var publicKey = key.getPublicKey(keyId);
                if(publicKey == null) {
                    throw new PublicKeyException();
                }
                return publicKey;
            }else {
                return key.fetchNewKey(keyId);
            }
        }
    }
}
