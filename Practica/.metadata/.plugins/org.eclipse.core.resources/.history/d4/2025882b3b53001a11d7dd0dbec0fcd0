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
	
	@OneToMany
	@JoinColumn(name = "id_producto")
	private List<Producto> producto;
	
	@ManyToOne
	@JoinColumn(name = "Usuario_id")
	private Usuario usuario;
	
	// Constructores
	public Pedido() {
		producto = new ArrayList<>();
	}
	
	public Pedido(Usuario u) {
		this.usuario = u;
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

	public List<Producto> getProducto() {
		return producto;
	}

	public void setProducto(List<Producto> producto) {
		this.producto = producto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	

	
	
}
