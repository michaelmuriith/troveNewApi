package com.devkenya.user.model;

import lombok.Getter;

@Getter
public enum LoginType {
    EMAIL(1),
    GOOGLE(2),
    PHONE(3),
    FACEBOOK(4);

    private final int value;

    LoginType(int value) {
        this.value = value;
    }

    public static LoginType fromValue(int value) {
        for (LoginType type : LoginType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid LoginType value: " + value);
    }

    public static LoginType fromValue(String value) {
        for (LoginType type : LoginType.values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid LoginType value: " + value);
    }
}
