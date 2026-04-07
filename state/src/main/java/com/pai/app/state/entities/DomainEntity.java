package com.pai.app.state.entities;

import com.pai.app.state.core.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name = "domains")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class DomainEntity extends BaseEntity {
   @Column(name = "class_name", nullable = false)
   private String className;
}
