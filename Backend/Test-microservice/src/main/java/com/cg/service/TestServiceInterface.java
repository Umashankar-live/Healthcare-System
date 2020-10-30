package com.cg.service;

import java.util.List;

import com.cg.bean.Tests;

public interface TestServiceInterface {

	public Tests addTest(Tests test);

	public void deleteTest(Integer testId);

	public Tests searchTest(Integer testId);

	public List<Tests> getAllTest();
	
	public Tests updateTest(Tests test);

	public long countTests();

}
