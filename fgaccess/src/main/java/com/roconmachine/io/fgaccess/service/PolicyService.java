package com.roconmachine.io.fgaccess.service;

import com.roconmachine.io.fgaccess.repo.PolicyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PolicyService {
    private final PolicyRepository policyRepository;
    private final ConditionService conditionService;


}
