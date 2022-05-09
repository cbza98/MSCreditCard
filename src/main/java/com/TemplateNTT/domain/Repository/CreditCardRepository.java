package com.TemplateNTT.domain.Repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.TemplateNTT.domain.Entity.CreditCard;

public interface CreditCardRepository extends ReactiveMongoRepository<CreditCard, String> {

}
