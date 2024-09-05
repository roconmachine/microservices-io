package com.roconmachine.io.accounts.account;

import java.util.Date;

public record AccountResponse(Long id, String title, String brifDescription, String reportername, String reporterContact
                            , String reporterEmail, String address, Date createdDate) { }
