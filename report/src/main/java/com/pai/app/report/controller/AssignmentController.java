package com.pai.app.report.controller;

import com.pai.app.report.converter.AssignmentConverter;
import com.pai.app.report.entity.AssignmentEntity;
import com.pai.app.report.service.AssignmentService;
import com.roconmachine.io.dataframe.report.interfaces.AssignApi;
import com.roconmachine.io.dataframe.report.models.Assignment;
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
public class AssignmentController implements AssignApi {

    private final AssignmentService assignmentService;
    @Override
    public ResponseEntity<Report> assignReport(@Valid Assignment assignment) {
        Optional<AssignmentEntity> savedAssign = assignmentService.save(AssignmentConverter.toEntity(assignment));
        if (savedAssign.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        else return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
