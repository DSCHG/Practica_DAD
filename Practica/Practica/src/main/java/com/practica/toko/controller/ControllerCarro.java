package com.practica.toko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.practica.toko.model.Carro;
import com.practica.toko.repositorios.CarroRepository;

@Controller
public class ControllerCarro {
	
	@Autowired
	private CarroRepository carrodao;

	@RequestMapping("/carro")
	public String mostrarCarro(Model model) {	
		return "Carrito";
	}
	
	@RequestMapping("/formalizarPedido")
	public String formalizarPedido() {
		return "Carrito";
	}
	@RequestMapping("/borrarArticulo")
	public String borrarItem(Model model,@RequestParam(name = "id") String id) {
		System.out.println(id);
		return "Carrito";
	}
	
	@RequestMapping("/verProducto")
	public String verProducto(@RequestParam(name = "id") String id) {
		return "vistaProducto";
	}
}
