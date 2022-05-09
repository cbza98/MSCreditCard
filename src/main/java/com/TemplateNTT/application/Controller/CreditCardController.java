package com.TemplateNTT.application.Controller;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebExchangeBindException;

import com.TemplateNTT.domain.Entity.CreditCard;
import com.TemplateNTT.infraestructure.Services.CreditCardService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/Credit")
public class CreditCardController {
	@Autowired
	private CreditCardService service;

	@GetMapping
	public Mono<ResponseEntity<Flux<CreditCard>>> FindAll() {
		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(service.findAll()));
	}

	@GetMapping("/{id}")
	public Mono<CreditCard> findById(@PathVariable String id) {
		return service.findById(id);
	}

	@PostMapping
	public Mono<ResponseEntity<Map<String, Object>>> Create(@Valid @RequestBody Mono<CreditCard> request) {
		Map<String, Object> response = new HashMap<>();

		return request.flatMap(a -> {
			return service.save(a).map(c -> {
				response.put("Tarjeta de Credito", c);
				response.put("mensaje", "Tarjeta de Credito creada con exito");
				return ResponseEntity.created(URI.create("/api/Account/".concat(c.getCreditcardid().toString())))
						.contentType(MediaType.APPLICATION_JSON_UTF8).body(response);
			});
		}).onErrorResume(t -> {
			return Mono.just(t).cast(WebExchangeBindException.class).flatMap(e -> Mono.just(e.getFieldErrors()))
					.flatMapMany(Flux::fromIterable)
					.map(fieldError -> "El campo:" + fieldError.getField() + " " + fieldError.getDefaultMessage())
					.collectList().flatMap(list -> {

						response.put("errors", list);
						response.put("timestamp", new Date());
						response.put("status", HttpStatus.BAD_REQUEST.value());

						return Mono.just(ResponseEntity.badRequest().body(response));

					});

		});
	}

	@PutMapping("/{id}")
	public Mono<ResponseEntity<CreditCard>> Update(@PathVariable String id, @RequestBody CreditCard request) {
		return service.update(id, request);

	}

	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> Delete(@PathVariable String id) {
		return service.delete(id).map(r -> ResponseEntity.ok().<Void>build())
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

}
