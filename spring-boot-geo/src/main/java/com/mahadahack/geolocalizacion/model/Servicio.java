package com.mahadahack.geolocalizacion.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Servicio {

	
	private String categoria;
	private Timestamp fecha_inicio;
	private Timestamp fecha_fin;
	private String title = "titulo a cabiar";
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
	private String descripcion;
	@JsonProperty("service_id")
	private String id_servicio;
	private String rating = "5";
	@JsonProperty("user_id")
	private String id_usuario;
	private String email;
	private String mongo_pos;
	private Posicion position;
	
	public Servicio() {
	}
	
	public Servicio(String categoria, Timestamp fecha_inicio,
			Timestamp fecha_fin, String descripcion, String id_servicio,
			String id_usuario, String email, String mongo_pos) {
		super();
		this.categoria = categoria;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.descripcion = descripcion;
		this.id_servicio = id_servicio;
		this.id_usuario = id_usuario;
		this.email = email;
		this.mongo_pos = mongo_pos;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Timestamp getFecha_inicio() {
		return fecha_inicio;
	}
	public void setFecha_inicio(Timestamp fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}
	public Timestamp getFecha_fin() {
		return fecha_fin;
	}
	public void setFecha_fin(Timestamp fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getId_servicio() {
		return id_servicio;
	}
	public void setId_servicio(String id_servicio) {
		this.id_servicio = id_servicio;
	}
	public String getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMongo_pos() {
		return mongo_pos;
	}
	public void setMongo_pos(String mongo_pos) {
		this.mongo_pos = mongo_pos;
	}
	public Posicion getPosition() {
		return position;
	}
	public void setPosition(Posicion position) {
		this.position = position;
	}
}
