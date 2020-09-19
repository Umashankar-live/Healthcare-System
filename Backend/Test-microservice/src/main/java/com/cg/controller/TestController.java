package com.cg.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.cg.bean.Tests;
import com.cg.service.TestServiceInterface;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/test")
public class TestController {

	@Autowired
	private TestServiceInterface testService;

	@RequestMapping(value = "/add-Test", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Tests addHotel(@RequestBody Tests test) {
		return this.testService.addtest(test);
	}

	@GetMapping(value = "/list-Tests")
	public List<Tests> getAllTest() {
		return this.testService.getAllTest();
	}

	@RequestMapping(value = "/delete-test/{testId}", method = RequestMethod.DELETE)
	public void deleteTest(@PathVariable Integer testId) {
		this.testService.deletetest(testId);
	}

	@GetMapping(value = "/search-TestById/{testId}")
	public Tests searchHotelById(@PathVariable Integer testId) {
		return this.testService.searchTest(testId);
	}
	
	@RequestMapping(value = "/update-Test/{testId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Tests updateHotel(@RequestBody Tests test) {
		return this.testService.updateTest(test);
	}
	
}