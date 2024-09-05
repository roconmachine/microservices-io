package com.roconmachine.io.accounts.account;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
public record AccountRequest(Long id,
                             @NotNull(message = "Account title is required")
                             @Size(max = 255, message = "Account title max length is 255")
                             String title,
                             @NotNull(message = "Brif description is required")
                             @Size(max = 1000, message = "Brif description max length is 1000")
                             String brifDescription,
                             String reportername,
                             String reporterContact,
                             String reporterEmail,
                             String address
                             ) { }
