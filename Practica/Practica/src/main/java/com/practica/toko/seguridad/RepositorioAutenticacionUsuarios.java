package com.practica.toko.seguridad;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.practica.toko.model.Usuario;
import com.practica.toko.repositorios.UserRepository;

@Component
public class RepositorioAutenticacionUsuarios implements AuthenticationProvider{

	@Autowired 
	private UserRepository userrepos;

	@Autowired
	private UsuarioComponent  userComponent;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String nombreusuario = authentication.getName();
		String password=(String) authentication.getCredentials();
		
		Usuario usu= userrepos.findByNombre(nombreusuario);
		
		if(usu==null) {
			throw new BadCredentialsException("Usuario no encontrado, o la contraseña vaya usted a saber");
		}
		
		if(!new BCryptPasswordEncoder().matches(password, usu.getPassword())) {
			throw new BadCredentialsException("Error de autenticacion");
		}else {
			userComponent.setLoggedUser(usu);
		    
			List<GrantedAuthority> roles = new ArrayList<>();
			for (String role: usu.getRoles()) {
				roles.add(new SimpleGrantedAuthority(role));
				
			}
			
			return new UsernamePasswordAuthenticationToken(nombreusuario,password,roles);
						
		}
		
	}

	@Override
	public boolean supports(Class<?> authenticationObject) {
		
		return true;
		
	}
	
	
}
