package com.roconmachine.io.fgaccess.service;

import com.roconmachine.io.dataframe.fgaccess.models.Role;
import com.roconmachine.io.fgaccess.converters.RoleConverter;
import com.roconmachine.io.fgaccess.core.AbstructService;
import com.roconmachine.io.fgaccess.entity.RoleEntity;
import com.roconmachine.io.fgaccess.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RoleService extends AbstructService<RoleEntity, RoleRepository> {
    private final R2dbcEntityTemplate r2dbcTemplate;
    private final RoleRepository roleRepository;
    public Flux<RoleEntity> search(String name){
        Criteria criteria = Criteria.empty();
        if(name != null)
            criteria = Criteria
                    .where("name")
                    .like("%"+name+"%");
        return r2dbcTemplate
                .select(RoleEntity.class)
                .matching(Query.query(criteria))
                .all();
    }


    public Mono<RoleEntity> update(Long id, RoleEntity entity){
        return this.repository.findById(id)
                .flatMap(
                        roleEntity -> {
                            entity.setRole_id(id);
                            entity.setVersion(roleEntity.getVersion());
                            return this.repository.save(entity);
                        }
                );
    }


}
