package com.cg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.bean.Appointments;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointments, Integer> {
	//To see the pending status wrt appointmentId
	boolean findApprovedByAppointmentId(Integer appointmentId);
	
	//to find appointments by appointmentId
   Appointments findByAppointmentId(Integer appointmentId);
		
	
	//To extract list of appointments requests per user
	Appointments findByUserId(int userId);
	
	//To find list of appointment requests wrt to diagnostic center
	List<Appointments> findByCenterId(int centerId);
	
	//To extract list of appointments requests per test
		List<Appointments> findByTestId(int testId);
	
	/*
	 * //To find date and time wrt to Appointment ID LocalDateTime
	 * findDateTimeByAppointmentId(Integer appointmentId);
	 * 
	 * //To find list of all pending appointments wrt to a diagnostic center
	 * //List<Appointments> checkPendingAppointmentForDiagnosticCenter(int
	 * centerId);
	 * 
	 * //List<Appointments> checkPendingAppointmentForTestCenter(int testId);
	 */}
