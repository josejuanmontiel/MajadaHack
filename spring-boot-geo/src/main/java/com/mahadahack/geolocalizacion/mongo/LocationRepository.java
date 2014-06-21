package com.mahadahack.geolocalizacion.mongo;

import java.util.List;

import org.springframework.data.geo.Box;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocationRepository extends MongoRepository<Location, String> {

	List<Location> findByPositionWithin(Circle c);

	List<Location> findByPositionWithin(Box b);

	List<Location> findByPositionNear(Point p, Distance d);
}
