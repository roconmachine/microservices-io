package com.roconmachine.io.notification.entity;

import com.roconmachine.io.models.MessageTemplate;
import com.roconmachine.io.models.RequestNotification;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Document("Notification")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {
    @Id Long id;
    @NotNull
    RequestNotification.TypeEnum type;
    @NotNull
    String header;
    @NotNull
    List<String> destinations;
    String sender;
    @NotNull
    MessageTemplate template;
    NotificationStatus status;
}




