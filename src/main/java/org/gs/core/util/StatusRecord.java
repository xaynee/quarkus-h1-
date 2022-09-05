package com.dimata.service.general.harisma.core.util;

import lombok.Getter;

public enum StatusRecord {
    ACTIVE("active"),
    ERROR("error");

    @Getter
    private final String code;

    StatusRecord(String code) {
        this.code = code;
    }
}
