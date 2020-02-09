package com.practica.Z.entities;

import javax.persistence.*;

import org.springframework.stereotype.Repository;

@Entity
public class Usuario {
	// Atributos

		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		int id;
		
		String nombre;
		String email;
		String password;
		
		//constructor
		
		public Usuario() {}
		
		public Usuario(int id,String nombre, String email,String password) {
			this.id = id;
			this.nombre = nombre;
			this.email = email;
			this.password = password;
		}
		
		// Getter and Setter
		public int getId() {
			return id;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getEmail() {
			return email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		

}
