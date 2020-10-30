package com.cg.service;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.bean.Tests;
import com.cg.dao.TestDao;
import com.cg.exception.NoValueFoundException;
import com.cg.exception.NotPossibleException;

@Service
public class TestServiceImpl implements TestServiceInterface {

	@Autowired
	private TestDao testDao;
	
	private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

	@Override
	public Tests addTest(Tests test) {
		if(test.getTestName()==null)
		{
			logger.warn("Null test cannot be added");
			throw new NotPossibleException("This operation is not possible");
		}
		else
		return this.testDao.save(test);
	}

	@Override
	public void deleteTest(Integer testId) {
		Tests test=this.testDao.findAll().stream().filter(x -> testId.equals(x.getTestId())).findAny().orElse(null);
		if(test==null) {
			logger.warn("This Test is not Present");
			throw new NotPossibleException("This operation is not possible");
		}
		else
		this.testDao.deleteById(testId);

	}

	@Override
	public Tests searchTest(Integer testId) {
		Tests test=this.testDao.findAll().stream().filter(x -> testId.equals(x.getTestId())).findAny().orElse(null);
		if(test==null) {
			logger.warn("No such Test is available");
			throw new NoValueFoundException("No such Test is available");
		}
		else
		return test;
	}

	@Override
	public List<Tests> getAllTest() {
		List<Tests> tests=this.testDao.findAll();
		if(tests==null) {
			logger.warn("No Tests are available");
			throw new NoValueFoundException("No Tests are available");
		}
		else
		return tests;
	}

	@Override
	public Tests updateTest(Tests test) {
		Tests test1=this.searchTest(test.getTestId());
		return this.testDao.save(test1);
	}

	@Override
	public long countTests() {
		return this.testDao.count();
		
	}

}
