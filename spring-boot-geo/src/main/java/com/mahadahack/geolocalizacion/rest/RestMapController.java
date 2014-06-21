package com.mahadahack.geolocalizacion.rest;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mahadahack.geolocalizacion.model.Filtro;
import com.mahadahack.geolocalizacion.model.Servicio;
import com.mahadahack.geolocalizacion.repository.ServiciosRepository;

// @formatter:off

@Controller
@RequestMapping("/map")
class RestMapController {

	private ServiciosRepository repository;

	@Autowired
	public RestMapController(ServiciosRepository repository) {
	    this.repository = repository;
	}
	
	@RequestMapping(value = "/services", method = RequestMethod.GET)
	public @ResponseBody List<Servicio> getServices(HttpSession session) {
		Filtro filtro = (Filtro)session.getAttribute("filtro");
		
		return repository.getServicesByFitlro(filtro);
	}
}	
