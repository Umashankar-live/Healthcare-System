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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/admin/test")
public class TestController {

	@Autowired
	private TestServiceInterface testService;

	
	//http://localhost:8001/admin/test/addTest
	@ApiOperation(value = "Add Test", nickname = "addTest")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Tests.class),
	@ApiResponse(code = 500, message = "Failure", response = Tests.class) })
	@RequestMapping(value = "/addTest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Tests addTest(@RequestBody Tests test) {
		return this.testService.addTest(test);
	}

	
	//http://localhost:8001/admin/test/getAllTests
	@ApiOperation(value = "Get All Tests", nickname = "getAllTests")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Tests.class),
	@ApiResponse(code = 500, message = "Failure", response = Tests.class) })
	@GetMapping(value = "/getAllTests")
	public List<Tests> getAllTest() {
		return this.testService.getAllTest();
	}

	//http://localhost:8001/admin/test/deleteTest/3
	@ApiOperation(value = "Delete Test", nickname = "deleteTest")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Tests.class),
	@ApiResponse(code = 500, message = "Failure", response = Tests.class) })
	@DeleteMapping(value = "/deleteTest/{testId}")
	public Integer deleteTest(@PathVariable Integer testId) {
	  return this.testService.deleteTest(testId);
	}

	
	//http://localhost:8001/admin/test/searchTest/5
	@ApiOperation(value = "Search Test", nickname = "searchTest")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Tests.class),
	@ApiResponse(code = 500, message = "Failure", response = Tests.class) })
	@GetMapping(value = "/searchTest/{testId}")
	public Tests searchTestsById(@PathVariable Integer testId) {
		return this.testService.searchTest(testId);
	}

	
	//http://localhost:8001/admin/test/updateTest/1
	@ApiOperation(value = "Update Test", nickname = "updateTest")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Tests.class),
	@ApiResponse(code = 500, message = "Failure", response = Tests.class) })
	@RequestMapping(value = "/updateTest/{testId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Tests updateTests(@RequestBody Tests test,@PathVariable Integer testId) {
		Tests newTest=this.testService.searchTest(testId);
		newTest.setTestName(test.getTestName());
		return this.testService.updateTest(newTest);
	}

	
	//http://localhost:8001/admin/test/countTest
	@ApiOperation(value = "Count All Tests", nickname = "countTest")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Tests.class),
	@ApiResponse(code = 500, message = "Failure", response = Tests.class) })
	@GetMapping(value = "/countTest")
	public long countTests() {
		return testService.countTests();
	}

}