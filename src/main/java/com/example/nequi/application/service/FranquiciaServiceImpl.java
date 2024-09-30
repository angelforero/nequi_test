package com.example.nequi.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nequi.application.port.FranquiciaServicePort;
import com.example.nequi.domain.model.Franquicia;
import com.example.nequi.domain.repository.FranquiciaRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FranquiciaServiceImpl implements FranquiciaServicePort {
    @Autowired
	FranquiciaRepository franquiciaRepository;
/*
    public FranquiciaServiceImpl(FranquiciaRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<Franquicia> getUserById(Long id) {
        return franquiciaRepository.findById(id);
    }

    @Override
    public Mono<Franquicia> createUser(Franquicia user) {
        return franquiciaRepository.save(user);
    }*/

    @Override
    public Flux<Franquicia> getAll() {
        return franquiciaRepository.findAll();
    }
}
