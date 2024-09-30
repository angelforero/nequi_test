package com.example.nequi.domain.repository;

import com.example.nequi.domain.model.Producto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductoRepository {
	Flux<Producto> findAll();
	Flux<Producto> findAllById(Long sucursalId);
	Mono<Producto> findById(Long productolId);
	Mono<Producto> save(Producto producto);
	Mono<Void> deleteById(Long productoId);
}
