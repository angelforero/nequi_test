package com.example.nequi.application.port;

import com.example.nequi.domain.model.Franquicia;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FranquiciaServicePort {
		Flux<Franquicia> getAll();
	    //Mono<Franquicia> getUserById(Long id);
	    //Mono<Franquicia> createUser(Franquicia user);
	}
