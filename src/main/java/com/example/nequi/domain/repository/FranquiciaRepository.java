package com.example.nequi.domain.repository;

import com.example.nequi.domain.model.Franquicia;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FranquiciaRepository {
	Flux<Franquicia> findAll();
	Mono<Franquicia> findById(Long id);
    Mono<Franquicia> save(Franquicia user);
}
