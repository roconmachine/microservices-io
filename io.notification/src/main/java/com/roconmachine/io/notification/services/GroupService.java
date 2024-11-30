package com.roconmachine.io.notification.services;

import com.roconmachine.io.notification.core.AbstructService;
import com.roconmachine.io.notification.entities.GroupEntity;
import com.roconmachine.io.notification.repositories.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GroupService extends AbstructService<GroupEntity, GroupRepository> {

    @Autowired
    private GroupRepository groupRepository;
    public Mono<Void> delete(String groupName) {
        return repository.deleteByName(groupName);
    }

    public Mono<GroupEntity> getByName(String name){
        return repository.getByName(name);
    }
}
