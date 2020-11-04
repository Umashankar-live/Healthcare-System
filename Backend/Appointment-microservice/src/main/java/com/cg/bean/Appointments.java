package com.cg.bean;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Appointments {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer appointmentId;

	@NotNull(message = "Cannot generate appointment without UserId")
	@Column(name = "User_ID")
	private int userId;

	@Column(name = "User_NAME")
	private String userName;

	@NotNull(message = "Cannot generate appointment without TestId")
	@Column(name = "Test_ID")
	private int testId;

	@Column(name = "Test_NAME")
	private String testName;

	@NotNull(message = "Cannot generate appointment without DiagnosticCenterId")
	@Column(name = "CENTER_ID")
	private int centerId;

	@Column(name = "CENTER_NAME")
	private String centerName;

	@Column(name = "Status")
	private String status;

	@NotNull(message = "Please specify a date for appointment")
	@Column(name = "Date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateTime;

	public Integer getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
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

	public LocalDateTime getDatetime() {
		return dateTime;
	}

	public void setDatetime(LocalDateTime datetime) {
		this.dateTime = datetime;
	}

	public Appointments() {

	}

	public Appointments(Integer appointmentId, int userId, int testId, int centerId, String status,
			LocalDateTime dateTime) {
		super();
		this.appointmentId = appointmentId;
		this.userId = userId;
		this.testId = testId;
		this.centerId = centerId;
		this.status = status;
		this.dateTime = dateTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

}
