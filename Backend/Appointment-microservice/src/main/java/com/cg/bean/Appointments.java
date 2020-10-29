package com.cg.bean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Appointments {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer appointmentId;

	@Column(name = "User_ID")
	private int userId;

	@Column(name = "CENTER_ID")
	private int centerId;
	
	private String centerName;
	
	private String userName;
	
	private String testName;
	
	private int testId;

	@Column(name = "Status")
	private int approved;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "START_DATE")
	@DateTimeFormat(style = "yyyy-mm-dd")
	private Date date;

	@Temporal(value = TemporalType.TIME)
	@Column(name = "TIME")
	@DateTimeFormat(style = "hh:mm")

	private Date time;
//	@Column(name = "Time")
//	private LocalTime time;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

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

	public int getCenterId() {
		return centerId;
	}

	public void setCenterId(int centerId) {
		this.centerId = centerId;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

//	public LocalTime getTime() {
//		return time;
//	}
//
//	public void setTime(LocalTime time) {
//		this.time = time;
//	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
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

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}
	
	

}
