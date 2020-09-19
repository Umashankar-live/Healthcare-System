package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.bean.Tests;
import com.cg.dao.TestDao;

@Service
public class TestServiceImpl implements TestServiceInterface {

	@Autowired
	private TestDao testDao;

	@Override
	public Tests addtest(Tests test) {
		// TODO Auto-generated method stub
		 return this.testDao.save(test);
	}

	@Override
	public void deletetest(Integer testId) {
		// TODO Auto-generated method stub
		this.testDao.deleteById(testId);
		
	}

	@Override
	public Tests searchTest(Integer testId) {
		// TODO Auto-generated method stub
		return this.testDao.findAll().stream().filter(x -> testId.equals(x.getTestId())).findAny().orElse(null);
	}

	@Override
	public List<Tests> getAllTest() {
		// TODO Auto-generated method stub
		return this.testDao.findAll();
	}
	
	public Tests updateTest(Tests test) {
		return this.testDao.save(test);
	}



}
