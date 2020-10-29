package com.cg.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bean.Appointments;
import com.cg.dao.AppointmentRepository;
import com.cg.exception.NoValueFoundException;
import com.cg.exception.NotPossibleException;

@Service
public class Appointment_ServiceImpl implements Appointment_Service {

	@Autowired
	private AppointmentRepository appointmentRepository;

	// @Autowired
	// private RestTemplate restTemplate;

	private static final Logger logger = LoggerFactory.getLogger(Appointment_ServiceImpl.class);

	String appointmentNotPresent = "No appointment present with this appointment Id";
	String diagnosticCenterNotPresent = "No diagnostic center present with this diagnostic center Id";
	String userNotPresent = "No user present with this user Id";

	// Method to check appointment Status
	@Override
	public String checkAppointmentStatus(Integer appointmentId) {

		Appointments appointment = appointmentRepository.findByAppointmentId(appointmentId);

		if (appointment == null) {
			logger.warn("check appointment status failed, no appointmentId");
			throw new NoValueFoundException(appointmentNotPresent);
		}
		String status = "pending";
		int statusValue = appointment.getApproved();
		if (statusValue == 1)
			status = "Approved";
		else if (statusValue == -1)
			status = "Cancelled";
		return status;

	}

	// Method to fetch appointments for a user with user Id
	@Override
	public List<Appointments> findAppointmentsByUserId(int userId) {

		// Boolean userExists = restTemplate.getForObject("url to check userId" +
		// userId, boolean.class);
		List<Appointments> appointmentList = appointmentRepository.findByUserId(userId);
		Boolean userExists;
		if (appointmentList.isEmpty()) {
			userExists = Boolean.FALSE;
		} else
			userExists = Boolean.TRUE;

		if (Boolean.FALSE.equals(userExists)) {
			logger.warn(userNotPresent);
			throw new NoValueFoundException(userNotPresent);
		}

		appointmentList = appointmentRepository.findByUserId(userId);
		if (appointmentList.isEmpty()) {
			logger.warn("No appointment made for this user ");
			throw new NoValueFoundException("You Haven't made any appointment yet");
		}
		// return appointmentList.stream().map(a -> new
		// AppointmentDto(a)).collect(Collectors.toList());
		return appointmentList;

	}

	// Method to fetch appointments for a diagnostic center with diagnostic center
	// Id
	@Override
	public List<Appointments> findAppointmentsByDiagnosticCenterId(int diagnosticCenterId) {

		// String url = "URL to check whether diagnostic center is present" +
		// diagnosticCenterId;
		// Boolean diagnosticCenterExists = restTemplate.getForObject(url,
		// boolean.class);

		List<Appointments> appointmentList = appointmentRepository.findByCenterId(diagnosticCenterId);
		Boolean diagnosticCenterExists;
		if (appointmentList.isEmpty()) {
			diagnosticCenterExists = Boolean.FALSE;
		} else
			diagnosticCenterExists = Boolean.TRUE;

		// Boolean diagnosticCenterExists=Boolean.TRUE;
		if (Boolean.FALSE.equals(diagnosticCenterExists)) {
			logger.warn(diagnosticCenterNotPresent);
			throw new NoValueFoundException(diagnosticCenterNotPresent);
		}

		if (appointmentList.isEmpty()) {
			logger.warn("user has not made any appointment yet!");
			throw new NoValueFoundException("You Haven't made any appointment yet!");
		}
		// return appointmentList.stream().map(a -> new
		// AppointmentDto(a)).collect(Collectors.toList());
		return appointmentList;
	}

	// Method to validate date according to appointments. (Validations on Date)
	@Override
	public boolean validateDate(LocalDate date) {
		if (!date.isBefore(LocalDate.now().plusDays(30))) {
			logger.warn("Invalid date");
			throw new NotPossibleException("Please select date of today or any day within 30 days of today.");
		} else if (date.isBefore(LocalDate.now())) {
			logger.warn("Invalid date");
			throw new NotPossibleException("Please select date of today or any day within 30 days of today.");
		} else if (date.getDayOfWeek().toString().equalsIgnoreCase("SUNDAY")) {
			logger.warn("date is of sunday");
			throw new NotPossibleException("we are closed on Sunday!");
		}

		return true;
	}

	// Method to cancel appointment by appointment Id.
	@Override
	public String cancelAppointment(Integer appointmentId) {

		Appointments appointment = appointmentRepository.findByAppointmentId(appointmentId);
		if (appointment == null) {
			logger.warn(appointmentNotPresent);
			throw new NoValueFoundException(appointmentNotPresent);
		}
		
		
		
		if (appointment.getDatetime().toLocalDate().isBefore(LocalDate.now())) {
			return "Appointment Date is already passed";
		} else if (appointment.getDatetime().toLocalDate() == LocalDate.now()
				&& appointment.getDatetime().toLocalTime().isBefore(LocalTime.now())) {
			return "Appointment Time is already passed";
		}
		appointment.setApproved(-1);
		Appointments appointmentObject = appointmentRepository.save(appointment);

		if (appointmentObject.getApproved() == 1) {
			return "Problem Occured!!";
		} else {
			return "Appointment Cancelled!!";
		}
	}

