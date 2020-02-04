package com.prueba.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerTest {

	@RequestMapping("/test")
	public String getVista(Model model) {
		model.addAttribute("nombre", "Mundo");
		return "vista";
	}
}
