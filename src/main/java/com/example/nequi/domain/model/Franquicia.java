package com.example.nequi.domain.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Franquicia {
    private Long id;
    private String nombre;
    private List<Sucursal> sucursales;

}
