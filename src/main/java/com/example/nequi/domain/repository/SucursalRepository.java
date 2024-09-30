package com.example.nequi.domain.repository;

import com.example.nequi.domain.model.Sucursal;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SucursalRepository {
	Flux<Sucursal> findAll();
	Flux<Sucursal> findAllById(Long franquiciaId);
	Mono<Sucursal> findById(Long franquiciaId);
	Mono<Sucursal> save(Sucursal sucursal);
}
