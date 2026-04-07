package com.roconmachine.io.notification.service;


import com.roconmachine.io.notification.entities.NotificationTemplateEntity;
import com.roconmachine.io.notification.services.NotificationTemplateService;
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

import java.util.ArrayList;
import java.util.List;

@DataR2dbcTest
@Testcontainers
@Import(NotificationTemplateService.class)
public class NotificationTemplateServiceTest {
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
    private NotificationTemplateService notificationTemplateService;

    private List<NotificationTemplateEntity> list = new ArrayList<>();
    @BeforeEach
    void setup(){
        NotificationTemplateEntity entity = NotificationTemplateEntity.builder()
                .name("order")
                .subject_template("Application for {$1}")
                .body_template("Hello {$2}")
                .build();
        list.add(entity);
    }

//    @Test
//    void notificationTemplateUpdateTest() {
//        Long savedId = 0L;
//        StepVerifier.create(notificationTemplateService.save(list.get(0)))
//                .expectNextMatches(savedRecipient -> savedRecipient.getId() != null &&
//                        savedRecipient.getName().equals("order"))
//                .verifyComplete();
//        StepVerifier.create(
//                        notificationTemplateService.getById(1L) // Fetch the entity
//                                .flatMap(notificationTemplateEntity -> { // Update the entity
//                                    notificationTemplateEntity.setName("order updated");
//                                    return notificationTemplateService.save(notificationTemplateEntity); // Save the updated entity
//                                })
//                )
//                .expectNextMatches(updatedEntity -> updatedEntity.getName().equals("order updated")) // Verify the update
//                .verifyComplete(); // Ensure the stream completes
//    }

    @Test
    void notificationTemplateSaveTest(){
        StepVerifier.create(notificationTemplateService.save(list.get(0)))
                .expectNextMatches(savedRecipient -> savedRecipient.getId() != null &&
                        savedRecipient.getName().equals("order"))
                .verifyComplete();
    }
    @Test
    void notificationTemplateDeleteTest(){
        StepVerifier.create(notificationTemplateService.delete(1L))
                .expectNext(true)
                .verifyComplete();

        StepVerifier.create(notificationTemplateService.getById(1L))
                .verifyComplete(); // Should complete without emitting any value
    }

    @Test
    void notificationTemplateGetByIDTest(){
        StepVerifier.create(notificationTemplateService.getById(1L))
                .verifyComplete(); // Should complete without emitting any value
    }


    @Test
    void notificationTemplateGetByName(){
        notificationTemplateService.save(list.get(0)).block();
        StepVerifier.create(notificationTemplateService.getByName("order"))
                .expectNextMatches(template ->
                    template.getName().equals("order")
                )
                .verifyComplete(); // Should complete without emitting any value
    }



}
