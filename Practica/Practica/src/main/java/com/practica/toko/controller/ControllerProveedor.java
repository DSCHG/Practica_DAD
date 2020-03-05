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
	public String recogerDatosForm(Model model,@RequestParam String name,@RequestParam String producto, @RequestParam String Precio) {
		
		Proveedor prob=new Proveedor(name);
		proveedores.save(prob);
		if(!(producto.isEmpty() && Precio.isEmpty())){
			Producto auxiliar=new Producto(producto,Double.valueOf(Precio));
			auxiliar.setProveedor(prob);
			productos.save(auxiliar);
			prob.getListaProductos().add(auxiliar);
		}else if(producto.isEmpty() && Precio.isEmpty()) {
			
		}else {
			return "crudproveedor";
		}
		List<Proveedor> listaproveedores = proveedores.findAll();
		
		prob=listaproveedores.get(0);
		productos.findById(3);
		for (Proveedor u : listaproveedores) {
			model.addAttribute("proveedor", listaproveedores);
			model.addAttribute("id", u.getId());
			model.addAttribute("nombre", u.getNombre());
			for(Producto p: u.getListaProductos()) {
				System.out.println(p.getNombre());
			}
			System.out.println();
			
		}
		return "crudproveedor";
	}
	
}
