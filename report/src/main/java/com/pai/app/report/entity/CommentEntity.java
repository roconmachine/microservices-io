package com.pai.app.report.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "comments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "report_id", nullable = false)
    private ReportEntity report;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "parent_comment")
    private Long parentId;

    @Column(name = "rank")
    private Integer rank;


}
