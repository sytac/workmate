package com.sytac.workmate.repository;

import java.util.List;

import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Polygon;
import org.springframework.data.repository.CrudRepository;

import com.sytac.workmate.model.Workplace;

public interface WorkplaceRepository extends CrudRepository<Workplace, String>  {

	List<Workplace> findByLocationWithin(Polygon polygon);
	
	List<Workplace> findByLocationWithin(Circle c);
	
}
