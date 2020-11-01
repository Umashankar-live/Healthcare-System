package com.cg.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.cg.bean.Appointments;

public interface AppointmentService {
	
	Appointments findAppointmentsbyId(Integer appointmentId);
	
	List<Appointments> findallAppointments();
	
	Appointments saveAppointments(Appointments a);
	
	String checkAppointmentStatus(Integer appointmentId);

	List<Appointments> findAppointmentsByUserId(int userId);

	Appointments makeAppointment(Appointments appointment);

	List<Appointments> findAppointmentsByDiagnosticCenterId(int diagnosticCenterId);

	boolean approveAppointment(Integer appointmentId);

	boolean validateDate(LocalDate date);

	String cancelAppointment(Integer appointmentId);

	boolean checkAppointmentByAppointmentId(Integer appointmentId);

	Appointments searchAppointmentByAppointmentId(Integer appointmentId);

	String export(Integer userId) throws IOException;

	//boolean getPendingAppointmentsForDiagnosticCenter(int diagnosticCenterId);

    //boolean getPendingAppointmentsForTestCenter(int testCenterId);
	
}
