package com.roconmachine.io.fgaccess.entity;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("properties")
@Data
@Builder
public class Properties {

    @Id
    @Column("id")
    private Long id;
    @Column("uuid")
    private String uuid;
    @Column("key")
    private String key;
    @Column("value_string")
    private String valueString; // String value

    @Column("value_number")
    private Double valueNumber; // Numeric value

    @Column("value_boolean")
    private Boolean valueBoolean; // Boolean value

    @Column("record_status")
    private RecordStatus recordStatus;
}
