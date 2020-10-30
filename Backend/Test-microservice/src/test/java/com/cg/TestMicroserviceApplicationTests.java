package com.cg;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.bean.Tests;
import com.cg.dao.TestDao;
import com.cg.service.TestServiceImpl;

@SpringBootTest
class TestMicroserviceApplicationTests {

	@Autowired
	private TestServiceImpl service;

	@MockBean
	private TestDao repository;

	@Test
	public void presentTestsTesting() {
		//adds the behaviour of repository
		when(repository.findAll()).thenReturn(Stream.of(new Tests(5, "Eye Test")).collect(Collectors.toList()));
		//test the findall functionality
		Assertions.assertEquals(1, service.getAllTest().size());
	}


	@Test
	public void addTestsTesting() {
		Tests test = new Tests(4, "Eye Test");
		Tests test1 = new Tests(5, "Heart Test");
		when(repository.save(test)).thenReturn(test);
		//Assertions.assertEquals(test, service.addTest(test));		
		Assertions.assertEquals(test.getTestId(), service.addTest(test).getTestId());
	}


	@Test
	public void  deleteUserById() {
         Tests user = new Tests(7, "Eye Test");
		service.deleteTest(user.getTestId());
		verify(repository, times(1)).deleteById(9);
	}

}
