package com.TemplateNTT.infraestructure.Intefaces;

import com.TemplateNTT.domain.Entity.Account;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAccountService {
	public Flux<Account> findAll();

	public Mono<Account> save(Account _account);

	public Mono<Void> delete(Account _account);

	public Mono<Account> findById(String Id);
}
