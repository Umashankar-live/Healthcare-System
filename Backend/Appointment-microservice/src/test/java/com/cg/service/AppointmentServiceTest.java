package com.cg.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.bean.Appointments;
import com.cg.dao.AppointmentRepository;

public class AppointmentServiceTest {

	@InjectMocks
	private AppointmentServiceImpl AppointmentService;

	@MockBean
	private AppointmentRepository appointmentRepository;

	private Appointments appointment;

	@BeforeEach
	void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		appointment = new Appointments();
		appointment.setAppointmentId(14);
		appointment.setUserId(13);
		appointment.setTestId(13);
		appointment.setCenterId(3);
		appointment.setApproved(0);
		appointment.setDatetime(LocalDateTime.parse("2020-12-30T00:00:00"));
	}
	
	@Test
	public void testAddCenter() {

		Mockito.when(appointmentRepository.save(appointment)).thenReturn(appointment);
		assertThat(AppointmentService.makeAppointment(appointment)).isEqualTo(appointment);

	}
	
	@Test
	public void teststatus() {
		Mockito.when(AppointmentService.checkAppointmentStatus(1)).thenReturn("Cancelled");
		assertThat(AppointmentService.checkAppointmentStatus(1)).isEqualTo("Cancelled");
	}
	
	
}
