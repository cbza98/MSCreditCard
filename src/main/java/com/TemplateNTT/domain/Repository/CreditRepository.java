package com.TemplateNTT.domain.Repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.TemplateNTT.domain.Entity.Credit;

public interface CreditRepository extends ReactiveMongoRepository<Credit, String> {

}
