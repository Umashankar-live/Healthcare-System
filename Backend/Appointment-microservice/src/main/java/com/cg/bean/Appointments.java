package com.cg.bean;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class Appointments {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer appointmentId;

	@Column(name = "User_ID")
	private int userId;

	@Column(name = "Test_ID")
	private int testId;

	@Column(name = "CENTER_ID")
	private int centerId;

	@Column(name = "Status")
	private int approved;

	@Column(name = "Date")
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone="(UTC+05:30)Chennai,Kolkata,Mumbai,New Delhi")
	private LocalDateTime dateTime;

	//@Column(name = "Time")
	//private LocalTime time;

	public Integer getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}

	public int getApproved() {
		return approved;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public int getCenterId() {
		return centerId;
	}

	public void setCenterId(int centerId) {
		this.centerId = centerId;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}

	public LocalDateTime getDatetime() {
		return dateTime;
	}

	public void setDatetime(LocalDateTime datetime) {
		this.dateTime = datetime;
	}

	

	
}
