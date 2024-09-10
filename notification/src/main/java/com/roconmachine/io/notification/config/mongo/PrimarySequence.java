package com.roconmachine.io.notification.config.mongo;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("primarySequences")
@Getter
@Setter
@Data
@NoArgsConstructor
public class PrimarySequence {

    @Id
    private String id;

    private long seq;

}
