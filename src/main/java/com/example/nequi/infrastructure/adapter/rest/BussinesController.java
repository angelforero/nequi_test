package com.example.nequi.infrastructure.adapter.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.nequi.application.port.BussinesServicePort;
import com.example.nequi.domain.model.Franquicia;
import com.example.nequi.domain.model.FranquiciaSucursales;
import com.example.nequi.domain.model.Producto;
import com.example.nequi.domain.model.ProductoMaxSucursales;
import com.example.nequi.domain.model.Sucursal;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/nequi_test")
public class BussinesController {
	@Autowired
    BussinesServicePort bussinesService;
	
// Crear elementos Franquicia
    @PostMapping("/franquicia")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Franquicia> addfranquicia(@RequestBody Franquicia franquicia) {
        return bussinesService.saveFranquicia(franquicia);
    }

 // Crear elementos Sucursal
    @PostMapping("/{franquiciaId}/sucursal")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Sucursal> addSucursal(@PathVariable Long franquiciaId, @RequestBody Sucursal sucursal) {
        return bussinesService.saveSucursal(franquiciaId, sucursal);
    }

 // Crear elementos Producto
    @PostMapping("/{sucursalId}/producto")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Producto> addProducto(@PathVariable Long sucursalId, @RequestBody Producto producto) {
        return bussinesService.saveProducto(sucursalId, producto);
    }
    
    
    
 // Eliminar elementos Producto
    @DeleteMapping("/producto/{productoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> addsucursal(@PathVariable Long productoId) {
        return bussinesService.deleteProductoById(productoId);
    }
	
	
    
 // Editar elementos Sucursal
    @PutMapping("/franquicia/{franquiciaId}")
    public Mono<Franquicia> updateFranquicia(@PathVariable Long franquiciaId, @RequestBody Franquicia franquicia) {
        return bussinesService.updateFranquicia(franquiciaId, franquicia);
    }
    
 // Editar elementos Sucursal
    @PutMapping("/sucursal/{sucursalId}")
    public Mono<Sucursal> updateSucursal(@PathVariable Long sucursalId, @RequestBody Sucursal sucursal) {
        return bussinesService.updateSucursal(sucursalId, sucursal);
    }
    
// Editar elementos Sucursal
    @PutMapping("/producto/{productoId}")
    public Mono<Producto> updateProducto(@PathVariable Long productoId, @RequestBody Producto producto) {
        return bussinesService.updateProducto(productoId, producto);
    }
	
    

    @GetMapping("/franquicias")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Franquicia> getFranquicias() {
        return bussinesService.getAllFranquicias();
    }
    
    @GetMapping("/sucursales")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Sucursal> getSucursales() {
        return bussinesService.getAllSucursales();
    }
    
    @GetMapping("/productos")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Producto> getProductos() {
        return bussinesService.getAllProductos();
    }
    
    
    
    @GetMapping("/productos/maximos/{franquiciaId}")
    @ResponseStatus(HttpStatus.OK)
    public Flux<ProductoMaxSucursales> getProductoMaxSucursales(@PathVariable Long franquiciaId) {
        return bussinesService.getProductoMaxSucursales(franquiciaId);
    }
    
    
    
    
    @GetMapping("/all2/{franquiciaId}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<FranquiciaSucursales> getFranquicias2(@PathVariable Long franquiciaId) {
        return bussinesService.getAllFranquiciaSucursales(franquiciaId);
    }
}
