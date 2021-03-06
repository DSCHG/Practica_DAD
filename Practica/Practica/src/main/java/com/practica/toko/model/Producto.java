package com.practica.toko.model;


import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.*;

@Entity
public class Producto implements Serializable{
	// Atributos
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int id;
		@Column
		private String nombre;
		@Column
		private double precio;
		
		@ManyToOne(optional=true)
		private Proveedor proveedor;
		
		public Producto () {
			
		}
		
		public Producto (String nombre, double precio) {
			this.nombre=nombre;
			this.precio=precio;
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

		public Proveedor getProveedor() {
			return proveedor;
		}

		public void setProveedor(Proveedor proveedor) {
			this.proveedor = proveedor;
		}

		public void setId(int id) {
			this.id = id;
		}

		@Override
		public String toString() {
			if(proveedor==null) {
				proveedor=new Proveedor(-1,"TOKO",this.getNombre(),new ArrayList<Producto>());
			}
			return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", proveedor=" + proveedor.getNombre()
					+ "]";
		}
		
		
		
		
		
}
