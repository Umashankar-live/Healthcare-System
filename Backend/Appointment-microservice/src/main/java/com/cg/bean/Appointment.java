package com.cg.bean;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "APPOINTMENTS")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer appointmentId;

	@OneToOne
	@JoinColumn(name = "User_ID")
	private User user;

	@OneToOne
	@JoinColumn(name = "Test_ID")
	private Tests test;

	@Column(name = "Status")
	private Boolean approved;

	@Column(name = "Date")
	private LocalDate date;

	@Column(name = "Time")
	private LocalTime time;

	public Appointment() {

	}

	public Tests getTest() {
		return test;
	}

	public void setTest(Tests test) {
		this.test = test;
	}

	public Integer getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getApproved() {
		return approved;
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
