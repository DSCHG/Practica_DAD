package com.practica.toko.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.practica.toko.model.*;
import com.practica.toko.repositorios.*;

@Controller
public class ControllerProveedor {
	@Autowired
	private ProductoRepository productos;
	@Autowired
	private ProveedorRepository proveedores;
	
	
	
	@RequestMapping("/datosform")
	public String recogerDatosForm(Model model,@RequestParam String name,@RequestParam String producto) {
		
		Proveedor prob=new Proveedor(name);
		proveedores.save(prob);
		Producto auxiliar=new Producto(producto,100);
		auxiliar.setProveedor(prob);
		productos.save(auxiliar);
		
		
		
		
		prob.getListaProductos().add(auxiliar);
		
		List<Proveedor> listaproveedores = proveedores.findAll();
		for (Proveedor u : listaproveedores) {
			model.addAttribute("proveedor", listaproveedores);
			model.addAttribute("id", u.getId());
			model.addAttribute("nombre", u.getNombre());
			if(!u.getListaProductos().isEmpty()) {
				model.addAttribute("producto", u.getListaProductos().get(0).getNombre());
			}
		}
		return "crudproveedor";
	}
}