	// Method to return boolean value by checking if appointment exists in database
	// or not.
	@Override
	public boolean checkAppointmentByAppointmentId(Integer appointmentId) {
		Appointments appointment = appointmentRepository.findByAppointmentId(appointmentId);
		if (appointment == null) {
			logger.warn(appointmentNotPresent);
			throw new NoValueFoundException(appointmentNotPresent);
		}

		return true;
	}

	// Method to search appointment with appointment Id.
	@Override
	public Appointments searchAppointmentByAppointmentId(Integer appointmentId) {
		Appointments appointment = appointmentRepository.findByAppointmentId(appointmentId);
		if (appointment == null) {
			logger.warn(appointmentNotPresent);
			throw new NoValueFoundException(appointmentNotPresent);
		}

		// return new AppointmentDto(appointment);
		return appointment;
	}

	@Override
	public Appointments makeAppointment(Appointments appointment1) {

		Appointments appointment = new Appointments();

		appointment.setAppointmentId(null);
		appointment.setApproved(0); // 0 is pending , so by default it will be pending

		/*
		 * String diagnosticUrl ="url to check if appointment center exists"
		 * +appointment1.getCenterId(); Boolean diagnosticCenterExists =
		 * restTemplate.getForObject(diagnosticUrl, boolean.class);
		 * 
		 * if (Boolean.FALSE.equals(diagnosticCenterExists)) {
		 * logger.warn(diagnosticCenterNotPresent); throw new
		 * NoValueFoundException(diagnosticCenterNotPresent); } else
		 * appointment.setCenterId(appointment1.getCenterId());
		 */

		/*
		 * String userUrl = "URL  to check if user exists" +appointment1.getUserId();
		 * Boolean userExists = restTemplate.getForObject(userUrl, boolean.class); if
		 * (Boolean.FALSE.equals(userExists)) { logger.warn(userNotPresent); throw new
		 * NoValueFoundException(userNotPresent); } else
		 * appointment.setUserId(appointment1.getUserId());
		 */

		/*
		 * String testUrl =
		 * "URL to check if test center exists"+appointment1.getTestId(); Boolean
		 * testCenterExists = restTemplate.getForObject(testUrl, boolean.class);
		 * 
		 * if (Boolean.FALSE.equals(testCenterExists)) {
		 * logger.warn("Test C doesn't exist"); throw new
		 * NoValueFoundException("No Test  present with this Center Id"); } else
		 * appointment.setTestId(appointment1.getTestId());
		 */

		appointment.setCenterId(appointment1.getCenterId());
		appointment.setUserId(appointment1.getUserId());
		appointment.setTestId(appointment1.getTestId());

		if (validateDate(appointment1.getDatetime().toLocalDate())) {

			appointment.setDatetime(appointment1.getDatetime());
			appointment = appointmentRepository.save(appointment);
		} else {
			throw new NotPossibleException("sorry we can't book your appointment");
		}

		// appointment.setDatetime(appointment1.getDatetime());
		// appointment = appointmentRepository.save(appointment);
		return appointment;

	}

	@Override
	public boolean approveAppointment(Integer appointmentId) {

		Appointments appointment = appointmentRepository.findByAppointmentId(appointmentId);
		if (appointment == null) {
			logger.warn(appointmentNotPresent);
			throw new NoValueFoundException(appointmentNotPresent);
		}

		int statusValue = appointment.getApproved();
		LocalDateTime dateTime = appointment.getDatetime();

		if (statusValue != -1 && statusValue != 1) {
			if ((dateTime.toLocalDate().equals(LocalDate.now())
					&& Duration.between(dateTime.toLocalTime(), LocalDateTime.now().toLocalTime()).toMinutes() >= 30
					&& dateTime.toLocalTime().isAfter(LocalTime.now())
					|| dateTime.isAfter(LocalDateTime.now()) && !dateTime.toLocalDate().equals(LocalDate.now()))) {
				appointment.setApproved(1); // can be also given a agrument as (Boolean.TRUE) if it isnt approving the
											// request , try changing this
				appointmentRepository.save(appointment);
			} else {
				logger.warn("Appointment date is missed");
				throw new NotPossibleException("Appointment date and time is already missed!!");
			}

		}

		else if (statusValue == 1) {
			logger.warn("already approved appointment");
			throw new NotPossibleException("Appointment is already approved");
		} else if (statusValue == -1) {
			logger.warn("Already cancelled appointment");
			throw new NotPossibleException("Appointment is already cancelled");
		}

		return true;

	}
}
