package com.roconmachine.io.accounts.config;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.OffsetDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Domain {
    @CreatedDate
    private Date dateCreated = new Date();

    @LastModifiedDate
    private Date lastUpdated;

    @Version
    private Integer version;
}

