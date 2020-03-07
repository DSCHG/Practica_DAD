package com.practica.toko.login;
import org.springframework.web.bind.annotation.RestController;

import com.practica.toko.model.Usuario;
import com.practica.toko.security.UsuarioComponent;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class controladorDeLogin {
	private static final Logger log = LoggerFactory.getLogger(controladorDeLogin.class);

	
	@Autowired
	private UsuarioComponent usercomponent;
	
	@RequestMapping("/login")
	public ResponseEntity<Usuario> logIn(){
		if(!usercomponent.isLoggedUser()) {
			log.info("No esta registrado");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			
		}else {
			Usuario loggedUser = usercomponent.getLoggedUser();
			log.info("Registrado como " + loggedUser.getNombre());
			return new ResponseEntity<>(loggedUser, HttpStatus.OK);
			
		}
	}
	
	
	@RequestMapping("/logout")
	public ResponseEntity<Boolean> logout(HttpSession sesion){
		if(!usercomponent.isLoggedUser()) {
			log.info("No hay usuario autenticado");
			return new ResponseEntity<> (HttpStatus.UNAUTHORIZED);
			
		}else {
			sesion.invalidate();
			log.info("Has salido de la sesion");
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
	}
	
}
