package com.roconmachine.io.identity_service.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ResponseMessagesConfig {
    public static enum ResponseStatus {
        USER_CREATED("0000", "User saved successfull"),
        DUPLICATE_USER("9001", "Duplicate user name."),
        VALID_TOKEN("1001", "Its a valid token"),
        NOT_VALID_USER("9002", "Not a valid user.");

        private final String code;
        private final String message;

        ResponseStatus(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
}
