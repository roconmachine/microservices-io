package com.roconmachine.io.fgaccess.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ResourceType {

        FILE("FILE"),
        DATABASE("DATABASE"),
        API("API"),
        SERVICE("SERVICE"),
        CUSTOM("CUSTOM");

        private String value;

        ResourceType(String value) {
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
        public static ResourceType fromValue(String value) {
                for (ResourceType b : ResourceType.values()) {
                        if (b.value.equals(value)) {
                                return b;
                        }
                }
                throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }

}
