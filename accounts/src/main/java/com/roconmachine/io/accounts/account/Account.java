package com.roconmachine.io.accounts.account;

import com.roconmachine.io.accounts.config.Domain;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document("Account")
public class Account extends Domain {

    @Id
    private Long id;
    private String title;
    private String brifDescription;
    private String reportername;
    private String reporterContact;
    private String reporterEmail;
    private String address;

}
