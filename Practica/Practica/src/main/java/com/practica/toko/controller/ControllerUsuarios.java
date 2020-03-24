package com.practica.toko.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
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
	public String recogerDatosForm(Model model,@RequestParam String name,@RequestParam String email,@RequestParam String pass,HttpSession session, HttpServletRequest request) {
							
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if(usuario != null) {
			usuario.setNombre(name);
			usuario.setEmail(email);
			usuario.setPassword(new BCryptPasswordEncoder().encode(pass));
			usuario.getRolesUser().add("ROLE_USER");
			usuarios.save(usuario);
			List<Usuario> listaUsers = usuarios.findAll();
			for (Usuario u : listaUsers) {
				if(!(u.getNombre()==null )&& !(u.getEmail()==null) && !(u.getPassword()==null)) {
					model.addAttribute("users", listaUsers);
					model.addAttribute("id", u.getId());
					model.addAttribute("nombre", u.getNombre());
					model.addAttribute("email", u.getEmail());
					model.addAttribute("password", u.getPassword());
					CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
					model.addAttribute("token", token.getToken());
				}else {
					model.addAttribute("users", listaUsers);
					model.addAttribute("id", u.getId());
					u.setNombre("Anonimo");
					u.setEmail("Anonimo@TokoStore.com");
					u.setPassword("");
					model.addAttribute("name", "Anonimo");
					model.addAttribute("email", u.getEmail());
					model.addAttribute("password", u.getPassword());
					model.addAttribute("usuario",true);
					CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
					model.addAttribute("token", token.getToken());
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
	@RequestMapping("/formRegistro")
	public String registro(Model model,@RequestParam String name,@RequestParam String email,@RequestParam String pass,@RequestParam String roles,HttpSession session, HttpServletRequest request) {
							
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if(usuario != null) {
			usuario.setNombre(name);
			usuario.setEmail(email);
			usuario.setPassword(new BCryptPasswordEncoder().encode(pass));
			usuario.getRolesUser().add(roles);
			usuarios.save(usuario);
		}else {
			usuarios.save(new Usuario(name,email,new BCryptPasswordEncoder().encode(pass),roles));
		}
		
		
		return "login";
	}
	
}
