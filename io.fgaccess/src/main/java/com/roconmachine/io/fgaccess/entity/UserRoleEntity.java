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

@Table("userrole")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleEntity {
    @Id
    public Long user_role_id;

    public Long role_id;
    public String user_id;

    @Version
    public Long version;

    @CreatedDate
    public LocalDateTime created_at;

    @LastModifiedDate
    public LocalDateTime updated_at;

}
