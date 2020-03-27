package com.practica.toko.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControllerLogin {
	
	@GetMapping("/login")
	public String mostrarLogin(Model model, HttpServletRequest request) {
		CsrfToken token=(CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		model.addAttribute("ADMIN", request.isUserInRole("ADMIN"));
		return "login";
	}
	
	@GetMapping("/loginerror")
	public String mostrarError(Model model, HttpServletRequest request) {
		model.addAttribute("ADMIN", request.isUserInRole("ADMIN"));
		return "loginerror";
	}

	@GetMapping("/producto")
	public String proveedor(Model model, HttpServletRequest request) {
		model.addAttribute("ADMIN", request.isUserInRole("ADMIN"));
		return "crudproducto";
	}
	
	@GetMapping("/proveedor")
	public String administrador(Model model, HttpServletRequest request) {
		model.addAttribute("ADMIN", request.isUserInRole("ADMIN"));
		return "crudproveedor";
	}

}
