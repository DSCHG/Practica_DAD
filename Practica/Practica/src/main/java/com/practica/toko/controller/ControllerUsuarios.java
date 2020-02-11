package com.practica.toko.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.practica.toko.model.Usuario;
import com.practica.toko.repositorios.UserRepository;

@Controller
public class ControllerUsuarios {

	@Autowired
	private UserRepository usuarios;
	
	@RequestMapping("/form")
	public String recogerDatosForm(Model model,@RequestParam String name,@RequestParam String email,@RequestParam String pass) {
		usuarios.save(new Usuario(name,email,pass));
		List<Usuario> listaUsers = usuarios.findAll();
		for (Usuario u : listaUsers) {
			model.addAttribute("users", listaUsers);
			model.addAttribute("id", u.getId());
			model.addAttribute("nombre", u.getNombre());
			model.addAttribute("email", u.getEmail());
			model.addAttribute("password", u.getPassword());
		}
		return "crud";
	}
}
