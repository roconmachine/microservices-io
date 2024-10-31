package com.roconmachine.io.fgaccess.dto;

import com.roconmachine.io.fgaccess.entity.RecordStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PropertiesDto {
    private Long id;
    private String uuid;
    private String key;
    private String valueString; // String value
    private Double valueNumber; // Numeric value
    private Boolean valueBoolean; // Boolean value
    private RecordStatus recordStatus;
}
