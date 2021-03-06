package com.servicio.toko.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Entity
@Component
@SessionScope
public class Usuario {
	// Atributos

		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private int id;
		@Column
		private String nombre;
		@Column
		private String email;
		@Column
		private String password;
		
		
		// relacion uno a muchos con los pedidos unidireccional
		@OneToMany(cascade = CascadeType.ALL)
		private List<Pedido> listaPedidos;
		
		// relacion uno a uno con los carrito bidireccional
		@OneToOne(cascade = CascadeType.ALL)
		private Carro carrito;
		
		@ElementCollection(fetch = FetchType.EAGER)
		private List<String> rolesUser=new ArrayList<>();
		
		
		//constructor
		
		public Usuario() {
			listaPedidos = new ArrayList<>();
		}
		
		public Usuario(int id,String nombre, String email,String password) {
			this.id = id;
			this.nombre = nombre;
			this.email = email;
			this.password = password;
		}
		
		public Usuario(String nombre, String email,String password) {
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

		public List<Pedido> getListaPedidos() {
			return listaPedidos;
		}

		public void setListaPedidos(List<Pedido> listaPedidos) {
			this.listaPedidos = listaPedidos;
		}

		public Carro getCarrito() {
			return carrito;
		}

		public void setCarrito(Carro carrito) {
			this.carrito = carrito;
		}

		public void setId(int id) {
			this.id = id;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public List<String> getRolesUser() {
			return rolesUser;
		}

		public void setRolesUser(List<String> rolesUser) {
			this.rolesUser = rolesUser;
		}
}
