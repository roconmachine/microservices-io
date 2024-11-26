package com.pai.app.report.repository;

import com.pai.app.report.entity.ProposalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposalRepository extends JpaRepository<ProposalEntity, Long> {
}
