package com.roconmachine.io.fgaccess.domains;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PolicyMappingDto {

    private Long id;
    private Long policy_id;
    private Long subject_id;
    private Long resource_id;
    private SubjectTypeEnum subject_type;
    private ActionEnum action;
    private RecordStatus recordStatus;


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

    public enum ActionEnum {
        READ("READ"),

        WRITE("WRITE"),

        DELETE("DELETE"),

        EXECUTE("EXECUTE"),

        SHARE("SHARE");

        private String value;

        ActionEnum(String value) {
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
        public static ActionEnum fromValue(String value) {
            for (ActionEnum b : ActionEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }

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

}
