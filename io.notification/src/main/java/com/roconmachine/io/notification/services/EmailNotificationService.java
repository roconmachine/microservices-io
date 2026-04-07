package com.roconmachine.io.notification.services;

import com.roconmachine.io.notification.core.AbstructService;
import com.roconmachine.io.notification.entities.EmailNotificationEntity;
import com.roconmachine.io.notification.repositories.EmailNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class EmailNotificationService extends AbstructService<EmailNotificationEntity, EmailNotificationRepository> {

    private final R2dbcEntityTemplate r2dbcTemplate;
    public Flux<EmailNotificationEntity> search(String status, String severity) {

        Criteria criteria = null;
        if(status != null)
            criteria = Criteria
                    .where("status")
                    .is(status);
        if (severity != null)
            criteria = criteria == null ? Criteria.where("severity").is(severity):
                    criteria.and("severity").is(severity);


        //assert criteria != null;
        return criteria == null ? null : r2dbcTemplate
                .select(EmailNotificationEntity.class)
                .matching(Query.query(criteria))
                .all();

    }
}
