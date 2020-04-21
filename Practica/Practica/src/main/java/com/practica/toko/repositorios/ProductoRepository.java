package com.practica.toko.repositorios;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.practica.toko.model.Producto;

@CacheConfig(cacheNames = "productos")
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	
	@CacheEvict(allEntries = true)
	Producto save(Producto producto);
	
	@Cacheable
	List<Producto> findByNombre(String nombre);
	//@query("Select * from Producto,Proveedor where proveedor_id=id")
	
	@Cacheable
	List<Producto> findAll();

}
