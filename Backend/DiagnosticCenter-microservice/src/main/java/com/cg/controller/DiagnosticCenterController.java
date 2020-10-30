package com.cg.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bean.DiagnosticCenters;
import com.cg.service.DiagnosticCenterService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/admin/diagnosticCenter")
public class DiagnosticCenterController {
	
	@Autowired
	private DiagnosticCenterService service;
	
	@PostMapping(value = "/addCenter", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public DiagnosticCenters addHotel(@RequestBody DiagnosticCenters center) {
		System.out.println(center.getCenterName());
		System.out.println(center.getListOfTests().size());
		return this.service.addCenter(center);
	}
	
	@GetMapping(value = "/getAllCenters")
	public List<DiagnosticCenters> getAllTest() {
		return this.service.getAllCenter();
	}
	
	@RequestMapping(value = "/deleteCenter/{centerId}", method = RequestMethod.DELETE)
	public void deleteCenter(@PathVariable Integer centerId) {
		this.service.deleteCenter(centerId);
	}
	
	@GetMapping(value = "/searchCenter/{centerId}")
	public DiagnosticCenters searchHotelById(@PathVariable Integer centerId) {
		return this.service.searchCenter(centerId);
	}
	
	@RequestMapping(value = "/updateCenter/{centerId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public DiagnosticCenters updateCenter(@RequestBody DiagnosticCenters center) {
		return this.service.updateCenter(center);
	}
	
	@GetMapping(value = "/countCenter")
	public long countAssets() {
		return this.service.countCenters();
	}
}
