package com.example.nequi.domain.model;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sucursal {
	@Id
	private Long id;
    private String nombre;
    private Long id_franquicia;
}
