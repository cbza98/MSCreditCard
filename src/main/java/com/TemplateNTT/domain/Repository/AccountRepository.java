package com.TemplateNTT.domain.Repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.TemplateNTT.domain.Entity.Account;

public interface AccountRepository extends ReactiveMongoRepository<Account, String> {

}
