package com.practica.toko;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practica.toko.model.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Integer> {

}
