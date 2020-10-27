package com.cg.bean;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	private Boolean approved;

	@Column(name = "Date")
	private LocalDate date;

	@Column(name = "Time")
	private LocalTime time;

	public Integer getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Boolean getApproved() {
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

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

}
