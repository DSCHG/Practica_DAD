package com.practica.toko.repositorios;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.practica.toko.model.Producto;
import com.practica.toko.model.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Integer> {
	Usuario findByNombre(String nombre);
	//Usuario findByrolesUser (String nombre);
	//Usuario findOneByNombre(String nombre);
}
