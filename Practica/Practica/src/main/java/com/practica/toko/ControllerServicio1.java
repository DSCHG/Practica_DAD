package com.practica.toko;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.practica.toko.model.*;
import com.practica.toko.repositorios.*;

@Controller
public class ControllerServicio1 {
	
	@Autowired
	private ProductoRepository productos;
	@Autowired
	private ProveedorRepository proveedores;
	@Autowired
	private UserRepository Usuarios;
	
	
	private Usuario user;
	private Carro carrito;
	
	@GetMapping("/")
	public String getVista(Model model,HttpSession session) {
		user = (Usuario) session.getAttribute("usuario");
		if(user == null) {
			user = new Usuario();
			carrito = new Carro();
			user.setCarrito(carrito);
			session.setAttribute("usuario", user);		
			session.setMaxInactiveInterval(-1);
		}
		//Usuarios.deleteAll();
		List<Producto> prod=productos.findAll();
		for(Producto p:prod) {
			model.addAttribute("productos", prod);
			model.addAttribute("id", p.getId());
			model.addAttribute("nombre", p.getNombre());
			model.addAttribute("precio",p.getPrecio());
			model.addAttribute("opciones", "?");
		}
		
		return "index";
	}
	@GetMapping("/Welcome")
	public String Welcome(Model model,HttpSession session) {
		user = (Usuario) session.getAttribute("usuario");
		if(user == null) {
			user = new Usuario();
			carrito = new Carro();
			user.setCarrito(carrito);
			//user=Usuarios.save(user);
			session.setAttribute("usuario", user);			
		}
		List<Producto> prod=productos.findAll();
		for(Producto p:prod) {
			model.addAttribute("productos", prod);
			model.addAttribute("id", p.getId());
			model.addAttribute("nombre", p.getNombre());
			model.addAttribute("precio",p.getPrecio());
			model.addAttribute("opciones", "?");
		}
		
		return "index";
	}

	@RequestMapping("/CRUD")
	public String mostrarFormulario(Model model) {
		return "crud";
	}
	@RequestMapping("/Resgistro")
	public String Resgistro(Model model) {
		return "registroForm";
	}

	@RequestMapping("/formproveedor")
	public String mostrarFormularioproveedores(Model model, HttpServletRequest request) {
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		return "crudproveedor";
	}
	@RequestMapping("/contacto")
	public String mostrarcontacto(Model model) {
		return "contacto";
	}
	
	@RequestMapping("/formproducto")
	public String mostrarFormularioproductos(Model model, HttpServletRequest request) {
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		System.out.println( request.isUserInRole("ADMIN"));
		List<Proveedor> aux=proveedores.findAll();
		for(Proveedor x:aux) {
			model.addAttribute("proveedor", aux);
			model.addAttribute("id", x.getId());
			model.addAttribute("nombre", x.getNombre());
		}
		
		List<Producto> listaproductos = productos.findAll();
		model.addAttribute("producto", listaproductos);
		for (Producto u : listaproductos) {
			model.addAttribute("id", u.getId());
			model.addAttribute("nombre", u.getNombre());
			model.addAttribute("precio", u.getPrecio());
			
		}
		return "crudproducto";
	}
	
	@RequestMapping("/end")
	public String guardar(HttpSession session,HttpServletRequest request) {
		if(request.getUserPrincipal()!=null) {
			user=Usuarios.findByNombre(request.getUserPrincipal().getName());
			Usuarios.save(user);
		}else {
			user = (Usuario) session.getAttribute("usuario");
			Usuarios.save(user);
		}
		
		return "index";
	}
}
