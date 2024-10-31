package com.roconmachine.io.fgaccess.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("policymapping")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PolicyMappingEntity {
    @Id
    private Long id;
    @Column("policy_id") private Long policy_id;
    @Column("subject_id") private Long subject_id;
    @Column("resource_id") private Long resource_id;
    @Column("subject_type") private SubjectTypeEnum subject_type;
    @Column("action") private ActionEnum action;
    @Column("record_status") private RecordStatus recordStatus;

}
