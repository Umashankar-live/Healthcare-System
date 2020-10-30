package com.cg.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	@RequestMapping(value = "/addTest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Tests addTest(@RequestBody Tests test) {
		Tests test1=this.testService.addTest(test);
		logger.info("Test Added successfully");
		return test1;
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
	public Tests searchTestById(@PathVariable Integer testId) {
		return this.testService.searchTest(testId);
	}

	@RequestMapping(value = "/updateTest/{testId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Tests updateHotel(@RequestBody Tests test,@PathVariable Integer testId) {
		Tests newTest=this.testService.searchTest(testId);
		newTest.setTestName(test.getTestName());
		Tests test1=this.testService.updateTest(newTest);
		logger.info("Test Updated successfully");
		return test1;
	}

	@GetMapping(value = "/countTest")
	public long countTests() {
		return testService.countTests();
	}

}