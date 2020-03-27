package com.practica.toko.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.practica.toko.model.Producto;
import com.practica.toko.repositorios.ProductoRepository;

@Controller
public class ControllerBusquedas {
	@Autowired
	private ProductoRepository productos;
	
	
	@RequestMapping("/formbusqueda")
	public String mostrarbusquedas(Model model,@RequestParam (value= "search") String search) {

			List<Producto> listaproductos = productos.findByNombre(search);
			model.addAttribute("producto", listaproductos);
			for (Producto u : listaproductos) {
				model.addAttribute("id", u.getId());
				model.addAttribute("nombre", u.getNombre());
				model.addAttribute("precio", u.getPrecio());
			}
		return "crudbusqueda";
	}
	
}
