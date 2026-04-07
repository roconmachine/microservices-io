package com.roconmachine.io.fgaccess.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("actions")
@Data
@Builder
public class ActionEntity {
    @Id
    public Long action_id;
    public String name;
    public String action_type;

    @Version
    public Long version;

    @CreatedDate
    public LocalDateTime created_at;

    @LastModifiedDate
    public LocalDateTime updated_at;
}
