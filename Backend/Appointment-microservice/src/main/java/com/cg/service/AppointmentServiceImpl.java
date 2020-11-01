package com.cg.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cg.bean.Appointments;
import com.cg.bean.DiagnosticCenters;
import com.cg.bean.Tests;
import com.cg.bean.User;
import com.cg.dao.AppointmentRepository;
import com.cg.exception.NoValueFoundException;
import com.cg.exception.NotPossibleException;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private RestTemplate restTemplate;

	private static final Logger logger = LoggerFactory.getLogger(AppointmentServiceImpl.class);

	String appointmentNotPresent = "No appointment present with this appointment Id";
	String diagnosticCenterNotPresent = "No diagnostic center present with this diagnostic center Id";
	String userNotPresent = "No user present with this user Id";

	@Override
	public Appointments saveAppointments(Appointments a) {
		return appointmentRepository.save(a);
	}

	@Override
	public List<Appointments> findallAppointments() {
		return appointmentRepository.findAll();
	}

	@Override
	public Appointments findAppointmentsbyId(Integer a) {
		return appointmentRepository.findByAppointmentId(a);
	}

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

		System.out.println(userId);
		Integer intObj = new Integer(userId);
		User userExists = restTemplate.getForObject("http://localhost:9008/user/searchUser/" + intObj, User.class);

		if (userExists == null) {
			logger.warn(userNotPresent);
			throw new NoValueFoundException(userNotPresent);
		}

		List<Appointments> appointmentList = appointmentRepository.findByUserId(userId);
		if (appointmentList.isEmpty()) {
			logger.warn("No appointment made for this user ");
			throw new NoValueFoundException("User Has not made any appointment yet");
		}
		// return appointmentList.stream().map(a -> new
		// AppointmentDto(a)).collect(Collectors.toList());
		return appointmentList;

	}

	// Method to fetch appointments for a diagnostic center with diagnostic center
	// Id
	@Override
	public List<Appointments> findAppointmentsByDiagnosticCenterId(int diagnosticCenterId) {
		String url = "http://localhost:9003/admin/diagnosticCenter/searchCenter/" + diagnosticCenterId;
		DiagnosticCenters diagnosticCenterExists = restTemplate.getForObject(url, DiagnosticCenters.class);

		if (diagnosticCenterExists == null) {
			logger.warn(diagnosticCenterNotPresent);
			throw new NoValueFoundException(diagnosticCenterNotPresent);
		}

		List<Appointments> appointmentList = appointmentRepository.findByCenterId(diagnosticCenterId);

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
			throw new NoValueFoundException("Please enter AppointmentId");
		}

		if (appointment.getDatetime().toLocalDate().isBefore(LocalDate.now())) {
			return "Appointment Date is already passed";
		} else if (appointment.getDatetime().toLocalDate() == LocalDate.now()
				&& appointment.getDatetime().toLocalTime().isBefore(LocalTime.now())) {
			return "Appointment Time is already passed";
		}
		appointment.setApproved(-1);
		this.appointmentRepository.save(appointment);

		return "Appointment Cancelled!!";
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

		Integer intObj = new Integer(appointment1.getUserId());
		User userExists = restTemplate.getForObject("http://localhost:9008/user/searchUser/" + intObj, User.class);
		if (userExists == null) {
			logger.warn(userNotPresent);
			throw new NoValueFoundException(userNotPresent);
		}

		// to ensure no user raises 2 appointments
		if (appointmentRepository.findByUserId(appointment1.getUserId()).isEmpty() == Boolean.FALSE) {
			logger.warn(userNotPresent);
			throw new NoValueFoundException("particular user has already raised an appointment");
		}

		Integer intObj1 = new Integer(appointment1.getCenterId());
		String url = "http://localhost:9003/admin/diagnosticCenter/searchCenter/" + intObj1;
		DiagnosticCenters diagnosticCenterExists = restTemplate.getForObject(url, DiagnosticCenters.class);
		if (diagnosticCenterExists == null) {
			logger.warn(diagnosticCenterNotPresent);
			throw new NoValueFoundException(diagnosticCenterNotPresent);
		}

		Integer intObj2 = new Integer(appointment1.getTestId());
		String testurl = "http://localhost:8001/admin/test/searchTest/" + intObj2;

		Tests testExists = restTemplate.getForObject(testurl, Tests.class);
		if (testExists == null) {
			logger.warn(diagnosticCenterNotPresent);
			throw new NoValueFoundException("TestNotPresent");
		}

		appointment.setCenterId(appointment1.getCenterId());
		appointment.setUserId(appointment1.getUserId());
		appointment.setTestId(appointment1.getTestId());
		appointment.setTestName(testExists.getTestName());
		appointment.setCenterName(diagnosticCenterExists.getCenterName());
		appointment.setUserName(userExists.getUserName());

		if (validateDate(appointment1.getDatetime().toLocalDate())) {

			appointment.setDatetime(appointment1.getDatetime());
			appointment = appointmentRepository.save(appointment);
		} else {
			throw new NotPossibleException("sorry we can't book your appointment");
		}

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

		if (statusValue == 1) {
			logger.warn("already approved appointment");
			throw new NotPossibleException("Appointment is already approved");
		} else if (statusValue == -1) {
			logger.warn("Already cancelled appointment");
			throw new NotPossibleException("Appointment is already cancelled");
		} else {
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

		return true;
	}
	
	@Override
	public String export(Integer userId) throws IOException {
		Writer writer = new FileWriter("D:/HeathcarePractice/"+userId+"Report.csv");
		Appointments a=appointmentRepository.findAll().stream().filter(x -> userId.equals(x.getUserId())).findAny().orElse(null);
		String ab;
		if(a.getApproved()==1)
			ab="approved";
		else if(a.getApproved()==0)
			ab="pending";
		else
			ab="Cancelled";
		writer.write("AppointmentId,UserId,UserName,TestId,TestName,CenterId,CenterName,Status,Date\n");
		writer.write(a.getAppointmentId() + "," + a.getUserId() + ","+a.getUserName() +","+ a.getTestId() + ","+a.getTestName() +","
				+ a.getCenterId() + "," +a.getCenterName()+ ","+ ab + "," + a.getDatetime());
		writer.close();
		
		return "exported";
	}
}
