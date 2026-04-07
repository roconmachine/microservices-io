package com.roconmachine.io.fgaccess.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RecordStatus {

    INSERTED("INSERTED"),

    DISABLED("DISABLED"),

    DELETED("DELETED");


    private String value;

    RecordStatus(String value) {
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
    public static RecordStatus fromValue(String value) {
        for (RecordStatus b : RecordStatus.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
