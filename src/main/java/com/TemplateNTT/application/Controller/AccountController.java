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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebExchangeBindException;

import com.TemplateNTT.domain.Entity.Account;
import com.TemplateNTT.infraestructure.Services.AccountService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/Account")
public class AccountController {
	@Autowired
	private AccountService service;

	@GetMapping
	public Mono<ResponseEntity<Flux<Account>>> GetAll() {
		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(service.findAll()));
	}

	@PostMapping
	public Mono<ResponseEntity<Map<String, Object>>> SaveAccount(@Valid @RequestBody Mono<Account> monoAccount) {
		Map<String, Object> response = new HashMap<>();

		return monoAccount.flatMap(a -> {
			return service.save(a).map(c -> {
				response.put("Cuenta", c);
				response.put("mensaje", "Cuenta creada con exito");
				return ResponseEntity.created(URI.create("/api/Account/".concat(c.getAccountId())))
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
}
