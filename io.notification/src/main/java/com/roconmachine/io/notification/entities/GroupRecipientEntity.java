package com.roconmachine.io.notification.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("groups")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupRecipientEntity {
    @Id
    public Long id;
    private String groupName;
    private Long recipientId;
}
