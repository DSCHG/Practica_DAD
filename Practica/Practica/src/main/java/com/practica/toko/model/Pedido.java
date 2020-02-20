package com.practica.toko.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Pedido {

	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private double precioTotal;
	
	// realacion uno a muchos con productos unidirecional
	@ManyToMany
	private List<Producto> producto;
	
	// Constructores
	public Pedido() {
		producto = new ArrayList<>();
	}
	
	/*public Pedido(Usuario u) {
		this.usuario = u;
	}*/
	
	public Pedido(List<Producto> id_producto) {
		this.producto = id_producto;
	}

	// Getters and Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Producto> getId_producto() {
		return producto;
	}

	public void setId_producto(List<Producto> id_producto) {
		this.producto = id_producto;
	}

	
	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public List<Producto> getProducto() {
		return producto;
	}

	public void setProducto(List<Producto> producto) {
		this.producto = producto;
	}

	

	
	

	
	
}
