package com.pai.app.report.controller;

import com.pai.app.report.converter.ReportConverter;
import com.pai.app.report.entity.ReportEntity;
import com.pai.app.report.service.CommentService;
import com.pai.app.report.service.ReportService;
import com.roconmachine.io.dataframe.report.interfaces.ReportApi;
import com.roconmachine.io.dataframe.report.models.Report;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/${service.name}/${service.apiversion}")
@RequiredArgsConstructor
public class ReportController implements ReportApi {
    private final ReportService service;
    private final CommentService commentService;
    @Override
    public ResponseEntity<Void> applyAction(Long reportId, String actionName) {


        return null;
    }

    @Override
    public ResponseEntity<Report> submitReport(@Valid Report report) {
        Optional<ReportEntity> optionalEntity = service.save(ReportConverter.toEntity(report));
        ReportEntity savedEntity = optionalEntity
                .orElseThrow(() -> new RuntimeException("Failed to save the report"));
        Report responseModel = ReportConverter.toModel(savedEntity);
        return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
    }
}
