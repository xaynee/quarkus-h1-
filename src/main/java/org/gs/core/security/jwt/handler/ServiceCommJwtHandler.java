package com.dimata.service.general.harisma.core.security.jwt.handler;

import com.dimata.service.general.harisma.core.security.jwt.enums.JwtType;
import com.dimata.service.general.harisma.core.security.jwt.key.ServiceKey;
import com.dimata.service.general.harisma.core.security.jwt.payload.BodyPayload;
import com.dimata.service.general.harisma.core.security.jwt.payload.HeaderPayload;
import com.dimata.service.general.harisma.core.security.jwt.payload.JwsPayload;
import com.dimata.service.general.harisma.core.security.jwt.tool.JwtExpiredTime;
import io.jsonwebtoken.Claims;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

@ApplicationScoped
public class ServiceCommJwtHandler {

    public static final String ROLE = "maintainer";
    
    @Inject
    ServiceKey serviceKey;
    @Inject
    JwtCreationHandler jwtCreationHDL;

    @ConfigProperty(name = "dimata.jwt.iss")
    String issuer;
    @ConfigProperty(name = "dimata.jwt.service-key.exp")
    String expTokenFormat;
    @ConfigProperty(name = "dimata.service.name")
    String subject;

    public JwsPayload builderAccessToken(String audience, Claims customClaims) {
        var header = HeaderPayload.fromKid(serviceKey.getVersion());
        var body = buildBodyPayload(audience, customClaims);
        return jwtCreationHDL.createAccessKey(header, body, serviceKey.getKey());
    }

    private BodyPayload buildBodyPayload(String audience, Claims claims) {
        var bodyPayload = new BodyPayload(issuer, subject, UUID.randomUUID().toString(), JwtType.ACCESS);
        var expiredTime = JwtExpiredTime.fromFormat(expTokenFormat);
        bodyPayload = bodyPayload.setExpiredClaim(expiredTime.getPeriod(), expiredTime.getUnit());
        if(claims != null && !claims.isEmpty()) {
            bodyPayload.setCustomClaims(claims);
        }
        bodyPayload.addAudience(audience);
        bodyPayload.addRole(ROLE);
        return bodyPayload;
    }
}
