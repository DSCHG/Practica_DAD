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
public class ControllerProducto {
	@Autowired
	private ProductoRepository productos;
	
	@RequestMapping("/productform")
	public String recogerDatosForm(Model model,@RequestParam String name,@RequestParam double precio) {
		productos.save(new Producto(name,precio));
		List<Producto> listaproductos = productos.findAll();
		for (Producto u : listaproductos) {
			model.addAttribute("producto", listaproductos);
			model.addAttribute("id", u.getId());
			model.addAttribute("nombre", u.getNombre());
			model.addAttribute("precio", u.getPrecio());
		}
		return "crudproducto";
	}
}