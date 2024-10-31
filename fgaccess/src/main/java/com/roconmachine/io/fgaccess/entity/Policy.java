package com.roconmachine.io.fgaccess.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table("policy")  // Maps to the 'policy' table in the database
public class Policy {

    @Id
    @Column("id")
    private Long id;

    @Column("name")
    private String name;

    @Column("effect")
    private EffectEnum effect; // Enum ["ALLOW", "DENY"]

    @Transient  // Represents a collection of conditions for this policy
    private List<Condition> conditions; // List of associated conditions
}