package com.cg.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bean.Appointments;
import com.cg.service.AppointmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/appointments")
@CrossOrigin // (origins = "http://localhost:4200")
@Api(value = "HealthCareSystem Appointments using logger and swagger")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	// "2020-10-29T04:10:22.293+00:00"
	// URL=http://localhost:9010/appointments/status/{appointmentId}
	@GetMapping("/status/{appointmentId}")
	@ApiOperation(value = "getStatus", nickname = "getStatus")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Appointments.class),
			@ApiResponse(code = 500, message = "Failure", response = Appointments.class) })
	public String getStatus(@PathVariable Integer appointmentId) {
		return appointmentService.checkAppointmentStatus(appointmentId);
	}

	// This method returns list of appointments made by a particular user whose
	// userId will be specified in the query.

	// URL=http://localhost:9010/appointments/appointmentsByUserId/{userId}
	@GetMapping("/appointmentsByUserId/{userId}")
	@ApiOperation(value = "getAppointmentByUserId", nickname = "getAppointmentByUserId")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Appointments.class),
			@ApiResponse(code = 500, message = "Failure", response = Appointments.class) })
	public List<Appointments> getAppointmentByUserId(@PathVariable int userId) {

		return appointmentService.findAppointmentsByUserId(userId);
	}

	// This method returns list of appointments made for a particular diagnostic
	// Center whose center Id will be specified in the query.

	// URL=http://localhost:9010/appointments/appointmentsByDiagnosticCenterId/{centerId}
	@GetMapping("/appointmentsByDiagnosticCenterId/{centerId}")
	@ApiOperation(value = "getAppointmentsByDiagnosticCenterId", nickname = "getAppointmentsByDiagnosticCenterId")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Appointments.class),
			@ApiResponse(code = 500, message = "Failure", response = Appointments.class) })
	public List<Appointments> getAppointmentsByDiagnosticCenterId(@PathVariable int centerId) {

		return appointmentService.findAppointmentsByDiagnosticCenterId(centerId);
	}

	// This method is used to approve any appointment by providing the appointment
	// URL=http://localhost:9010/appointments/approve/{appointmentId}
	@PutMapping("/approve/{appointmentId}")
	@ApiOperation(value = "approveAppointment", nickname = "approveAppointment")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Appointments.class),
			@ApiResponse(code = 500, message = "Failure", response = Appointments.class) })
	public void approveAppointment(@PathVariable Integer appointmentId) throws Exception {
		appointmentService.approveAppointment(appointmentId);
	}

	// This method is to cancel the appointments based on appointmentId provided.
	// URL=http://localhost:9010/appointments/cancel/{appointmentId}
	@PutMapping("/cancel/{appointmentId}")
	@ApiOperation(value = "cancelAppointment", nickname = "cancelAppointment")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Appointments.class),
			@ApiResponse(code = 500, message = "Failure", response = Appointments.class) })
	public String cancelAppointment(@PathVariable int appointmentId) {
		return this.appointmentService.cancelAppointment(appointmentId);
	}

	/*
	 * This method is used to make an appointment and appointmentDto is passed to
	 * it. On validation it will save the appointment information in the database.
	 */
	/***
	 * 
	 * { "appointmentId": "2", "userId": "3", "centerId": "6", "testId": "1",
	 * "status": "pending", "dateTime": "2020-11-05 09:09:09" }
	 */
	// URL=http://localhost:9010/appointments/makeAppointment
	@PostMapping(value = "/makeAppointment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "makeAppointment", nickname = "makeAppointment")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Appointments.class),
			@ApiResponse(code = 500, message = "Failure", response = Appointments.class) })
	public Appointments makeAppointment(@Valid @RequestBody Appointments appointment) {

		return appointmentService.makeAppointment(appointment);
	}

	// This method returns the boolean value by checking if appointment exists or
	// not for a appointmentId.
	// URL=http://localhost:9010/appointments/checkAppointmentByAppointmentId/{appointmentId}
	@GetMapping("/checkAppointmentByAppointmentId/{appointmentId}")
	@ApiOperation(value = "checkAppointmentExists", nickname = "checkAppointmentExists")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Appointments.class),
			@ApiResponse(code = 500, message = "Failure", response = Appointments.class) })
	public boolean checkAppointmentExists(@PathVariable Integer appointmentId) {
		return appointmentService.checkAppointmentByAppointmentId(appointmentId);
	}

	// This method returns appointment Object by searching the database with the
	// appointmentId provided.
	// URL=http://localhost:9010/appointments/searchAppointmentByAppointmentId/{appointmentId}
	@GetMapping("/searchAppointmentByAppointmentId/{appointmentId}")
	@ApiOperation(value = "searchAppointment", nickname = "searchAppointment")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Appointments.class),
			@ApiResponse(code = 500, message = "Failure", response = Appointments.class) })
	public Appointments searchAppointment(@PathVariable Integer appointmentId) {

		return appointmentService.searchAppointmentByAppointmentId(appointmentId);

	}

	@GetMapping("/userreport/{userId}")
	@ApiOperation(value = "getReport", nickname = "getReport")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Appointments.class),
			@ApiResponse(code = 500, message = "Failure", response = Appointments.class) })
	public String getReport(@PathVariable Integer userId) throws IOException {
		return this.appointmentService.export(userId);
	}

	// URL=http://localhost:9010/appointments/getAllAppointments
	@GetMapping("/getAllAppointments")
	@ApiOperation(value = "getAllAppointment", nickname = "AllUserList")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Appointments.class),
			@ApiResponse(code = 500, message = "Failure", response = Appointments.class) })
	public List<Appointments> getAllAppointment() {

		return appointmentService.findallAppointments();
	}

}
