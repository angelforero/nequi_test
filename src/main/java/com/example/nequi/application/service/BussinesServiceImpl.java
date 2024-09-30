package com.example.nequi.application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;

import com.example.nequi.application.port.BussinesServicePort;
import com.example.nequi.domain.model.Franquicia;
import com.example.nequi.domain.model.FranquiciaSucursales;
import com.example.nequi.domain.model.Producto;
import com.example.nequi.domain.model.ProductoMaxSucursales;
import com.example.nequi.domain.model.Sucursal;
import com.example.nequi.domain.repository.FranquiciaRepository;
import com.example.nequi.domain.repository.ProductoRepository;
import com.example.nequi.domain.repository.SucursalRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BussinesServiceImpl implements BussinesServicePort {
	@Autowired
	FranquiciaRepository franquiciaRepository;

	@Autowired
	SucursalRepository sucursalRepository;

	@Autowired
	ProductoRepository productoRepository;
	
	@Autowired
	DatabaseClient databaseClient;

	@Override
	public Mono<Franquicia> saveFranquicia(Franquicia franquicia) {
		return franquiciaRepository.save(franquicia);
	}

	@Override
	public Mono<Sucursal> saveSucursal(Long franquiciaId, Sucursal sucursal) {
		sucursal.setId_franquicia(franquiciaId);
		return sucursalRepository.save(sucursal);
	}

	@Override
	public Mono<Producto> saveProducto(Long sucursalId, Producto producto) {
		producto.setId_sucursal(sucursalId);
		return productoRepository.save(producto);
	}

	@Override
	public Mono<Void> deleteProductoById(Long productoId) {
		return productoRepository.deleteById(productoId);
	}
	
	@Override
	public Mono<Franquicia> updateFranquicia(Long franquiciaId, Franquicia franquicia) {
		return franquiciaRepository.findById(franquiciaId).map(Optional::of).defaultIfEmpty(Optional.empty())
				.flatMap(fr -> {
					if (fr.isPresent()) {
						franquicia.setId(franquiciaId);
						if (franquicia.getNombre() == null) {
							franquicia.setNombre(fr.get().getNombre());
						}

						return franquiciaRepository.save(franquicia);
					}

					return Mono.empty();
				});
	}

	@Override
	public Mono<Sucursal> updateSucursal(Long sucursalId, Sucursal sucursal) {
		return sucursalRepository.findById(sucursalId).map(Optional::of).defaultIfEmpty(Optional.empty())
				.flatMap(su -> {
					if (su.isPresent()) {
						sucursal.setId(sucursalId);
						if (sucursal.getNombre() == null) {
							sucursal.setNombre(su.get().getNombre());
						}
						if (sucursal.getId_franquicia() == null) {
							sucursal.setId_franquicia(su.get().getId_franquicia());
						}

						return sucursalRepository.save(sucursal);
					}

					return Mono.empty();
				});
	}

	@Override
	public Mono<Producto> updateProducto(Long productoId, Producto producto) {
		return productoRepository.findById(productoId).map(Optional::of).defaultIfEmpty(Optional.empty())
				.flatMap(pr -> {
					if (pr.isPresent()) {
						producto.setId(productoId);
						if (producto.getCantidad() == null) {
							producto.setCantidad(pr.get().getCantidad());
						}
						if (producto.getNombre() == null) {
							producto.setNombre(pr.get().getNombre());
						}
						if (producto.getId_sucursal() == null) {
							producto.setId_sucursal(pr.get().getId_sucursal());
						}

						return productoRepository.save(producto);
					}

					return Mono.empty();
				});
	}

	@Override
	public Flux<Franquicia> getAllFranquicias() {
		return franquiciaRepository.findAll();
	}

	@Override
	public Flux<Sucursal> getAllSucursales() {
		return sucursalRepository.findAll();
	}

	@Override
	public Flux<Producto> getAllProductos() {
		return productoRepository.findAll();
	}
	
	public Flux<ProductoMaxSucursales> getProductoMaxSucursales(Long franquiciaId) {
        String sql = String.format("select su.id, su.nombre, MAX(pr.cantidad) "
        		+ "from sucursal su "
        		+ "join franquicia fr "
        		+ "on fr.id=su.id_franquicia "
        		+ "join producto pr "
        		+ "on su.id=pr.id_sucursal "
        		+ "where fr.id=%d "
        		+ "group by su.id, su.nombre "
        		+ "order by su.id", franquiciaId);

        return databaseClient.sql(sql)
            .map((row, metadata) -> new ProductoMaxSucursales(
                row.get("id", Long.class),
                row.get("nombre", String.class),
                row.get("max", Long.class)
            ))
            .all();
    }

	@Override
	public Mono<FranquiciaSucursales> getAllFranquiciaSucursales(Long franquiciaId) {
		return franquiciaRepository.findById(franquiciaId).flatMap(franq -> sucursalRepository.findAllById(franquiciaId)
				.collectList().map(suc -> new FranquiciaSucursales(franq, suc)));
	}
}
