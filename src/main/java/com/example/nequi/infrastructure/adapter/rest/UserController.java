package com.example.nequi.infrastructure.adapter.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.nequi.application.port.FranquiciaServicePort;
import com.example.nequi.domain.model.Franquicia;
import com.example.nequi.domain.model.Producto;
import com.example.nequi.domain.model.Sucursal;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/nequi_test")
public class UserController {
	@Autowired
    FranquiciaServicePort franquiciaService;
/*
    public UserController(FranquiciaServicePort franquiciaService) {
        this.franquiciaService = franquiciaService;
    }

    // Editar elementos Franquicia
    @PutMapping("/franquicia")
    public Mono<Franquicia> addfranquicia(@RequestBody Franquicia franquicia) {
        return userService.getUserById(franquicia);
    }

 // Editar elementos Sucursal
    @PutMapping("/{franquiciaId}/sucursal")
    public Mono<Sucursal> addSucursal(@PathVariable Long franquiciaId, @RequestBody Sucursal sucursal) {
        return userService.getUserById(franquiciaId);
    }

 // Editar elementos Producto
    @PostMapping("/{franquiciaId}/{sucursalId}/producto")
    public Mono<Producto> addProducto(@PathVariable Long franquiciaId, @PathVariable Long sucursalId, @RequestBody Producto producto) {
        return userService.getUserById(sucursalId);
    }

    // Crear elementos Franquicia
    @PostMapping("/franquicia")
    public Mono<Franquicia> addfranquicia(@RequestBody Franquicia franquicia) {
        return userService.getUserById(franquicia);
    }

 // Crear elementos Sucursal
    @PostMapping("/{franquiciaId}/sucursal")
    public Mono<Sucursal> addSucursal(@PathVariable Long franquiciaId, @RequestBody Sucursal sucursal) {
        return userService.getUserById(franquiciaId);
    }

 // Crear elementos Producto
    @PostMapping("/{franquiciaId}/{sucursalId}/producto")
    public Mono<Producto> addProducto(@PathVariable Long franquiciaId, @PathVariable Long sucursalId, @RequestBody Producto producto) {
        return userService.getUserById(sucursalId);
    }

 // Eliminar elementos Producto
    @DeleteMapping("/{franquiciaId}/{sucursalId}/{productoId}")
    public Mono<Producto> addsucursal(@PathVariable Long franquiciaId, @PathVariable Long sucursalId, @PathVariable Long productoId) {
        return userService.getUserById(sucursalId);
    }

    // Modificar cantidad de producto existente
    @PostMapping
    public Mono<Producto> modificarProducto(@RequestBody Producto producto) {
        return userService.createUser(user);
    }

 // Consultar elementos
    @GetMapping("/{franquiciaId}")
    public Mono<Franquicia> getInventarioFranquicia(@PathVariable Long franquiciaId) {
        return userService.getUserById(franquiciaId);
    }*/

    // Consultar elementos
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Franquicia> getFranquicias() {
        return franquiciaService.getAll();
    }
}
