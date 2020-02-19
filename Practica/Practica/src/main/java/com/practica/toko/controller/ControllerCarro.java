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
		int producto=0;
		if(user.getCarrito().getCarrito().isEmpty()){
			producto=0;
		}else {
			producto=1;
		}
		model.addAttribute("haydatos",producto );
		model.addAttribute("productos", user.getCarrito().getCarrito());
		for(Producto p : user.getCarrito().getCarrito()) {
			model.addAttribute("id", p.getId());
			model.addAttribute("nombre", p.getNombre());
			model.addAttribute("precio", p.getPrecio());
			
		}
		return "Carrito";
	}
	
	@RequestMapping("/formalizarPedido")
	public String formalizarPedido(Model model) {
		Pedido p=new Pedido();
		p.setUsuario(user);
		p.getProducto().addAll(user.getCarrito().getCarrito());
		user.getListaPedidos().add(p);
		//Pedidos.save(p);
		user.getCarrito().getCarrito().clear();
		model.addAttribute("haydatos",user.getCarrito().getCarrito().size());
		model.addAttribute("productos", user.getCarrito().getCarrito());
		for(Producto pr : user.getCarrito().getCarrito()) {
			model.addAttribute("id", pr.getId());
			model.addAttribute("nombre", pr.getNombre());
			model.addAttribute("precio", pr.getPrecio());
			
		}
		//Usuarios.save(user);
		//Pedidos.save(p);		
		return "Carrito";
	}
	@RequestMapping("/borrarArticulo")
	public String borrarItem(Model model,@RequestParam(name = "id") String id) {
		Producto borrar=new Producto();
		for(Producto p : user.getCarrito().getCarrito()) {
			if(p.getId() == Integer.parseInt(id)) {
				borrar=p;
			}
		}	
		user.getCarrito().getCarrito().remove(borrar);
		model.addAttribute("haydatos",user.getCarrito().getCarrito().size());
		model.addAttribute("productos", user.getCarrito().getCarrito());
		for(Producto p : user.getCarrito().getCarrito()) {
			model.addAttribute("id", p.getId());
			model.addAttribute("nombre", p.getNombre());
			model.addAttribute("precio", p.getPrecio());
			
		}
			
		
		return "Carrito";
	}
	
	@RequestMapping("/verProducto")
	public String verProducto(Model model,@RequestParam(name = "id") String id) {
		Optional<Producto> p=productos.findById(Integer.valueOf(id));
		Producto pr;
		if(p.isPresent()) {
		pr=p.get();
		model.addAttribute("id", pr.getId());
		model.addAttribute("nombre", pr.getNombre());
		model.addAttribute("precio", pr.getPrecio());
		}
		return "vistaProducto";
	}
	
	@RequestMapping("/addCarro")
	public String addProducto(Model model,@RequestParam(name = "id") String id) {	
		producto = productodao.findById(Integer.parseInt(id)).get();
		user.getCarrito().getCarrito().add(producto);
		int x=0;
		if(user.getCarrito().getCarrito().isEmpty()){
			x=0;
		}else {
			x=1;
		}
		model.addAttribute("haydatos",x );
		model.addAttribute("productos", user.getCarrito().getCarrito());
		for(Producto p : user.getCarrito().getCarrito()) {
			model.addAttribute("id", p.getId());
			model.addAttribute("nombre", p.getNombre());
			model.addAttribute("precio", p.getPrecio());
			
		}
		
		
		return "Carrito";
	}
	
	public void cargarDatos(Model model) {
		model.addAttribute("haydatos",user.getListaPedidos().size());
		model.addAttribute("productos", user.getCarrito().getCarrito());
		for(Producto p : user.getCarrito().getCarrito()) {
			model.addAttribute("id", p.getId());
			model.addAttribute("nombre", p.getNombre());
			model.addAttribute("precio", p.getPrecio());	
		}
	}
		
}
