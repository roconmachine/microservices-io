package com.roconmachine.io.fgaccess.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("policy")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PolicyEntity {

    @Id
    public Long policy_id;
    public String subject_id;
    public String action_name;
    public String object_id;
    public boolean status;
    @Version
    public Long version;

    @CreatedDate
    public LocalDateTime created_at;

    @LastModifiedDate
    public LocalDateTime updated_at;
}
