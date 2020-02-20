package com.practica.toko.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Proveedor {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	@Column
	String nombre;

	// realacion uno a muchos con proveedor bidireccional
	@OneToMany(mappedBy = "proveedor")
	private List <Producto> listaProductos;
	
	public Proveedor () {
		listaProductos=new ArrayList<Producto>();
	}

	public Proveedor ( int ident, String nombre, String producto, List<Producto> listaProductos) {
		this.nombre=nombre;
		this.listaProductos=listaProductos;
		this.id=ident;
		//this.listaProductos=listaProductos;
	}
	
	
	public Proveedor (String nombre) {
		this.nombre=nombre;
		listaProductos=new ArrayList<Producto>();
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

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}
}
