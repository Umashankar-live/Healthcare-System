package com.cg.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

@Entity
@Table(name = "Dignostic-Centers")
public class DiagnosticCenter {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer centerId;

	@Column(name = "Center-Name")
	private String centerName;

	@OneToMany
	private List<Tests> listOfTests =new ArrayList <Tests> ();

//	@OneToMany
//	@JoinColumn(name = "Appointment-list")
//	private List<Appointment> appointmentList;
	
	public DiagnosticCenter() {
		
	}

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

	public List<Tests> getListOfTests() {
		return listOfTests;
	}

	public void setListOfTests(List<Tests> listOfTests) {
		this.listOfTests = listOfTests;
	}

//	public List<Appointment> getAppointmentList() {
//		return appointmentList;
//	}
//
//	public void setAppointmentList(List<Appointment> appointmentList) {
//		this.appointmentList = appointmentList;
//	}

}