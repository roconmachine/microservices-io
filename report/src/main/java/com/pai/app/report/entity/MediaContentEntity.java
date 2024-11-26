package com.pai.app.report.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "media_contents")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MediaContentEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "report_id", nullable = false)
    private ReportEntity report;

    @Column(name = "media_source")
    private String mediaSource;

    @Column(name = "media_type")
    private String mediaType;
}
