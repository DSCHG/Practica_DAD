package com.practica.toko.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practica.toko.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{

}
