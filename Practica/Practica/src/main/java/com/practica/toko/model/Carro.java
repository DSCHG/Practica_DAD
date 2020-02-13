package com.practica.toko.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Carro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	private float coste;
	
	//private List<Producto> carrito;
	
	public Carro() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getCoste() {
		return coste;
	}
	public void setCoste(float coste) {
		this.coste = coste;
	}
	/*public List<Producto> getCarrito() {
		return carrito;
	}
	public void setCarrito(List<Producto> carrito) {
		this.carrito = carrito;
	}*/
	
	
	
}
