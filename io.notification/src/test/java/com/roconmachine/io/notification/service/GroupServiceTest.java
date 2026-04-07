package com.roconmachine.io.notification.service;

import com.roconmachine.io.notification.entities.GroupEntity;
import com.roconmachine.io.notification.services.GroupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.test.StepVerifier;

@DataR2dbcTest
@Testcontainers
@Import(GroupService.class)
public class GroupServiceTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.0")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass")
            .withInitScript("script.sql");

    @DynamicPropertySource
    static void dynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.r2dbc.url", () -> String.format("r2dbc:postgresql://%s:%d/%s",
                postgres.getHost(), postgres.getFirstMappedPort(), postgres.getDatabaseName()));
        registry.add("spring.r2dbc.username", postgres::getUsername);
        registry.add("spring.r2dbc.password", postgres::getPassword);
    }

    @Autowired private GroupService groupService;

    @BeforeEach
    void setup(){
        GroupEntity entity = GroupEntity.builder()
                .name("admin")
                .build();

        groupService.save(entity);
    }

    @Test
    void groupSaveTest(){
        GroupEntity entity = GroupEntity.builder()
                .name("operator")
                .build();

        StepVerifier.create(groupService.save(entity))
                .expectNextMatches(savedRecipient -> savedRecipient.getId() != null &&
                        savedRecipient.getName().equals("operator"))
                .verifyComplete();
    }

    @Test
    void groupDeleteTest() {
        GroupEntity entity = GroupEntity.builder()
                .name("operator")
                .build();

        StepVerifier.create(
                        groupService.save(entity) // Save the entity
                                .then(groupService.delete("operator")) // Delete the entity
                )
                .verifyComplete(); // Verify the delete operation completes successfully
    }


}
