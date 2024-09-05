package com.roconmachine.io.accounts.account;


import com.roconmachine.io.accounts.config.PrimarySequenceService;
import com.roconmachine.io.accounts.util.MongoOffsetDateTimeWriter;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;


@Component
public class AccountListener extends AbstractMongoEventListener<Account> {

    private final PrimarySequenceService primarySequenceService;

    public AccountListener(final PrimarySequenceService primarySequenceService) {
        this.primarySequenceService = primarySequenceService;
    }

    @Override
    public void onBeforeConvert(final BeforeConvertEvent<Account> event) {
        if (event.getSource().getId() == null) {
            event.getSource().setId(primarySequenceService.getNextValue());

        }
    }

}
