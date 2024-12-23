package com.roconmachine.io.notification.processor.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
public class MessageRequest {

    @JsonProperty("id")
    private long id;

    @JsonProperty("type")
    private String type;
    @JsonProperty("subjectTemplate")
    private String subjectTemplate;

    @JsonProperty("subjectPlaceholder")
    private String subjectPlaceholder;

    @JsonProperty("bodyTemplate")
    private String bodyTemplate;

    @JsonProperty("bodyPlaceholder")
    private String bodyPlaceholder;

    @JsonProperty("status")
    private String status;

    @JsonProperty("recipients")
    private String recipients;

    @JsonProperty("severity")
    private String severity;
}
