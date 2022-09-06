package org.gs.core.security.jwt.handler;

import java.security.Key;

import javax.enterprise.context.ApplicationScoped;

import org.gs.core.security.jwt.payload.BodyPayload;
import org.gs.core.security.jwt.payload.HeaderPayload;
import org.gs.core.security.jwt.payload.JwsBuilder;
import org.gs.core.security.jwt.payload.JwsPayload;

import io.jsonwebtoken.Jwts;

@ApplicationScoped
public class JwtCreationHandler {
    
    public JwsPayload createAccessKey(HeaderPayload header, BodyPayload body, Key privateKey) {
        var jwsBuilder = JwsBuilder.create(privateKey)
            .setHeader(header)
            .setBody(body);

        var jwtBuilder = Jwts.builder()
            .setHeaderParam("kid", jwsBuilder.getHeader().getKeyId())
            .setClaims(jwsBuilder.getBody().buildClaims())
            .signWith(jwsBuilder.getPrivateKey());
        
        return jwsBuilder.buildPayload(jwtBuilder.compact());
    }

}
