package com.roconmachine.io.accounts.account;


import org.springframework.data.mongodb.repository.MongoRepository;


public interface AccountRepository extends MongoRepository<Account, Long> {
}
