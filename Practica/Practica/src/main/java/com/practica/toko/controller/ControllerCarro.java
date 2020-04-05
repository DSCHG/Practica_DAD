package com.practica.toko.controller;


import java.util.Collections;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.practica.toko.model.*;
import com.practica.toko.repositorios.*;

@Controller
public class ControllerCarro {


	@Autowired
	private ProductoRepository productodao;
	@Autowired
	private UserRepository Usuarios;
	@Autowired
	private PedidoRepository repoPedido;
	
	private Producto producto;
	private Usuario user;
	private Usuario NoReg=null;

	@RequestMapping("/carro")
	public String mostrarCarro(Model model,HttpSession session, HttpServletRequest request) {
		if(request.getUserPrincipal()!=null) {
			user=Usuarios.findByNombre(request.getUserPrincipal().getName());
			if(NoReg!=null) {
				Usuario aux = NoReg;
				user=Usuarios.findByNombre(request.getUserPrincipal().getName());
				user.getCarrito().getListaProductos().addAll(aux.getCarrito().getListaProductos());
				aux.getCarrito().getListaProductos().clear();
				NoReg=null;
				Usuarios.save(user);
			}
		}else {
			NoReg = (Usuario) session.getAttribute("usuario");
			user=NoReg;
		}
		System.out.println(user.getNombre());
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
	
	@GetMapping("/formalizarPedido")
	public String formalizarPedido(Model model,HttpSession session, HttpServletRequest request) {
		if(request.getUserPrincipal()!=null) {
			user=Usuarios.findByNombre(request.getUserPrincipal().getName());
			if(NoReg!=null) {
				Usuario aux = NoReg;
				user=Usuarios.findByNombre(request.getUserPrincipal().getName());
				user.getCarrito().getListaProductos().addAll(aux.getCarrito().getListaProductos());
				aux.getCarrito().getListaProductos().clear();
				NoReg=null;
			}
		}else {
			user = (Usuario) session.getAttribute("usuario");
		}
		if(user != null) {			
			Pedido p = new Pedido();
			p.getProducto().addAll(user.getCarrito().getListaProductos());	
			user.getCarrito().getListaProductos().clear();
			user.getListaPedidos().add(p);
			System.out.println(user.getId());
			if(request.getUserPrincipal()==null) {
				session.setAttribute("usuario", user);
				NoReg=user;
			}else {
				user=Usuarios.save(user);
			}
			
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
	public String borrarItem(Model model,@RequestParam(name = "id") String id,HttpSession session, HttpServletRequest request) {
		if(request.getUserPrincipal()!=null) {
			user=Usuarios.findByNombre(request.getUserPrincipal().getName());
		}else {
			user = (Usuario) session.getAttribute("usuario");
		}
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
		if(request.getUserPrincipal()!=null) {
			Usuarios.save(user);
		}else {
			session.setAttribute("usuario", user);	
		}
		
			
		
		return "Carrito";
	}
	
	@RequestMapping("/verProducto")
	public String verProducto(Model model,@RequestParam(name = "id") String id,HttpSession session, HttpServletRequest request) {
		if(user != null) {
			Optional<Producto> p=productodao.findById(Integer.parseInt(id));
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
	public String addProducto(Model model,@RequestParam(name = "id") String id,HttpSession session, HttpServletRequest request) {
		if(request.getUserPrincipal()!=null) {
			user=Usuarios.findByNombre(request.getUserPrincipal().getName());
			if(NoReg!=null) {
				Usuario aux = NoReg;
				user=Usuarios.findByNombre(request.getUserPrincipal().getName());
				user.getCarrito().getListaProductos().addAll(aux.getCarrito().getListaProductos());
				aux.getCarrito().getListaProductos().clear();
				NoReg=null;
			}
		}else {
			user = (Usuario) session.getAttribute("usuario");
		}
		if(user != null) {
			producto = productodao.findById(Integer.parseInt(id)).get();
			user.getCarrito().getListaProductos().add(producto);
			int x=0;
			if(user.getCarrito().getListaProductos().isEmpty()){
				x=0;
			}else {
				x=1;
			}
			if(request.getUserPrincipal()!=null) {
				Usuarios.save(user);
			}else {
				session.setAttribute("usuario", user);
				session.setAttribute("usuario", user);
				NoReg=user;
			}
			
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
	
	@GetMapping("/serviciointerno")
	public String factura(@RequestParam (value="id") int id,HttpServletRequest request){
		Optional<Pedido> pedido = repoPedido.findById(id);
		pedido.isPresent();
		Usuario user = Usuarios.findByNombre(request.getUserPrincipal().getName());
		String url="http://localhost:8000/enviarFactura/"+id+"/"+user.getId();
		RestTemplate conexion=new RestTemplate();
		ObjectNode aux=conexion.getForObject(url,ObjectNode.class);
		return "correoenviado";
	}
		
}
