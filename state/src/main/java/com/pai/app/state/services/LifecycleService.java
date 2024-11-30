package com.pai.app.state.services;

import com.pai.app.state.core.BaseService;
import com.pai.app.state.entities.LifecycleEntity;
import com.pai.app.state.repositories.LifecycleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LifecycleService extends BaseService<LifecycleEntity, LifecycleRepository> {

    @Autowired
    private LifecycleRepository repository;
    @PersistenceContext
    private EntityManager entityManager;



    public List<LifecycleEntity> searchNext(Long stateId, Long actionId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<LifecycleEntity> cq = cb.createQuery(LifecycleEntity.class);
        Root<LifecycleEntity> entity = cq.from(LifecycleEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        if (stateId != null) {
            predicates.add(cb.equal(entity.get("fromState"), stateId));
        }
        if (actionId != null) {
            predicates.add(cb.equal(entity.get("actionId"), actionId));
        }

        // Combine all predicates
        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        TypedQuery<LifecycleEntity> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    public List<LifecycleEntity> findAllByFromState(long l) {
        return repository.findAllByFromState(l);
    }
}
