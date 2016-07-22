package com.sytac.config.controller;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sytac.workmate.model.Workplace;
import com.sytac.workmate.repository.WorkplaceRepository;

@RestController
@RequestMapping("/workmate")
public class LocationController {

	private static Logger logger = Logger.getLogger(LocationController.class);
	
	@Autowired
	private WorkplaceRepository repository;
	
	@RequestMapping(path = "/active", method = RequestMethod.GET)
	public String isActive() {
		return "OK";
	}
	
	
	@RequestMapping(path = "workplaces/{x}/{y}/", method = RequestMethod.GET)
	public @ResponseBody List<Workplace> getLocationByCoordinates(@PathVariable double x, @PathVariable double y) {
		
		logger.info("Get received for coordinates: "+x+" "+y);
		
		Circle circle = new Circle(new Point(x, y), new Distance(2, Metrics.MILES));
		
		List<Workplace> workplaces = repository.findByLocationWithin(circle);
		
		return workplaces;
		
	}
	
}
