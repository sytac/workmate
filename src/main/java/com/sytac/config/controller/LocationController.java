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

import com.sytac.workmate.model.Employee;
import com.sytac.workmate.model.Workplace;
import com.sytac.workmate.repository.EmployeeRepository;
import com.sytac.workmate.repository.WorkplaceRepository;

@RestController
@RequestMapping("/workmate")
public class LocationController {

	private static Logger logger = Logger.getLogger(LocationController.class);
	
	@Autowired
	private WorkplaceRepository workplaceRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@RequestMapping(path = "/active", method = RequestMethod.GET)
	public String isActive() {
		return "OK";
	}
	
	
	@RequestMapping(path = "workplaces/{x}/{y}/", method = RequestMethod.GET)
	public @ResponseBody List<Workplace> getLocationByCoordinates(@PathVariable double x, @PathVariable double y) {
		
		logger.info("Get received for coordinates: "+x+" "+y);
		
		Circle circle = new Circle(new Point(x, y), new Distance(0.5, Metrics.KILOMETERS));
		
		List<Workplace> workplaces = workplaceRepository.findByLocationWithin(circle);
		
		return workplaces;
		
	}
	
//	@RequestMapping(path = "workplaces/{x}/{y}/{id}", method = RequestMethod.PUT)
//	public @ResponseBody List<Workplace> updateByCoordinates(@PathVariable double x, @PathVariable double y, @PathVariable String id) {
//		
//		logger.info("Get received for coordinates: "+x+" "+y);
//		
//		Circle circle = new Circle(new Point(x, y), new Distance(1, Metrics.KILOMETERS));
//		
//		List<Workplace> workplaces = workplaceRepository.findByLocationWithin(circle);
//		
//		Employee employee = employeeRepository.findOne(id);
//		
//		for (Workplace workplace : workplaces)  {
//			if (!workplace.getEmployees().contains(employee)) {
//				workplace.getEmployees().add(employee);
//				workplaceRepository.save(workplace);
//			}
//		}
//		
//		return workplaces;
//		
//	}
	
}
