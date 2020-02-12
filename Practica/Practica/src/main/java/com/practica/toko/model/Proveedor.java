package com.practica.toko.model;

import javax.persistence.*;

@Entity
public class Proveedor {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	String nombre;
	String producto;
	/*id String nombre double precio*/

	public Proveedor () {
		
	}

	public Proveedor ( int ident, String nombre, String producto) {
		this.nombre=nombre;
		this.producto=producto;
		this.id=ident;
	}
	
	public Proveedor (String nombre, String producto) {
		this.nombre=nombre;
		this.producto=producto;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}
	
}
