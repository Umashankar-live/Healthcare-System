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

	//This function add test to database
	@Override
	public Tests addTest(Tests test) {
		if(test==null||test.getTestName()==null) {
			logger.warn("The data entered to add the test is incorrect");
			throw new NotPossibleException("Cannot Add this Test. Either one of the field is empty");
		}
		else
		return this.testDao.save(test);
	}

	@Override
	public Integer deleteTest(Integer testId) {
		try {
			this.testDao.deleteById(testId);
		} catch (Exception e) {
			e.printStackTrace();
			return -1 ;
		}
		
		return 1 ;

	}

	//This Function is used to search the center
	@Override
	public Tests searchTest(Integer testId) {
		Tests test=this.testDao.findAll().stream().filter(x -> testId.equals(x.getTestId())).findAny().orElse(null);
		if(test==null) {
			logger.warn("Check whether the Test Id is correct or not");
			throw new NoValueFoundException("Test not Found with this ID");
		}
		else
		return test;
	}

	//This function is used to get all Tests
	@Override
	public List<Tests> getAllTest() {
		if(this.testDao.findAll()==null) {
			 logger.warn("Check if database is empty or not");
			 throw new NoValueFoundException("There are no tests present");
		 }
		else
		return this.testDao.findAll();
	}
	
	
	//This Function is used to update the Test
	@Override
	public Tests updateTest(Tests test) {
		if(this.testDao.save(test)==null)
			throw new NotPossibleException("Cannot upadte this test");
		else
		return this.testDao.save(test);
	}

	//This method is used to count all the present tests in database
	@Override
	public long countTests() {
		return this.testDao.count();
		
	}

}
