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

import com.cg.bean.DiagnosticCenters;
import com.cg.dao.DiagnosticCenterDao;
import com.cg.service.DiagnosticCenterServiceImpl;

@SpringBootTest
class DiagnosticCenterMicroserviceApplicationTests {

	@Autowired
	private DiagnosticCenterServiceImpl service;

	@MockBean
	private DiagnosticCenterDao repository;

	@Test
	public void presentTestsTesting() {
		when(repository.findAll()).thenReturn(Stream.of(new DiagnosticCenters(5, "Cheers Laboratory")).collect(Collectors.toList()));
		Assertions.assertEquals(1, service.getAllCenter().size());
	}


	@Test
	public void addTestsTesting() {
		DiagnosticCenters center = new DiagnosticCenters(4, "Cheers Laboratory");
		//DiagnosticCenters test1 = new DiagnosticCenters(5, "Rahul Laboratory");
		when(repository.save(center)).thenReturn(center);
		//Assertions.assertEquals(test, service.addTest(test));		
		Assertions.assertEquals(center.getCenterId(), service.addCenter(center).getCenterId());
	}


	@Test
	public void  deleteUserById() {
		DiagnosticCenters center = new DiagnosticCenters(7, "Eye Test");
		service.deleteCenter(center.getCenterId());
		verify(repository, times(1)).deleteById(7);
	}

}
