package com.cg.dto;

import java.time.LocalDateTime;

import com.cg.bean.Appointments;

public class AppointmentDto {

	private int userId;
	private Integer appointmentId;         //should be BigInteger acc to doc 
	private int testId;
	private LocalDateTime dateTime;
	private int approved;
	private int centerId;
	public AppointmentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	//Constructor for converting entity to dto class
		public AppointmentDto(Appointments appointment) {
			this.appointmentId=appointment.getAppointmentId();
			this.dateTime=appointment.getDatetime();
			this.userId=appointment.getUserId();
			this.testId=appointment.getTestId();
			this.approved=appointment.getApproved();
			this.centerId=appointment.getCenterId();
			
		}

	public AppointmentDto(int userId, int testId, LocalDateTime datetime, int approved, int centerId) {
		super();
		this.userId = userId;
		this.testId = testId;
		this.dateTime = datetime;
		this.approved = approved;
		this.centerId = centerId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Integer getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	
	public LocalDateTime getDatetime() {
		return dateTime;
	}

	public void setDatetime(LocalDateTime datetime) {
		this.dateTime = datetime;
	}

	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}

	public int getCenterId() {
		return centerId;
	}

	public void setCenterId(int diagnosticCenterId) {
		this.centerId = diagnosticCenterId;
	}

	
	@Override
	public boolean equals(Object obj) {
		if(obj==null||obj.getClass()!=this.getClass())
			return false;
		AppointmentDto appointment=(AppointmentDto) obj;
		return appointment.getAppointmentId()==this.appointmentId;
	}
	
		
		


}
