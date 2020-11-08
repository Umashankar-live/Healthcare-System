package com.cg.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.cg.bean.Appointments;

public interface AppointmentService {

	Appointments findAppointmentsbyId(Integer appointmentId);

	List<Appointments> findallAppointments();

	Appointments saveAppointments(Appointments a);

	String checkAppointmentStatus(Integer appointmentId);

	Appointments findAppointmentsByUserId(int userId);

	Appointments makeAppointment(Appointments appointment);

	List<Appointments> findAppointmentsByDiagnosticCenterId(int diagnosticCenterId);

	Appointments approveAppointment(Integer appointmentId)
			throws AddressException, MessagingException, IOException, Exception;

	boolean validateDate(LocalDate date);

	Appointments cancelAppointment(Integer appointmentId);

	boolean checkAppointmentByAppointmentId(Integer appointmentId);

	Appointments searchAppointmentByAppointmentId(Integer appointmentId);

	String export(Integer userId) throws IOException;

	public Integer deleteAppointment(Integer testId);

}
