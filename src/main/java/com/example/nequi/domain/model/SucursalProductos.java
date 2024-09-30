package com.example.nequi.domain.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SucursalProductos {
	
	public SucursalProductos(Franquicia franquicia, List<Sucursal> sucursales) {
        this.franquicia = franquicia;
        this.sucursales = sucursales;
    }
	
    private Franquicia franquicia;
    private List<Sucursal> sucursales;

}
