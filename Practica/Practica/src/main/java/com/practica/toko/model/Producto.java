package com.practica.toko.model;

import javax.persistence.*;

@Entity
public class Producto {
	// Atributos
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		int id;
		
		String nombre;
		double precio;
		
		// Getters and Setters
		
		public int getId() {
			return id;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public double getPrecio() {
			return precio;
		}
		public void setPrecio(double precio) {
			this.precio = precio;
		}

}
