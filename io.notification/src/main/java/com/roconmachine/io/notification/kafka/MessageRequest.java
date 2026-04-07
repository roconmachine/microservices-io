package com.roconmachine.io.notification.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class MessageRequest {

    private long id;
    private String type;
    private String subjectTemplate;
    private String subjectPlaceholder;
    private String bodyTemplate;
    private String bodyPlaceholder;
    private String status;
    private String recipients;
    private String severity;
}
