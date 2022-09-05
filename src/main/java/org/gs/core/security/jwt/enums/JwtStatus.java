package com.dimata.service.general.harisma.core.security.jwt.enums;

public enum JwtStatus {
    ACTIVE, REVOKE, ERROR, EXPIRED;

    public String getCodeName() {
        return toString().toLowerCase();
    }
}
