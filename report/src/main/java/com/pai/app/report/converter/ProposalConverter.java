package com.pai.app.report.converter;

import com.pai.app.report.entity.ProposalEntity;
import com.roconmachine.io.dataframe.report.models.Proposal;

public class ProposalConverter {

    public static ProposalEntity toEntity(Proposal model){
        return ProposalEntity.builder().userId(model.getUserId())
                .action(model.getAction())
                .reportId(model.getReportId())
                .amount(model.getAmount())
                .stateId(model.getState())
                .build();
    }


    public static Proposal toModel(ProposalEntity entity){
        return Proposal.builder()
                .id(entity.getId())
                .amount(entity.getAmount())
                .action(entity.getAction())
                .state(entity.getStateId())
                .reportId(entity.getReportId())
                .build();
    }
}
