package com.practica.toko.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.practica.toko.model.Carro;
import com.practica.toko.model.Pedido;
import com.practica.toko.model.Producto;
import com.practica.toko.model.Usuario;
import com.practica.toko.repositorios.CarroRepository;
import com.practica.toko.repositorios.PedidoRepository;
import com.practica.toko.repositorios.ProductoRepository;
import com.practica.toko.repositorios.UserRepository;

@Controller
public class ControllerCarro {
	@Autowired
	private Usuario user;
	@Autowired
	private CarroRepository carrodao;
	@Autowired
	private ProductoRepository productodao;
	@Autowired
	private UserRepository Usuarios;
	@Autowired
	private PedidoRepository Pedidos;
	@Autowired
	private ProductoRepository productos;
	
	private Carro carrito;
	private Producto producto;

	@RequestMapping("/carro")
	public String mostrarCarro(Model model) {	
		System.out.println(user.getNombre());
		return "Carrito";
	}
	
	@RequestMapping("/formalizarPedido")
	public String formalizarPedido() {
		Pedido p=new Pedido();
		p.setUsuario(user);
		p.setProducto(user.getCarrito().getCarrito());
		user.getListaPedidos().add(p);
		Usuarios.save(user);
		Pedidos.save(p);		
		return "Carrito";
	}
	@RequestMapping("/borrarArticulo")
	public String borrarItem(Model model,@RequestParam(name = "id") String id) {
		for(Producto p : user.getCarrito().getCarrito()) {
			if(p.getId() == Integer.parseInt(id)) {
				user.getCarrito().getCarrito().remove(p);
				return "Carrito";
			}
		}
		if(!user.getCarrito().getCarrito().isEmpty()) {
			
			model.addAttribute("productos", user.getCarrito().getCarrito());
			for(Producto p : user.getCarrito().getCarrito()) {
				model.addAttribute("id", p.getId());
				model.addAttribute("nombre", p.getNombre());
				model.addAttribute("precio", p.getPrecio());
				
			}
		}
		
		return "Carrito";
	}
	
	@RequestMapping("/verProducto")
	public String verProducto(@RequestParam(name = "id") String id) {
		return "vistaProducto";
	}
	
	@RequestMapping("/addCarro")
	public String addProducto(Model model,@RequestParam(name = "id") String id) {	
		producto = productodao.findById(Integer.parseInt(id)).get();
		user.getCarrito().getCarrito().add(producto);
		model.addAttribute("haydatos", user.getCarrito().getCarrito());
		model.addAttribute("productos", user.getCarrito().getCarrito());
		for(Producto p : user.getCarrito().getCarrito()) {
			model.addAttribute("id", p.getId());
			model.addAttribute("nombre", p.getNombre());
			model.addAttribute("precio", p.getPrecio());
			
		}
		
		
		return "Carrito";
	}
		
}
