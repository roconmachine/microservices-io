package com.roconmachine.io.fgaccess.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SubjectTypeEnum {
    USER("USER"),

    GROUP("GROUP"),

    ROLE("ROLE");

    private String value;

    SubjectTypeEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static SubjectTypeEnum fromValue(String value) {
        for (SubjectTypeEnum b : SubjectTypeEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
