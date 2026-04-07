package com.pai.app.report.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "assignments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "report_id", nullable = false)
    private Long report;

    @Column(name = "assign_by", nullable = false)
    private String assignBy;


    @Column(name = "assign_to", nullable = false)
    private String assignTo;


    @Column(name = "proposal_id", nullable = false)
    private Long proposalId;
}
