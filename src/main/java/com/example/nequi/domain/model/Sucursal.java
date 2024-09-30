package com.example.nequi.domain.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sucursal {
	private Long id;
    private String nombre;
    private List<Producto> productos;
}
