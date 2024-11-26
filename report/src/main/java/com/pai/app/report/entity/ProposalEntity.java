package com.pai.app.report.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "proposals")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "report_id", nullable = false)
    private ReportEntity report;

    @Column(name = "user_id", nullable = false)
    private String userId;
    @Column(name = "state_id", nullable = false)
    private Long stateId;

    @Column(name = "action", nullable = false)
    private String action;

    @Column(name = "amount")
    private double amount;

}
