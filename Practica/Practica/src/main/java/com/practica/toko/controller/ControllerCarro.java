package com.practica.toko.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerCarro {

	@RequestMapping("/carro")
	public String mostrarCarro(Model model) {
		return "carro";
	}
}
