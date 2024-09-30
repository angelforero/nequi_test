package com.example.nequi.infrastructure.adapter.persistence;

import com.example.nequi.domain.repository.SucursalRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.example.nequi.domain.model.ProductoMaxSucursales;
import com.example.nequi.domain.model.Sucursal;

@Repository
public interface SucursalRepositoryR2dbc extends R2dbcRepository<Sucursal, Long>, SucursalRepository {
	Flux<Sucursal> findAll();
	
	@Query("select * from sucursal s where s.id_franquicia =:franquiciaId")
	Flux<Sucursal> findAllById(Long franquiciaId);
	Mono<Sucursal> save(Sucursal sucursal);
}
