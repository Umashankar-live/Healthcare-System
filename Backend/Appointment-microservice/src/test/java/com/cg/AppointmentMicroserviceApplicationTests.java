package com.cg;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.bean.Appointments;
import com.cg.dao.AppointmentRepository;
import com.cg.service.AppointmentServiceImpl;

@SpringBootTest
class AppointmentMicroserviceApplicationTests {

	@Autowired
	private AppointmentServiceImpl service;
	
	@MockBean
	private AppointmentRepository repo;
	
	Appointments appointment;
	
	@Test
	public void presentAppointmentsTesting() {
		when(repo.findAll()).thenReturn(Stream.of(new Appointments(14,13,13,3,"pending",LocalDateTime.parse("2020-12-30T00:00:00"))).collect(Collectors.toList()));
		//Assertions.assertEquals(1, service.getAllTest().size());
		Assertions.assertEquals(1, service.findallAppointments().size());
	}

	@Test
	public void CheckStatusTesting() {
		when(repo.findAll()).thenReturn(Stream.of(new Appointments(14,13,13,3,"pending",LocalDateTime.parse("2020-12-30T00:00:00"))).collect(Collectors.toList()));
		//Assertions.assertEquals(1, service.getAllTest().size());
		Assertions.assertEquals(0, repo.findAll().size());
	}
	
	@Test
	public void addAppointmentsTesting() {
		Appointments a = new Appointments(14,13,13,3,"pending",LocalDateTime.parse("2020-12-30T00:00:00"));
		Appointments b = new Appointments(15,14,14,3,"pening",LocalDateTime.parse("2020-11-30T00:00:00"));
		when(repo.save(a)).thenReturn(a);
		//Assertions.assertEquals(test, service.addTest(test));		
		Assertions.assertEquals(a.getTestId(), repo.save(a).getTestId());
	}

	@Test
	public void  deleteUserById() {
         Appointments b = new Appointments(14,13,13,3,"pending",LocalDateTime.parse("2020-12-30T00:00:00"));
		repo.delete(b);
         //service.deleteTest(b.getAppointmentId());
		verify(repo, times(1)).delete(b);;
	}

	
	@Test
	void contextLoads() {
	}

}
