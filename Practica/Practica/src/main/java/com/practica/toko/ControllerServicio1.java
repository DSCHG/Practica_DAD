package com.practica.toko;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.practica.toko.model.Usuario;

@Controller
public class ControllerServicio1 {
	
	
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
		return "crudproducto";
	}
	
	

}
