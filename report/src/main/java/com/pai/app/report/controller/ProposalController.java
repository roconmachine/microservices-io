package com.pai.app.report.controller;

import com.pai.app.report.converter.ProposalConverter;
import com.pai.app.report.entity.ProposalEntity;
import com.pai.app.report.service.ProposalService;
import com.roconmachine.io.dataframe.report.interfaces.ProposalApi;
import com.roconmachine.io.dataframe.report.models.Proposal;
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
public class ProposalController implements ProposalApi {
    private final ProposalService proposalService;
    @Override
    public ResponseEntity<Proposal> submitProposal(@Valid Proposal proposal) {
        Optional<ProposalEntity> savedProposal = proposalService.save(ProposalConverter.toEntity(proposal));
        if (savedProposal.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        else return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
