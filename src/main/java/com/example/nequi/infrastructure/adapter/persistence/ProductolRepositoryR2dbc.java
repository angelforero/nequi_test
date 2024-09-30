package com.example.nequi.infrastructure.adapter.persistence;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.example.nequi.domain.model.Producto;
import com.example.nequi.domain.repository.ProductoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductolRepositoryR2dbc extends R2dbcRepository<Producto, Long>, ProductoRepository {
	Flux<Producto> findAll();
	
	@Query("select * from producto p where p.id_sucursal =:productoId")
	Flux<Producto> findAllById(Long productoId);
	Mono<Producto> save(Producto producto);
}
