package com.roconmachine.io.fgaccess.entity;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table("resource")  // Maps to the 'resource' table in the database
@Data
@Builder
public class Resource {

    @Id
    @Column("id")
    private Long id;  // Unique identifier for the resource

    @Column("type")
    private String type;  // Enum: ["FILE", "DATABASE", "API", "SERVICE", "CUSTOM"]

    @Column("source")
    private String source;  // The origin or source of the resource

    @Column("owner")
    private String owner;  // Owner of the resource (user ID or group ID)

    @Transient
    private List<Properties> properties; // Assuming values can be stored as strings
    public enum ResourceType {
        FILE,
        DATABASE,
        API,
        SERVICE,
        CUSTOM
    }

    @Column("record_status")
    private RecordStatus recordStatus;

}
