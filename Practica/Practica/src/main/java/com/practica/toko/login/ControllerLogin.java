package com.practica.toko.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControllerLogin {
	
	@GetMapping("/login")
	public String mostrarLogin() {
		return "login";
	}
	
	@GetMapping("/loginerror")
	public String mostrarError() {
		return "loginerror";
	}

}
