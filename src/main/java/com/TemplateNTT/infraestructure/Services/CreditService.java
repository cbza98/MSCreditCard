package com.TemplateNTT.infraestructure.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.TemplateNTT.domain.Entity.Credit;
import com.TemplateNTT.domain.Repository.CreditRepository;
import com.TemplateNTT.infraestructure.Intefaces.ICreditService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditService implements ICreditService {

	@Autowired(required = true)
	private CreditRepository repository;

	@Override
	public Flux<Credit> findAll() {
		return repository.findAll();
	}

	@Override
	public Mono<Credit> save(Credit entity) {
		return repository.save(entity);

	}

	@Override
	public Mono<Credit> delete(String Id) {
		return repository.findById(Id).flatMap(deleted -> repository.delete(deleted).then(Mono.just(deleted)));
	}

	@Override
	public Mono<Credit> findById(String Id) {
		return repository.findById(Id);
	}

	public Mono<ResponseEntity<Credit>> update(String id, Credit _request) {
		return repository.findById(id).flatMap(a -> {
			a.setApprovedcredit(_request.getApprovedcredit());
			a.setAssignmentdate(_request.getAssignmentdate());
			a.setCreatedate(_request.getCreatedate());
			a.setCode_BusinessPartner(_request.getCode_BusinessPartner());
			a.setCreditcardid(_request.getCreditcardid());
			a.setCreditid(_request.getCreditid());
			a.setCreditname(_request.getCreditname());
			a.setCredittype(_request.getCredittype());
			a.setCurrencycredit(_request.getCurrencycredit());
			a.setValid(_request.getValid());
			return repository.save(a);
		}).map(updated -> new ResponseEntity<>(updated, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.OK));
	}
}
