package com.example.nequi.domain.model;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Franquicia {
    @Id
	private Long id;
    private String nombre;

}
