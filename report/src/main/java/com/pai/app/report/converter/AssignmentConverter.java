package com.pai.app.report.converter;

import com.pai.app.report.entity.AssignmentEntity;
import com.roconmachine.io.dataframe.report.models.Assignment;

public class AssignmentConverter {
    public static Assignment toModel(AssignmentEntity entity){
        return Assignment.builder()
                .assignBy(entity.getAssignBy())
                .assignTo(entity.getAssignTo())
                .reportId(entity.getReport())
                .proposalId(entity.getProposalId())
                .build();

    }

    public static AssignmentEntity toEntity(Assignment model){
        return AssignmentEntity.builder()
                .report(model.getReportId())
                .assignBy(model.getAssignBy())
                .assignTo(model.getAssignTo())
                .proposalId(model.getProposalId())
                .build();
    }
}
