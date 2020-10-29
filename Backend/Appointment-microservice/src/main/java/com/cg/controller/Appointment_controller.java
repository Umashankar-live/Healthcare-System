package com.cg.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bean.Appointments;
import com.cg.service.Appointment_Service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
//@RequestMapping("/appointments")
@CrossOrigin // (origins = "http://localhost:4200")
@Api(value = "HealthCareSystem Appointments using logger and swagger")
public class Appointment_controller {

	@Autowired
	private Appointment_Service appointmentService;

	// "2020-10-29T04:10:22.293+00:00"
	/*
	 * This method is used to check status of the appointment based on appointmentId
	 * provided in the form of query. Status is having 3 values. '1' for approved
	 * appointments, '-1', for cancelled appointments,'0' for pending.
	 */
	@GetMapping("/status/{appointmentId}")
	@ApiOperation(value = "getStatus", nickname = "getStatus")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Appointments.class),
			@ApiResponse(code = 500, message = "Failure", response = Appointments.class) })
	public String getStatus(@PathVariable Integer appointmentId) {
		return appointmentService.checkAppointmentStatus(appointmentId);
	}

	// This method returns list of appointments made by a particular user whose
	// userId will be specified in the query.
	@GetMapping("/appointments-by-UserId/{userId}")
	@ApiOperation(value = "getAppointmentByUserId", nickname = "getAppointmentByUserId")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Appointments.class),
			@ApiResponse(code = 500, message = "Failure", response = Appointments.class) })
	public List<Appointments> getAppointmentByUserId(@PathVariable int userId) {

		return appointmentService.findAppointmentsByUserId(userId);
	}

	// This method returns list of appointments made for a particular diagnostic
	// Center whose center Id will be specified in the query.
	@GetMapping("/appointments-by-DiagnosticCenterId/{centerId}")
	@ApiOperation(value = "getAppointmentsByDiagnosticCenterId", nickname = "getAppointmentsByDiagnosticCenterId")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Appointments.class),
			@ApiResponse(code = 500, message = "Failure", response = Appointments.class) })
	public List<Appointments> getAppointmentsByDiagnosticCenterId(@PathVariable int centerId) {

		return appointmentService.findAppointmentsByDiagnosticCenterId(centerId);
	}

	// This method is used to approve any appointment by providing the appointment
	// Id.
	@PutMapping("/approve/{appointmentId}")
	@ApiOperation(value = "approveAppointment", nickname = "approveAppointment")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Appointments.class),
			@ApiResponse(code = 500, message = "Failure", response = Appointments.class) })
	public void approveAppointment(@PathVariable Integer appointmentId) {
		appointmentService.approveAppointment(appointmentId);
	}

	// This method is to cancel the appointments based on appointmentId provided.
	@GetMapping("/cancel/{appointmentId}")
	@ApiOperation(value = "cancelAppointment", nickname = "cancelAppointment")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Appointments.class),
			@ApiResponse(code = 500, message = "Failure", response = Appointments.class) })
	public Map<String, String> cancelAppointment(@PathVariable String appointmentId) {
		return Collections.singletonMap("result",
				appointmentService.cancelAppointment(Integer.valueOf((int) Long.parseLong(appointmentId))));
	}

	/*
	 * This method is used to make an appointment and appointmentDto is passed to
	 * it. On validation it will save the appointment information in the database.
	 */
	@PostMapping(value = "/makeAppointment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "makeAppointment", nickname = "makeAppointment")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Appointments.class),
			@ApiResponse(code = 500, message = "Failure", response = Appointments.class) })
	public Appointments makeAppointment(@RequestBody Appointments appointmentDto) {
		return appointmentService.makeAppointment(appointmentDto);
	}

	// This method returns the boolean value by checking if appointment exists or
	// not for a appointmentId.
	@GetMapping("/check-appointment-by-appointmentId/{appointmentId}")
	@ApiOperation(value = "checkAppointmentExists", nickname = "checkAppointmentExists")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Appointments.class),
			@ApiResponse(code = 500, message = "Failure", response = Appointments.class) })
	public boolean checkAppointmentExists(@PathVariable Integer appointmentId) {
		return appointmentService.checkAppointmentByAppointmentId(appointmentId);
	}

	// This method returns appointment Object by searching the database with the
	// appointmentId provided.
	@GetMapping("/search-appointment-by-appointmentId/{appointmentId}")
	@ApiOperation(value = "searchAppointment", nickname = "searchAppointment")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Appointments.class),
			@ApiResponse(code = 500, message = "Failure", response = Appointments.class) })
	public Appointments searchAppointment(@PathVariable Integer appointmentId) {

	//	return appointmentService
		//		.searchAppointmentByAppointmentId(Integer.valueOf((int) Long.parseLong(appointmentId)));

		return appointmentService
				.searchAppointmentByAppointmentId(appointmentId);

	}

}
