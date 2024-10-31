package com.roconmachine.io.fgaccess.service;

import com.roconmachine.io.fgaccess.entity.Resource;
import com.roconmachine.io.fgaccess.repo.ResourceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ResourceService {
    private final ResourceRepository resourceRepository;
    private final PropertyService propertyService;


    public Mono<Resource> createResource(Resource resource) {
        String propertyIds = "000";
        return resourceRepository.save(resource)
                .flatMap(savedResource -> {
                    // Save the properties separately if needed
                    if (resource.getProperties() != null) {
                        return Flux.fromIterable(resource.getProperties())
                                .flatMap(property -> {
                                    property.setUuid("RES_" + savedResource.getId() + incrementId(propertyIds)); // Set the resource ID
                                    return propertyService.saveProperty(property); // Save the property
                                })
                                .then(Mono.just(savedResource)); // Return the saved resource
                    }
                    return Mono.just(savedResource);
                });
    }

    private String incrementId(String id) {
        // Parse the ID as an integer, increment it, and format it back to a string with leading zeros
        int numericId = Integer.parseInt(id); // Convert to integer
        numericId++; // Increment the ID
        return String.format("%03d", numericId); // Format back to string with leading zeros
    }


    public Mono<Resource> getResourceById(Long id) {
        return resourceRepository.findById(id)
                .flatMap(resource -> {
                    // Load the properties for the resource
                    return propertyService.findByUuidPrefix("RES_" + String.format("%03d", id))
                            .collectList() // Collect properties into a List
                            .map(properties -> {
                                resource.setProperties(properties); // Set the properties in the resource
                                return resource; // Return the resource with loaded properties
                            });
                });
    }

    public Mono<Void> deleteResource(Long id) {
        return resourceRepository.findById(id)
                .flatMap(resource -> {
                    // First delete properties associated with the resource
                    return propertyService.deletePropertiesByUuidPrefix("RES" + String.format("%03d", resource.getId()))
                            .then(resourceRepository.deleteById(id)); // Then delete the resource
                }).then(); // Return a Mono<Void>
    }
}
