package com.practica.toko.repositorios;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.practica.toko.model.Proveedor;

@CacheConfig(cacheNames = "proveedores")
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer>{
	
	@CacheEvict(allEntries = true)
	Proveedor save(Proveedor proveedor);
	
	@Cacheable
	List<Proveedor> findAll();

}
