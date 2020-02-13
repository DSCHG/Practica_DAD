package com.practica.toko.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Proveedor {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	String nombre;
	String producto;
	


	@OneToMany (targetEntity=Producto.class)
	private List <Producto> listaProductos;
	
	public Proveedor () {
		
	}

	public Proveedor ( int ident, String nombre, String producto, List<Producto> listaProductos) {
		this.nombre=nombre;
		this.producto=producto;
		this.id=ident;
		this.listaProductos=listaProductos;
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

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}
	
}
