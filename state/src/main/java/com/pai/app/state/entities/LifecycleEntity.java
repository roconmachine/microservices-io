package com.pai.app.state.entities;

import com.pai.app.state.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name = "lifecycles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class LifecycleEntity extends BaseEntity {

    @Column(name = "from_state", nullable = false)
    private Long fromState;
    @Column(name = "to_state", nullable = false)
    private Long toState;
    @Column(name = "action_id", nullable = false)
    private Long actionId;
}
