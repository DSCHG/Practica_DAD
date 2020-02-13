package com.practica.toko.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Pedido {

	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToMany (targetEntity=Producto.class)
	private List<Producto> producto;
	@OneToOne
	private Usuario usuario;
	
	// Constructores
	public Pedido() {
	}
	
	public Pedido(int id,List<Producto> id_producto) {
		this.id = id;
		this.producto = id_producto;
	}
	
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

	public Usuario getId_usuario() {
		return usuario;
	}

	public void setId_usuario(Usuario id_usuario) {
		this.usuario = id_usuario;
	}

	
	

	
	
}
