package com.roconmachine.io.fgaccess.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table("resource")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceEntity {

    @Id
    private Long id;

    @Column private String name;
    @Column private String type;
    @Column private String source;
    @Column private String owner;
    @Column private RecordStatus record_status;

    @Transient private List<PropertiesEntity> properties;
}
