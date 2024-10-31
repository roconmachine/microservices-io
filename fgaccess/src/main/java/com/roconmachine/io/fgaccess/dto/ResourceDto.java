package com.roconmachine.io.fgaccess.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResourceDto {
    private Long id;  // Unique identifier for the resource
    private String type;  // Enum: ["FILE", "DATABASE", "API", "SERVICE", "CUSTOM"]
    private String source;  // The origin or source of the resource
    private String owner;
}
