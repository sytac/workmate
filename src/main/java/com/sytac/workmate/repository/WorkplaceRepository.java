package com.sytac.workmate.repository;

import java.util.List;

import org.springframework.data.geo.Box;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.geo.Polygon;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.sytac.workmate.model.Workplace;

public interface WorkplaceRepository extends MongoRepository<Workplace, String>  {

	List<Workplace> findByLocationWithin(Polygon polygon);
}
