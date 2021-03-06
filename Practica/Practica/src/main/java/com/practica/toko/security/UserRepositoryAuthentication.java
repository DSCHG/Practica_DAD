package com.practica.toko.security;

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

import com.practica.toko.repositorios.*;
import com.practica.toko.model.*;
import java.util.List;
import java.util.ArrayList;

@Component
public class UserRepositoryAuthentication implements AuthenticationProvider {
	@Autowired
	private UserRepository Usuarios;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Usuario user= Usuarios.findByNombre(authentication.getName());
		
		if(user==null) {
			throw new BadCredentialsException("User not found");
		}
		System.out.println(user.getNombre()+" "+user.getPassword());
		String password=(String)authentication.getCredentials();
		System.out.println(password);
		if(!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
			throw new BadCredentialsException("Wrong Password");
		}
		System.out.println("COntraseña bien");
		
		List<GrantedAuthority> roles=new ArrayList<>();
		for(String Rol:user.getRolesUser()) {
			roles.add(new SimpleGrantedAuthority(Rol));
		}
		System.out.println("Roles bien");
		System.out.println(roles);
		return new UsernamePasswordAuthenticationToken(user.getNombre(), user.getPassword(), roles);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

}
