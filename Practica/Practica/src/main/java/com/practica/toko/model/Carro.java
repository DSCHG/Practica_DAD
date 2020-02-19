package com.practica.toko.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CascadeType;



@Entity
public class Carro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	private float coste;
	
	@OneToMany(targetEntity = Producto.class,cascade = javax.persistence.CascadeType.PERSIST)
	private List<Producto> carrito=new ArrayList<>();
	
	public Carro() {
		carrito = new ArrayList<>();
	}
	
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
	public List<Producto> getCarrito() {
		return carrito;
	}
	public void setCarrito(List<Producto> carrito) {
		this.carrito = carrito;
	}
}
