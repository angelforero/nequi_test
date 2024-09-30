package com.example.nequi.infrastructure.adapter.persistence;

import com.example.nequi.domain.repository.FranquiciaRepository;

import reactor.core.publisher.Flux;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.example.nequi.domain.model.Franquicia;

@Repository
public interface FranquiciaRepositoryR2dbc extends R2dbcRepository<Franquicia, Long>, FranquiciaRepository {
	Flux<Franquicia> findAll();
}
