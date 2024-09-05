package com.roconmachine.io.accounts.account;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public Account toAccount(AccountRequest request) {
        if(request == null) return null;
        return Account.builder().id(request.id()).title(request.title()).brifDescription(request.brifDescription())
                .address(request.address()).reportername(request.reportername()).reporterContact(request.reporterContact())
                .reporterEmail(request.reporterEmail()).build();
    }

    public AccountResponse fromAccount(Account account){
        if(account == null) return null;
        return new AccountResponse(account.getId(), account.getTitle(), account.getBrifDescription(),
                account.getReportername(), account.getReporterContact(), account.getReporterEmail(), account.getAddress(),account.getDateCreated());
    }

    public void mergeAccount(Account account, AccountRequest request){
        if(StringUtils.isEmpty(request.title())) account.setTitle(request.title());
        if(StringUtils.isEmpty(request.brifDescription())) account.setBrifDescription(request.brifDescription());
        if(StringUtils.isEmpty(request.reportername())) account.setReportername(request.reportername());
        if(StringUtils.isEmpty(request.address())) account.setAddress(request.address());
        if(StringUtils.isEmpty(request.reporterEmail())) account.setReporterEmail(request.reporterEmail());
        if(StringUtils.isEmpty(request.reporterContact())) account.setReporterContact(request.reporterContact());

    }
}
