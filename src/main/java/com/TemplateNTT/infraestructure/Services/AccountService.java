package com.TemplateNTT.infraestructure.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TemplateNTT.domain.Entity.Account;
import com.TemplateNTT.domain.Repository.AccountRepository;
import com.TemplateNTT.infraestructure.Intefaces.IAccountService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountService implements IAccountService {

	@Autowired(required = true)
	private AccountRepository repository;

	@Override
	public Flux<Account> findAll() {
		return repository.findAll();
	}

	@Override
	public Mono<Account> save(Account _account) {
		return repository.save(_account);

	}

	@Override
	public Mono<Void> delete(Account _account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Account> findById(String Id) {
		// TODO Auto-generated method stub
		return null;
	}

}
