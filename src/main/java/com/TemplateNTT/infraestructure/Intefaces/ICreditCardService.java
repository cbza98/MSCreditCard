package com.TemplateNTT.infraestructure.Intefaces;

import com.TemplateNTT.domain.Entity.CreditCard;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICreditCardService {
	public Flux<CreditCard> findAll();

	public Mono<CreditCard> save(CreditCard _account);

	public Mono<CreditCard> findById(String Id);

	public Mono<CreditCard> delete(String Id);
}
