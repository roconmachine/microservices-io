package com.roconmachine.io.fgaccess.service;

import com.roconmachine.io.fgaccess.entity.Properties;
import com.roconmachine.io.fgaccess.entity.RecordStatus;
import com.roconmachine.io.fgaccess.repo.PropertyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class PropertyService {
    private final PropertyRepository propertiesRepository;

    /**
     * Save a property
     * @param properties The property to save
     * @return Mono of the saved property
     */
    public Mono<Properties> saveProperty(Properties properties) {
        return propertiesRepository.save(properties);
    }

    /**
     * Delete properties by prefix
     * @param uuidPrefix The prefix to match properties to delete
     * @return Mono that completes when properties are deleted
     */
    public Mono<Properties> deletePropertiesByUuidPrefix(String uuid) {
        return propertiesRepository.findByUuid(uuid)
                .flatMap(properties -> {
                    properties.setRecordStatus(RecordStatus.DELETED);
                    return propertiesRepository.save(properties);
                });
    }

    public Mono<Properties> updatePropertiesByUuid(String uuid, Properties newProperties) {
        return propertiesRepository.findByUuid(uuid)
                .flatMap(properties -> {
                    properties.setKey(newProperties.getKey());
                    properties.setValueBoolean(newProperties.getValueBoolean());
                    properties.setValueNumber(newProperties.getValueNumber());
                    properties.setValueString(newProperties.getValueString());
                    return propertiesRepository.save(properties);
                });
    }

    public Mono<Properties> findByUuid(String uuid) {
        return propertiesRepository.findByUuid(uuid);
    }

    public Flux<Properties> findByUuidPrefix(String prefixEntity){
        return propertiesRepository.findByUuidStartingWith(prefixEntity);
    }

}
