package com.practica.toko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.practica.toko.model.*;


@Controller
public class ControllerPedido {
	
	@Autowired
	private Usuario user;
	
	@RequestMapping("/verMisPedidos")
	public String mostrarPedidos(Model model) {
		double preciototal = 0.0;
		model.addAttribute("pedidos", user.getListaPedidos());
		for(Pedido p : user.getListaPedidos()) {
			model.addAttribute("id", p.getId());
			for(Producto pro : p.getProducto()) {
				preciototal += pro.getPrecio();
			}
			model.addAttribute("precio", preciototal);
		}
		return "index";
	}

}
