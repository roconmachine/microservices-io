package com.roconmachine.io.fgaccess.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;


@Table(name = "conditions")
public class Condition {

    @Id
    private Long id;

    @Column
    private TargetType target;

    @Column
    private OperatorType operator;

    @Transient
    private List<Properties> properties; // Assuming values can be stored as strings

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

