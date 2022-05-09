package com.TemplateNTT.infraestructure.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.TemplateNTT.domain.Entity.CreditCard;
import com.TemplateNTT.domain.Repository.CreditCardRepository;
import com.TemplateNTT.infraestructure.Intefaces.ICreditCardService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditCardService implements ICreditCardService {

	@Autowired(required = true)
	private CreditCardRepository repository;

	@Override
	public Flux<CreditCard> findAll() {
		return repository.findAll();
	}

	@Override
	public Mono<CreditCard> save(CreditCard entity) {
		return repository.save(entity);

	}

	@Override
	public Mono<CreditCard> delete(String Id) {
		return repository.findById(Id).flatMap(deleted -> repository.delete(deleted).then(Mono.just(deleted)));
	}

	@Override
	public Mono<CreditCard> findById(String Id) {
		return repository.findById(Id);
	}

	public Mono<ResponseEntity<CreditCard>> update(String id, CreditCard _request) {
		return repository.findById(id).flatMap(a -> {
			a.setAccountcode(_request.getAccountcode());
			a.setCardname(_request.getCardname());
			a.setCardnumber(_request.getCardnumber());
			a.setCcv(_request.getCcv());
			a.setCreditcardid(_request.getCreditcardid());
			a.setCreditid(_request.getCreditid());
			a.setDdate(_request.getDdate());
			a.setValid(_request.getValid());
			return repository.save(a);
		}).map(updated -> new ResponseEntity<>(updated, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.OK));
	}
}
