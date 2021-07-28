package com.bolsadeideas.springboot.backend.apirest.models.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "productos")
public class Productos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String nombre;

	private String descripcion;
	
	private String image;

	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date fechaCreada;

	private double precio;
	
	@PrePersist
	public void setearFecha() {
		fechaCreada = new Date();// con este metodo se setea automaticamente una fecha
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaCreada() {
		return fechaCreada;
	}

	public void setFechaCreada(Date fechaCreada) {
		this.fechaCreada = fechaCreada;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
