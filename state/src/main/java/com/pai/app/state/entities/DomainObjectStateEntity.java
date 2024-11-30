package com.pai.app.state.entities;

import com.pai.app.state.core.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name = "domain_object_state")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class DomainObjectStateEntity extends BaseEntity {
    @Column(name = "class_name")
    private String className;

    @Column(name = "object_id")
    private Long objectId;

    @Column(name = "state_id")
    private Long stateId;

}
