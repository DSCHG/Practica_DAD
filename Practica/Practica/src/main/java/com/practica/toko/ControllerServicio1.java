package com.practica.toko;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.practica.toko.model.Producto;
import com.practica.toko.model.Proveedor;

import com.practica.toko.repositorios.*;

@Controller
public class ControllerServicio1 {
	
	@Autowired
	private ProductoRepository productos;
	@Autowired
	private ProveedorRepository proveedores;
	
	@RequestMapping("/")
	public String getVista(Model model) {
		model.addAttribute("nombre", "TOKO");
		return "index";
	}

	@RequestMapping("/CRUD")
	public String mostrarFormulario(Model model) {
		return "crud";
	}

	@RequestMapping("/formproveedor")
	public String mostrarFormularioproveedores(Model model) {
		return "crudproveedor";
	}
	
	@RequestMapping("/formproducto")
	public String mostrarFormularioproductos(Model model) {
		List<Proveedor> aux=proveedores.findAll();
		for(Proveedor x:aux) {
			model.addAttribute("proveedor", aux);
			model.addAttribute("idProv", x.getId());
			model.addAttribute("NombreProv", x.getNombre());
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
	
	

}
