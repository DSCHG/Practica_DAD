package com.servicio.toko.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.servicio.toko.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
