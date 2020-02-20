package com.practica.toko.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

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
	private CarroRepository carrodao;
	@Autowired
	private ProductoRepository productodao;
	@Autowired
	private UserRepository Usuarios;
	@Autowired
	private PedidoRepository Pedidos;
	@Autowired
	private ProductoRepository productos;
	
	private Producto producto;
	private Usuario user;
	private Carro carrito;

	@RequestMapping("/carro")
	public String mostrarCarro(Model model,HttpSession session) {
		user = (Usuario) session.getAttribute("usuario");
		carrito = (Carro) session.getAttribute("carrito");
		if(user != null && carrito != null) {
			int producto=0;
			if(carrito.getListaProductos().isEmpty()){
				producto=0;
			}else {
				producto=1;
			}
			model.addAttribute("haydatos",producto );
			model.addAttribute("productos", carrito.getListaProductos());
			for(Producto p : carrito.getListaProductos()) {
				model.addAttribute("id", p.getId());
				model.addAttribute("nombre", p.getNombre());
				model.addAttribute("precio", p.getPrecio());
				
			}
		}
		
		return "Carrito";
	}
	
	@RequestMapping("/formalizarPedido")
	public String formalizarPedido(Model model,HttpSession session) {
		
		user = (Usuario) session.getAttribute("usuario");
		if(user != null) {			
			Pedido p = new Pedido();
			p.getProducto().addAll(user.getCarrito().getListaProductos());	
			user.getCarrito().getListaProductos().clear();
			user.getListaPedidos().add(p);
			System.out.println(user.getId());
			Usuarios.delete(user);
			user=Usuarios.save(user);
			
			session.setAttribute("usuario", user);	
			
			model.addAttribute("haydatos",user.getCarrito().getListaProductos().size());
			model.addAttribute("productos", user.getCarrito().getListaProductos());
			for(Producto pr : user.getCarrito().getListaProductos()) {
				model.addAttribute("id", pr.getId());
				model.addAttribute("nombre", pr.getNombre());
				model.addAttribute("precio", pr.getPrecio());
				
			}
		}		
		return "Carrito";
	}
	@RequestMapping("/borrarArticulo")
	public String borrarItem(Model model,@RequestParam(name = "id") String id,HttpSession session) {
		user = (Usuario) session.getAttribute("usuario");
		carrito = (Carro) session.getAttribute("carrito");
		if(user != null && carrito != null) {
			Producto borrar=new Producto();
			for(Producto p : carrito.getListaProductos()) {
				if(p.getId() == Integer.parseInt(id)) {
					borrar=p;
				}
			}	
			carrito.getListaProductos().remove(borrar);
			model.addAttribute("haydatos",carrito.getListaProductos().size());
			model.addAttribute("productos", carrito.getListaProductos());
			for(Producto p : carrito.getListaProductos()) {
				model.addAttribute("id", p.getId());
				model.addAttribute("nombre", p.getNombre());
				model.addAttribute("precio", p.getPrecio());
				
			}
		}
		session.setAttribute("usuario", user);	
			
		
		return "Carrito";
	}
	
	@RequestMapping("/verProducto")
	public String verProducto(Model model,@RequestParam(name = "id") String id,HttpSession session) {
		user = (Usuario) session.getAttribute("usuario");
		if(user != null) {
			Optional<Producto> p=productos.findById(Integer.parseInt(id));
			Producto pr;
			if(p.isPresent()) {
			pr=p.get();
			model.addAttribute("id", pr.getId());
			model.addAttribute("nombre", pr.getNombre());
			model.addAttribute("precio", pr.getPrecio());
			}
		}		
		return "vistaProducto";
	}
	
	@RequestMapping("/addCarro")
	public String addProducto(Model model,@RequestParam(name = "id") String id,HttpSession session) {	
		user = (Usuario) session.getAttribute("usuario");
		carrito = (Carro) session.getAttribute("carrito");
		if(user != null && carrito != null) {
			producto = productodao.findById(Integer.parseInt(id)).get();
			carrito.getListaProductos().add(producto);
			int x=0;
			if(carrito.getListaProductos().isEmpty()){
				x=0;
			}else {
				x=1;
			}
			
			//Usuarios.save(user);
			model.addAttribute("haydatos",x );
			model.addAttribute("productos", carrito.getListaProductos());
			for(Producto p : carrito.getListaProductos()) {
				model.addAttribute("id", p.getId());
				model.addAttribute("nombre", p.getNombre());
				model.addAttribute("precio", p.getPrecio());
				
			}
		}
		
		return "Carrito";
	}
	
	/*public void cargarDatos(Model model,HttpSession session) {
		
		model.addAttribute("haydatos",user.getListaPedidos().size());
		model.addAttribute("productos", user.getCarrito().getListaProductos());
		for(Producto p : user.getCarrito().getListaProductos()) {
			model.addAttribute("id", p.getId());
			model.addAttribute("nombre", p.getNombre());
			model.addAttribute("precio", p.getPrecio());	
		}
	}*/
		
}
