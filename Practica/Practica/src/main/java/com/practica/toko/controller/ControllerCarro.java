package com.practica.toko.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.practica.toko.model.Carro;
import com.practica.toko.model.Producto;
import com.practica.toko.repositorios.CarroRepository;
import com.practica.toko.repositorios.ProductoRepository;

@Controller
public class ControllerCarro {
	
	@Autowired
	private CarroRepository carrodao;
	@Autowired
	private ProductoRepository productodao;
	
	private Carro carrito;
	private Producto producto;

	@RequestMapping("/carro")
	public String mostrarCarro(Model model) {	
		return "Carrito";
	}
	
	@RequestMapping("/formalizarPedido")
	public String formalizarPedido() {
		if(carrito != null) {
			carrodao.save(carrito);
		}
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
	
	@RequestMapping("/addCarro")
	public String addProducto(Model model,@RequestParam(name = "id") String id) {
		if(carrito == null) {
			carrito = new Carro();
		}		
		producto = productodao.findById(Integer.parseInt(id)).get();
		if(producto != null) {
			carrito.getCarrito().add(producto);
		}
		model.addAttribute("productos", carrito.getCarrito());
		for(Producto p : carrito.getCarrito()) {
			System.out.println(p.getNombre());
			model.addAttribute("id", p.getId());
			model.addAttribute("nombre", p.getNombre());
			model.addAttribute("precio", p.getPrecio());
			
		}
		
		
		return "Carrito";
	}
}
