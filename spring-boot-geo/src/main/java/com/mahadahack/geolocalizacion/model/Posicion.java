package com.mahadahack.geolocalizacion.model;

public class Posicion {

	public Posicion(String lat, String lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}
	
	
	public Posicion(Double lat, Double lng) {
		super();
		this.lat = Double.toString(lat);
		this.lng = Double.toString(lng);
	}
	
	private String lat;
	private String lng;
	
	
	
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	
	
	
}
