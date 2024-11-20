package com.roconmachine.io.fgaccess.service;

import com.roconmachine.io.fgaccess.core.AbstructService;
import com.roconmachine.io.fgaccess.entity.ActionEntity;
import com.roconmachine.io.fgaccess.entity.RoleEntity;
import com.roconmachine.io.fgaccess.repository.ActionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ActionService extends AbstructService<ActionEntity, ActionRepository> {
    private final R2dbcEntityTemplate r2dbcTemplate;
    private final ActionRepository actionRepository;

    public Flux<ActionEntity> search(String name, String actionType){
        Criteria criteria = Criteria.empty();
        if(name != null)
            criteria = Criteria
                    .where("name")
                    .like("%"+name+"%");
        if (actionType != null)
            criteria = Criteria
                    .where("action_type")
                    .like("%"+actionType+"%");
        return r2dbcTemplate
                .select(ActionEntity.class)
                .matching(Query.query(criteria))
                .all();
    }

    public Mono<ActionEntity> update(Long id, ActionEntity entity){
        return this.repository.findById(id)
                .flatMap(
                        roleEntity -> {
                            entity.setAction_id(id);
                            entity.setVersion(roleEntity.getVersion());
                            return this.repository.save(entity);
                        }
                );
    }
}
