package com.practica.toko.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practica.toko.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{

	List<Producto> findByNombre(String nombre);
	//@query("Select * from Producto,Proveedor where proveedor_id=id")

}
