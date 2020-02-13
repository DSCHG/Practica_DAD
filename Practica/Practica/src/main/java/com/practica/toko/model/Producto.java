package com.practica.toko.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Producto {
	// Atributos
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int id;
		
		private String nombre;
		private double precio;
		

		@OneToOne (targetEntity=Proveedor.class)
		private Proveedor proveedor;

		
		public Producto () {
			
		}
		
		public Producto (String nombre, double precio, Proveedor proveedor) {
			this.nombre=nombre;
			this.precio=precio;
			this.proveedor=proveedor;
		}
		
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

		public Proveedor getproveedore() {
			return proveedor;
		}

		public void setproveedor(Proveedor proveedor) {
			this.proveedor = proveedor;
		}

}
