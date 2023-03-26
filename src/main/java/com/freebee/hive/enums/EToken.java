package com.freebee.hive.enums;

public enum EToken {
    ACCESS_DURATION("3"),
    REFRESH_DURATION("6"),
    VERIFY_DURATION("3"),
    ACCESS_SECRET("kT2MJt-7NxPM6b-7biRysPw-FcEQHSKf-7Ywae3CU");

    private final String value;

    EToken(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
