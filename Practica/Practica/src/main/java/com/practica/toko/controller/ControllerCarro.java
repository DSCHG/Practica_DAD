package com.practica.toko.controller;


import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.practica.toko.model.*;
import com.practica.toko.repositorios.*;

@Controller
public class ControllerCarro {


	@Autowired
	private ProductoRepository productodao;
	@Autowired
	private UserRepository Usuarios;
	@Autowired
	private ProductoRepository productos;
	
	private Producto producto;
	private Usuario user;

	@RequestMapping("/carro")
	public String mostrarCarro(Model model,HttpSession session) {
		user = (Usuario) session.getAttribute("usuario");
		if(user != null) {
			int producto=0;
			if(user.getCarrito().getListaProductos().isEmpty()){
				producto=0;
			}else {
				producto=1;
			}
			model.addAttribute("haydatos",producto );
			model.addAttribute("productos", user.getCarrito().getListaProductos());
			for(Producto p : user.getCarrito().getListaProductos()) {
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
		if(user != null) {
			Producto borrar=new Producto();
			for(Producto p : user.getCarrito().getListaProductos()) {
				if(p.getId() == Integer.parseInt(id)) {
					borrar=p;
				}
			}	
			user.getCarrito().getListaProductos().remove(borrar);
			model.addAttribute("haydatos",user.getCarrito().getListaProductos().size());
			model.addAttribute("productos", user.getCarrito().getListaProductos());
			for(Producto p : user.getCarrito().getListaProductos()) {
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
		if(user != null) {
			producto = productodao.findById(Integer.parseInt(id)).get();
			user.getCarrito().getListaProductos().add(producto);
			int x=0;
			if(user.getCarrito().getListaProductos().isEmpty()){
				x=0;
			}else {
				x=1;
			}
			
			//Usuarios.save(user);
			model.addAttribute("haydatos",x );
			model.addAttribute("productos", user.getCarrito().getListaProductos());
			for(Producto p : user.getCarrito().getListaProductos()) {
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
