package com.dimata.service.general.harisma.core.security.mechanism;

import com.dimata.service.general.harisma.core.security.jwt.enums.HeaderType;
import com.dimata.service.general.harisma.core.security.jwt.payload.BodyPayload;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.math.NumberUtils;

import java.security.Principal;
import java.util.Objects;

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class DimataUserPrincipal implements Principal {

    private final HeaderType headerType;
    private final Long userId;
    private final Long appId;
    private final Claims claims;

    public DimataUserPrincipal(Claims claims) {
        Objects.requireNonNull(claims, "claims is required");
        this.claims = claims;
        this.appId = claims.get(BodyPayload.APP_ID, Long.class);
        
        var tempUserId = claims.get(BodyPayload.USER_ID, String.class);
        if(tempUserId == null || !NumberUtils.isParsable(tempUserId)) {
            headerType = HeaderType.SERVICE;
            userId = null;
        }else {
            headerType = HeaderType.INTERNAL;
            this.userId = Long.valueOf(tempUserId);
        }
    }

    @Override
    public String getName() {
        return headerType.toString();
    }
    
}
