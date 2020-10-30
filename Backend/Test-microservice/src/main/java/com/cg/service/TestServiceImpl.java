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
	public Tests addTest(Tests test) {
		return this.testDao.save(test);
	}

	@Override
	public void deleteTest(Integer testId) {
		this.testDao.deleteById(testId);

	}

	@Override
	public Tests searchTest(Integer testId) {
		return this.testDao.findAll().stream().filter(x -> testId.equals(x.getTestId())).findAny().orElse(null);
	}

	@Override
	public List<Tests> getAllTest() {
		return this.testDao.findAll();
	}

	@Override
	public Tests updateTest(Tests test) {
		return this.testDao.save(test);
	}

	@Override
	public long countTests() {
		return this.testDao.count();
		
	}

}
