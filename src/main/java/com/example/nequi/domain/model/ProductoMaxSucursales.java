package com.example.nequi.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoMaxSucursales {
	
	public ProductoMaxSucursales(Long id, String nombre, Long max) {
		this.id = id;
		this.nombre = nombre;
		this.max = max;
	}
	private Long id;
    private String nombre;
    private Long max;

}
