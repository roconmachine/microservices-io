package com.roconmachine.io.notification.service;

import com.roconmachine.io.notification.entities.RecipientEntity;
import com.roconmachine.io.notification.services.RecipientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;



@Testcontainers
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RecipientServiceTest {

//    @Container
//    @ServiceConnection
//    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
//            "postgres:16:0"
//    );

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

    @Autowired
    RecipientService recipientService;

    List<RecipientEntity> recipientEntities;

    @Test
    void connectionTest(){
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();
    }

    @BeforeEach
    public void beforeEarch(){
        RecipientEntity entity = new RecipientEntity();
        entity.setName("rocon");
        entity.setEmail("rocon@gmail.com");

        RecipientEntity entity2 = new RecipientEntity();
        entity.setName("rocon");
        entity.setEmail("rocon@gmail.com");

        recipientEntities = new ArrayList<>();
        recipientEntities.add(entity2);
        recipientEntities.add(entity);

    }

    @Test
    void recipientSaveTest(){
        Mono<RecipientEntity> savedEntity = recipientService.save(recipientEntities.get(1));
        StepVerifier.create(savedEntity)
                .expectNextMatches(entity ->
                        entity != null && entity.getId() != null
                )
                .verifyComplete();
    }

    @Test
    void getRecipientTest() {
        // Save a RecipientEntity
        Mono<RecipientEntity> savedEntity = recipientService.save(recipientEntities.get(1));

        // Test the save operation and validate the retrieved entity by its ID
        StepVerifier.create(savedEntity.flatMap(saved ->
                        recipientService.getById(saved.getId()) // Fetch the entity using the service
                                .map(retrieved -> retrieved.getId().equals(saved.getId())) // Compare IDs
                ))
                .expectNext(true) // Expect the comparison to be true
                .verifyComplete();
    }

    @Test
    void deleteRecipientTest() {
        // Save a RecipientEntity for testing delete
        Mono<RecipientEntity> savedEntity = recipientService.save(recipientEntities.get(1));

        // Perform the delete operation and validate
        StepVerifier.create(
                        savedEntity.flatMap(saved ->
                                recipientService.delete(saved.getId()) // Delete the saved entity
                                        .then(recipientService.getById(saved.getId())) // Try to fetch it again
                        )
                )
                 .verifyComplete();
    }


}
