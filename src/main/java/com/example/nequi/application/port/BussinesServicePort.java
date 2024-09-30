package com.example.nequi.application.port;

import com.example.nequi.domain.model.Franquicia;
import com.example.nequi.domain.model.FranquiciaSucursales;
import com.example.nequi.domain.model.Producto;
import com.example.nequi.domain.model.ProductoMaxSucursales;
import com.example.nequi.domain.model.Sucursal;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BussinesServicePort {	
		Flux<Franquicia> getAllFranquicias();
		Flux<Sucursal> getAllSucursales();
		Flux<Producto> getAllProductos();
		Mono<FranquiciaSucursales> getAllFranquiciaSucursales(Long franquiciaId);
		Mono<Franquicia> saveFranquicia(Franquicia franquicia);
		Mono<Sucursal> saveSucursal(Long franquiciaId, Sucursal sucursal);
		Mono<Producto> saveProducto(Long sucursalId, Producto producto);
		Mono<Void> deleteProductoById(Long productoId);
		Mono<Franquicia> updateFranquicia(Long productoId, Franquicia franquicia);
		Mono<Sucursal> updateSucursal(Long productoId, Sucursal sucursal);
		Mono<Producto> updateProducto(Long productoId, Producto producto);
		Flux<ProductoMaxSucursales> getProductoMaxSucursales(Long FranquiciaId);
	}
