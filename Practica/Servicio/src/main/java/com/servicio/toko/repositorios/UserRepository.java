package com.servicio.toko.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicio.toko.model.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Integer> {
	Usuario findByNombre(String nombre);
}
