package com.practica.toko.model;

import javax.persistence.*;

@Entity
public class Pedido {

	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToMany
	private Producto id_producto;
	@OneToOne
	private Usuario id_usuario;
	
	// Constructores
	public Pedido() {
	}
	
	public Pedido(int id,Producto id_producto) {
		this.id = id;
		this.id_producto = id_producto;
	}
	
	public Pedido(Producto id_producto) {
		this.id_producto = id_producto;
	}

	// Getters and Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Producto getId_producto() {
		return id_producto;
	}

	public void setId_producto(Producto id_producto) {
		this.id_producto = id_producto;
	}

	public Usuario getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Usuario id_usuario) {
		this.id_usuario = id_usuario;
	}

	
	
}
