package com.cg.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


import org.springframework.data.annotation.Id;

@Entity
public class DiagnosticCenter {

	@Id
	Integer centerId ;
	
	String centerName ;
	
	@OneToMany
	@Column(name = "Test-List" )
	List<Test> listOfTests ;
	
	@OneToMany
	@Column(name = "Appointment-list")
	List<Appointment> appointmentList ;

	public Integer getCenterId() {
		return centerId;
	}

	public void setCenterId(Integer centerId) {
		this.centerId = centerId;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public List<Test> getListOfTests() {
		return listOfTests;
	}

	public void setListOfTests(List<Test> listOfTests) {
		this.listOfTests = listOfTests;
	}

	public List<Appointment> getAppointmentList() {
		return appointmentList;
	}

	public void setAppointmentList(List<Appointment> appointmentList) {
		this.appointmentList = appointmentList;
	}
	
	
	
	
	
}
