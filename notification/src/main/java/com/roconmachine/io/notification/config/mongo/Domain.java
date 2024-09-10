package com.roconmachine.io.notification.config.mongo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

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

