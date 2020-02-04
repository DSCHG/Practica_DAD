package com.practica.toko;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerServicio1 {
	
	@RequestMapping("/index")
	public String getVista(Model model) {
		model.addAttribute("Nombre", "TOKO");
		return "index";
	}
	

}
