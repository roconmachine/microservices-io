package com.roconmachine.io.fgaccess.service;

import com.roconmachine.io.fgaccess.core.AbstructService;
import com.roconmachine.io.fgaccess.entity.ActionEntity;
import com.roconmachine.io.fgaccess.entity.SubjectEntity;
import com.roconmachine.io.fgaccess.repository.SubjectRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SubjectService extends AbstructService<SubjectEntity, SubjectRepository> {

    public Mono<SubjectEntity> update(Long id, SubjectEntity entity){
        return this.repository.findById(id)
                .flatMap(
                        subjectEntity -> {
                            entity.setSubject_id(id);
                            entity.setUser_id(subjectEntity.getUser_id());
                            entity.setVersion(subjectEntity.getVersion());
                            return this.repository.save(entity);
                        }
                );
    }
}
