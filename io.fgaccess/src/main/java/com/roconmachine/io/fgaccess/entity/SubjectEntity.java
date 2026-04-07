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

@Table("subject")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectEntity {
    @Id
    public Long subject_id;

    public String user_id;
    public boolean status;

    @Version
    public Long version;

    @CreatedDate
    public LocalDateTime created_at;

    @LastModifiedDate
    public LocalDateTime updated_at;

}
