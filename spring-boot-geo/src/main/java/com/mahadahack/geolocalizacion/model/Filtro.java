package com.mahadahack.geolocalizacion.model;

public class Filtro {
	
	private String categoria;
	
	private String fecha;
	
	private String hora;
	
	private String nick;

	
	
	public Filtro(String categoria, String fecha, String hora, String nick) {
		super();
		this.categoria = categoria;
		this.fecha = fecha;
		this.hora = hora;
		this.nick = nick;
	}

	public Filtro() {
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
	
	
}
