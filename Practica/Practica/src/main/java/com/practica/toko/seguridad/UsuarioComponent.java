package com.practica.toko.seguridad;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.practica.toko.model.Usuario;

@Component
@SessionScope
public class UsuarioComponent {
	
	private Usuario usuario;
	
	public Usuario getLoggedUser() {
		return usuario;
	}
	
	public void setLoggedUser(Usuario usu) {
		this.usuario=usu;
	}
	
	public boolean isLoggedUser() {
		return this.usuario != null;
	}
	
}
