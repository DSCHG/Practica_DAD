package com.practica.toko.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ConfiguracionDeSeguridad extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//super.configure(http);
		
		// paginas publicas
		
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/loginerror").permitAll();
		http.authorizeRequests().antMatchers("/logout").permitAll();
		http.authorizeRequests().antMatchers("/css/**").permitAll();
		http.authorizeRequests().antMatchers("/img/**").permitAll();
		http.authorizeRequests().antMatchers("/webjars/**").permitAll();
		// paginas privadas
		http.authorizeRequests().anyRequest().authenticated();
		
		// configuracion del formulario de login
		
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("name");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/");
		http.formLogin().failureUrl("/loginerror");
		
		
		// configuracion del logout
		
		http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/");
		
		// deshabilitar el CSRF de momento
		http.csrf().disable();
	}
	/*@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		UserDetails user = (UserDetails) User.withUsername("Darwin").password(encoder.encode("12345")).roles("USER").build();
		return new InMemoryUserDetailsManager(user);
	}*/

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//super.configure(auth);
		
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.inMemoryAuthentication().withUser("user").password(encoder.encode("pass")).roles("USER");
		auth.inMemoryAuthentication().withUser("admin").password(encoder.encode("1234")).roles("ADMIN");
	}
	
	

	
}
