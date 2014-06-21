package com.mahadahack.geolocalizacion.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mahadahack.geolocalizacion.mongo.Location;
import com.mahadahack.geolocalizacion.mongo.LocationRepository;

// @formatter:off

@Controller
@RequestMapping("/geo")
class RestGeoController {

	private static final Point DUS = new Point(6.810036, 51.224088);

	private static LocationRepository repo;

	@Autowired
	public RestGeoController(LocationRepository repo) {
		this.repo = repo;

		// ensure geospatial index
		repo.save(new Location("pos_ing_1", 40.474269, -3.866283));
		repo.save(new Location("pos_zoo_1", 40.474369, -3.866383));
		repo.save(new Location("pos_algo_1", 40.474469, -3.866483));

		repo.save(new Location("pos_ing_2", 40.474279, -3.866283));
		repo.save(new Location("pos_zoo_2", 40.474379, -3.866383));
		repo.save(new Location("pos_algo_2", 40.474479, -3.866483));

		repo.save(new Location("pos_ing_3", 40.474169, -3.866583));
		repo.save(new Location("pos_zoo_3", 40.474169, -3.866583));
		repo.save(new Location("pos_algo_3", 40.471469, -3.866583));
		
		
	}

	@RequestMapping(value = "/near", method = RequestMethod.GET)
	public @ResponseBody
	String getSample(@Param("km") String km) {

		List<Location> locations = repo.findByPositionNear(DUS, new Distance(Integer.parseInt(km),	Metrics.KILOMETERS));

		return "sample";

	}

}
