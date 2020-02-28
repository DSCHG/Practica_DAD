package com.practica.toko.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter{
	
    
    @Autowired(required=true)
    public RepositorioAutenticacionUsuarios repositorioAutenticacionUsuarios;
    
	@Override
	protected void configure (HttpSecurity http) throws Exception{
		
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/CRUD").permitAll();
		http.authorizeRequests().antMatchers("/index").permitAll();
		http.authorizeRequests().antMatchers("/carro").permitAll();
		http.authorizeRequests().antMatchers("/addCarro").permitAll();
		http.authorizeRequests().antMatchers("/borrarArticulo").permitAll();
		http.authorizeRequests().antMatchers("/verProducto").permitAll();
		http.authorizeRequests().antMatchers("/formbusqueda").permitAll();
		http.authorizeRequests().antMatchers("/contacto").permitAll();
		http.authorizeRequests().antMatchers("/cabecera").permitAll();
		http.authorizeRequests().antMatchers("/pie").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/loginerror").permitAll();
		http.authorizeRequests().antMatchers("/css/**").permitAll();
		http.authorizeRequests().antMatchers("/img/**").permitAll();
		http.authorizeRequests().antMatchers("/webjars/**").permitAll();
		
		//privadas
		http.authorizeRequests().antMatchers("/mostrarPedidos").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/crudproveedor").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/crudproducto").hasAnyRole("ADMIN");
		http.authorizeRequests().anyRequest().authenticated();
		
		//Login form
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("username");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/index");
		http.formLogin().failureUrl("/loginerror");
		
		//Logout
		http.logout().logoutUrl("/index");
		http.logout().logoutSuccessUrl("/index");
		
	}

	 @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 	auth.inMemoryAuthentication().withUser("toko").password("toko").roles("ADMIN");
		 	auth.inMemoryAuthentication().withUser("eva").password("eva").roles("USER");
		 	auth.inMemoryAuthentication().withUser("javi").password("javi").roles("USER");
		 	auth.inMemoryAuthentication().withUser("darwin").password("darwin").roles("USER");
	        auth.authenticationProvider(repositorioAutenticacionUsuarios);
	    }
	 
}
