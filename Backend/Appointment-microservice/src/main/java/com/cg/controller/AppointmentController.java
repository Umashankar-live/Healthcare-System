package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bean.Appointments;
import com.cg.service.AppointmentServiceImpl;


@RestController
@RequestMapping("/appointments")
@CrossOrigin(origins = "http://localhost:4200")
public class AppointmentController {

	@Autowired
	private AppointmentServiceImpl appointmentService;
	
	@PostMapping("/makeAppointment")   
	public Appointments makeAppointment(@RequestBody Appointments appointmentDto) {
		return appointmentService.makeAppointment(appointmentDto);
	}
}
