package com.mahadahack.geolocalizacion.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahadahack.geolocalizacion.model.Filtro;
import com.mahadahack.geolocalizacion.repository.ServiciosRepository;

@Controller
class HomeController {

	private static final String DATOS = "datos";
	
	private static ServiciosRepository repository;

	@Autowired
	public HomeController(ServiciosRepository repository) {
		this.repository = repository;
	}
	
	@RequestMapping("/home")
	public String goIndex(Model model, 
			@Param("categoria") String categoria,
			@Param("fecha") String fecha,
			@Param("hora") String hora,
			@Param("nick") String nick,
			HttpSession session) {
		
		categoria = "zoo";
		fecha = "20/06/2014";
		hora = "00:00:00";
		
		Filtro filtro = new Filtro(categoria, fecha, hora, nick);
		
		session.setAttribute("filtro", filtro);

		return "home";
	}

}
