package com.pai.app.state.entities;

import com.pai.app.state.core.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name = "states")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class StateEntity  extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "rank")
    private Integer rank;

    @Column(name = "domain")
    private String domain;

    @Column(name = "reference")
    private String reference;

    @Column(name = "is_leaf", columnDefinition = "boolean default false")
    private Boolean isLeaf;

    @Column(name = "is_root", columnDefinition = "boolean default false")
    private Boolean isRoot;
}
