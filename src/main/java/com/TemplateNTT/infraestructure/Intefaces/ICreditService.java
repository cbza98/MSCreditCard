package com.TemplateNTT.infraestructure.Intefaces;

import com.TemplateNTT.domain.Entity.Credit;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICreditService {
	public Flux<Credit> findAll();

	public Mono<Credit> save(Credit _account);

	public Mono<Credit> findById(String Id);

	public Mono<Credit> delete(String Id);
}
