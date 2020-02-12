package com.practica.toko.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.practica.toko.model.Proveedor;
import com.practica.toko.repositorios.ProveedorRepository;

@Controller
public class ControllerProveedor {
	@Autowired
	private ProveedorRepository proveedores;
	
	@RequestMapping("/datosform")
	public String recogerDatosForm(Model model,@RequestParam String name,@RequestParam String prod) {
		proveedores.save(new Proveedor(name,prod));
		List<Proveedor> listaproveedores = proveedores.findAll();
		for (Proveedor u : listaproveedores) {
			model.addAttribute("users", listaproveedores);
			model.addAttribute("id", u.getId());
			model.addAttribute("nombre", u.getNombre());
			model.addAttribute("producto", u.getProducto());
		}
		return "crudproveedor";
	}
}
