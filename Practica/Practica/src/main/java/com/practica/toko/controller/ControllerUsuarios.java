package com.practica.toko.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.practica.toko.model.*;
import com.practica.toko.repositorios.*;

@Controller
public class ControllerUsuarios {

	@Autowired
	private UserRepository usuarios;
	
	@RequestMapping("/form")
	public String recogerDatosForm(Model model,@RequestParam String name,@RequestParam String email,@RequestParam String pass,HttpSession session) {
							
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if(usuario != null) {
			usuario.setNombre(name);
			usuario.setEmail(email);
			usuario.setPassword(pass);
			usuarios.save(usuario);
			List<Usuario> listaUsers = usuarios.findAll();
			for (Usuario u : listaUsers) {
				if(!u.getNombre().isEmpty() && !u.getEmail().isEmpty() && !u.getPassword().isEmpty()) {
					model.addAttribute("users", listaUsers);
					model.addAttribute("id", u.getId());
					model.addAttribute("name", u.getNombre());
					model.addAttribute("email", u.getEmail());
					model.addAttribute("password", u.getPassword());
				}else {
					model.addAttribute("users", listaUsers);
					model.addAttribute("id", u.getId());
				}
				/*System.out.println(u.getNombre());
				model.addAttribute("users", listaUsers);
				model.addAttribute("id", u.getId());
				model.addAttribute("name", u.getNombre());
				model.addAttribute("email", u.getEmail());
				model.addAttribute("password", u.getPassword());*/
			}
		}else {
			System.out.println("no hay datos");
		}
		
		
		return "crud";
	}
	
}
