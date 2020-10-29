package com.cg.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.cg.bean.Appointments;

public interface AppointmentService {
	public Appointments makeAppointment(Appointments appointmentDto);

	boolean validateDate(LocalDate date);

	//List<LocalTime> getAvailableSlots(int testId, LocalDate date);
}
