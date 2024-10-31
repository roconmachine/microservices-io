package com.roconmachine.io.fgaccess.dto;

import com.roconmachine.io.fgaccess.entity.Condition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConditionDto {

    private Long id;
    private Condition.TargetType target;
    private Condition.OperatorType operator;
    private List<PropertiesDto> properties; // Assuming values can be stored as strings

    // Enum for Target
    public enum TargetType {
        TIME,
        USER_ATTRIBUTE,
        RESOURCE_ATTRIBUTE,
        ENVIRONMENT,
        CUSTOM
    }

    // Enum for Operator
    public enum OperatorType {
        EQUALS,
        NOT_EQUALS,
        GREATER_THAN,
        LESS_THAN,
        IN,
        NOT_IN,
        BETWEEN,
        CONTAINS
    }
}
