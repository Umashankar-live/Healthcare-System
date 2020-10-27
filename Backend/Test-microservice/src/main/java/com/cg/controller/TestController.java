package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bean.Tests;
import com.cg.service.TestServiceInterface;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/admin/test")
public class TestController {

	@Autowired
	private TestServiceInterface testService;

	@RequestMapping(value = "/addTest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Tests addTest(@RequestBody Tests test) {
		return this.testService.addTest(test);
	}

	@GetMapping(value = "/getAllTests")
	public List<Tests> getAllTest() {
		return this.testService.getAllTest();
	}

	@DeleteMapping(value = "/deleteTest/{testId}")
	public void deleteTest(@PathVariable Integer testId) {
		this.testService.deleteTest(testId);
	}

	@GetMapping(value = "/searchTest/{testId}")
	public Tests searchHotelById(@PathVariable Integer testId) {
		return this.testService.searchTest(testId);
	}
	
	@RequestMapping(value = "/updateTest/{testId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Tests updateHotel(@RequestBody Tests test) {
		return this.testService.updateTest(test);
	}
	
}