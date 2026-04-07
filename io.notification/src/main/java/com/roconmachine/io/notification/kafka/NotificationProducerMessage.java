package com.roconmachine.io.notification.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationProducerMessage {
    public Long id;
    private String template_name;
    private String subject_placeholder;
    private String body_placeholder;
    private String status;
    private String recipients;
    private String severity;


}
