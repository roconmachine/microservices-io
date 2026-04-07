package com.roconmachine.io.notification.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("email_notifications")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class EmailNotificationEntity {

    @Id
    public Long id;
    private String template_name;
    private String subject_placeholder;
    private String body_placeholder;
    private String status;
    private String recipients;
    private String severity;
    @CreatedDate
    private LocalDateTime createddate;

}
