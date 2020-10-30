package com.cg.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	//http://localhost:9003//admin/diagnosticCenter/addCenter
	@PostMapping(value = "/addCenter")
	public DiagnosticCenters addCenters(@RequestBody DiagnosticCenters center) {
		System.out.println(center.getCenterName());
		System.out.println(center.getListOfTests().size());
		return this.service.addCenter(center);
	}
	
	//http://localhost:9003//admin/diagnosticCenter/getAllCenters
	@GetMapping(value = "/getAllCenters")
	public List<DiagnosticCenters> getAllCenters() {
		return this.service.getAllCenter();
	}
	
	@RequestMapping(value = "/deleteCenter/{centerId}", method = RequestMethod.DELETE)
	//http://localhost:9003//admin/diagnosticCenter/deleteCenter/3
	public void deleteCenter(@PathVariable Integer centerId) {
		this.service.deleteCenter(centerId);
	}
	
	//http://localhost:9003//admin/diagnosticCenter/searchCenter/1
	@GetMapping(value = "/searchCenter/{centerId}")
	public DiagnosticCenters searchCenterById(@PathVariable Integer centerId) {
		return this.service.searchCenter(centerId);
	}
	
	//http://localhost:9003//admin/diagnosticCenter/updateCenter/1
	@PutMapping(value = "/updateCenter/{centerId}")
	public DiagnosticCenters updateCenter(@RequestBody DiagnosticCenters center) {
		return this.service.updateCenter(center);
	}
	
	//http://localhost:9003//admin/diagnosticCenter/countCenter
	@GetMapping(value = "/countCenter")
	public long countCenters() {
		return this.service.countCenters();
	}
}
