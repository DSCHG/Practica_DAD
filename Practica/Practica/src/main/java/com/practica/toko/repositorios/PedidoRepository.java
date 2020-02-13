package com.practica.toko.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practica.toko.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
