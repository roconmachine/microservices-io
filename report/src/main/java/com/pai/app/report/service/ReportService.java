package com.pai.app.report.service;

import com.pai.app.report.core.BaseService;
import com.pai.app.report.entity.ReportEntity;
import com.pai.app.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService extends BaseService<ReportEntity, ReportRepository> {
    
}
