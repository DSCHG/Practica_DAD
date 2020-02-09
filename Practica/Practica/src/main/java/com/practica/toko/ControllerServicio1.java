package com.practica.toko;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	

}
