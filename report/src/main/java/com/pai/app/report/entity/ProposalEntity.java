package com.pai.app.report.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "proposals")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

//    @ManyToOne
    @JoinColumn(name = "report_id", nullable = false)
    private Long reportId;

    @Column(name = "user_id", nullable = false)
    private String userId;
    @Column(name = "state_id", nullable = false)
    private Long stateId;

    @Column(name = "action", nullable = false)
    private String action;

    @Column(name = "amount")
    private BigDecimal amount;

}
