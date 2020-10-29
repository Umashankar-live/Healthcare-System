package com.cg.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

	@Override
	public Appointments makeAppointment(Appointments appoint) {

		Appointments appointment = new Appointments();
		appointment.setApproved(0); // 0 is pending , so by default it will be pending

		String diagnosticUrl = "http://localhost:9003/admin/diagnosticCenter/searchCenter/" + appoint.getCenterId();
		DiagnosticCenters center = restTemplate.getForObject(diagnosticUrl, DiagnosticCenters.class);
		if (center == null) {

			throw new NoValueFoundException("No diagnostic center present with this diagnostic center Id");
		} else {
			appointment.setCenterId(appoint.getCenterId());
			appointment.setCenterName(center.getCenterName());
		}
		String userUrl = "http://localhost:9001/user/searchUser/" + appoint.getUserId();
		User user = restTemplate.getForObject(userUrl, User.class);
		if (user == null) {
			throw new NoValueFoundException("User Not Present");
		} else {
			appointment.setUserId(appoint.getUserId());
			appointment.setUserName(user.getUserName());
		}
		String testUrl = "http://localhost:8001/admin/test/searchTest/" + appoint.getTestId();
		Tests test = restTemplate.getForObject(testUrl, Tests.class);
		if (test == null) {

			throw new NoValueFoundException("No Test Center present with this Center Id");
		} else {
			appointment.setTestId(appoint.getTestId());
			appointment.setTestName(test.getTestName());
		}
		System.out.println(appointment.getDate()+appointment.getCenterName());
//		if (validateDate(appointment.getDate())) {
////			//List<LocalTime> allSlots = getAvailableSlots(appointment.getTestId(),
////			//		appointment.getDate());
////			if (allSlots.contains(appointment.getTime())) {
////				appointment.setDate(appoint.getDate());
////				appointment.setTime(appoint.getTime());
////				appointment = appointmentRepository.save(appointment);
////			} else {
////				throw new NotPossibleException("sorry we can't book your appointment");
////			}
//			appointment.setDate(appoint.getDate());
//			appointment.setTime(appoint.getTime());
//			appointment = appointmentRepository.save(appointment);
//		}else {
//			throw new NotPossibleException("sorry we can't book your appointment");
//		}

	
		appointment.setDate(appoint.getDate());
//		appointment.setTime(appoint.getTime());
		appointment = appointmentRepository.save(appointment);

		return appointment;

	}

	// Method to validate date according to appointments. (Validations on Date)
	@Override
	public boolean validateDate(LocalDate date) {
		if (!date.isBefore(LocalDate.now().plusDays(30))) {
			throw new NotPossibleException("Please select date of today or any day within 30 days of today.");
		} else if (date.isBefore(LocalDate.now())) {

			throw new NotPossibleException("Please select date of today or any day within 30 days of today.");
		} else if (date.getDayOfWeek().toString().equalsIgnoreCase("SUNDAY")) {
			throw new NotPossibleException("we are closed on Sunday!");
		}

		return true;
	}

//	// Method to find available slots of booking for a particular test center on a
//	// particular day.
//	@Override
//	public List<LocalTime> getAvailableSlots(int testId, LocalDate date) {
//		List<LocalTime> allSlots = new ArrayList<>();
//		allSlots.add(LocalTime.of(9, 00));
//		allSlots.add(LocalTime.of(9, 30));
//		allSlots.add(LocalTime.of(10, 00));
//		allSlots.add(LocalTime.of(10, 30));
//		allSlots.add(LocalTime.of(11, 00));
//		allSlots.add(LocalTime.of(11, 30));
//		allSlots.add(LocalTime.of(12, 00));
//		allSlots.add(LocalTime.of(12, 30));
//		allSlots.add(LocalTime.of(14, 00));
//		allSlots.add(LocalTime.of(14, 30));
//		allSlots.add(LocalTime.of(15, 00));
//		allSlots.add(LocalTime.of(15, 30));
//		allSlots.add(LocalTime.of(16, 00));
//		allSlots.add(LocalTime.of(16, 30));
//		allSlots.add(LocalTime.of(17, 00));
//		allSlots.add(LocalTime.of(17, 30));
//		allSlots.add(LocalTime.of(18, 00));
//		allSlots.add(LocalTime.of(18, 30));
//		allSlots.add(LocalTime.of(19, 00));
//		allSlots.add(LocalTime.of(19, 30));
//
//		List<Appointments> listOfAppointments = appointmentRepository.findAll();
//		List<Appointments> toRemoveAppointments = new ArrayList<>();
//		Iterator<Appointments> itr = listOfAppointments.iterator();
//		if (validateDate(date)) {
//			while (itr.hasNext()) {
//				Appointments appointment = itr.next();
//				if (!appointment.getDate().isEqual(date)) {
//					toRemoveAppointments.add(appointment);
//				} else if (appointment.getTestId() != testId) {
//					toRemoveAppointments.add(appointment);
//				} else if (Boolean.FALSE.equals(appointment.getApproved())) {
//					toRemoveAppointments.add(appointment);
//				}
//			}
//		}
//		listOfAppointments.removeAll(toRemoveAppointments);
//		itr = listOfAppointments.iterator();
//		while (itr.hasNext()) {
//			Appointments appointment = itr.next();
//			allSlots.remove(appointment.getTime());
//		}
//
//		List<LocalTime> toRemoveSlots = new ArrayList<>();
//		Iterator<LocalTime> iterator = allSlots.iterator();
//		while (iterator.hasNext()) {
//			LocalTime time = iterator.next();
//			if (!date.isAfter(LocalDate.now()))
//				if (LocalTime.now().isAfter(time)) {
//					toRemoveSlots.add(time);
//				}
//		}
//		allSlots.removeAll(toRemoveSlots);
//
//		return allSlots;
//
//	}

}
