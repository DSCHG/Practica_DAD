package com.practica.toko.seguridad;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/loginerror")
	public String loginerror() {
		return "loginerror";
	}

	@GetMapping("/InfoPedido")
	public String usuario() {
		return "InfoPedido";
	}

	@GetMapping("/producto")
	public String proveedor() {
		return "crudproducto";
	}
	
	@GetMapping("/proveedor")
	public String administrador() {
		return "crudproveedor";
	}
	
}
