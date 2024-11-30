package com.roconmachine.io.notification.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("email_notifications")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipientEntity {
    @Id
    public Long id;
    private String name;
    private String email;
    private String phone;
    private String device_id;

}
