package com.roconmachine.io.fgaccess.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("properties")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertiesEntity {
    @Id private Long id;
    @Column private String uuid;
    @Column private String key;
    @Column private String value_string;
    @Column private BigDecimal value_number;
    @Column private boolean value_boolean;

}
