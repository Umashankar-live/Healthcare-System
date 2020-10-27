package com.cg.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class DiagnosticCenters {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int centerId;
	
	private String centerName; 
	
	@ManyToMany(cascade = {CascadeType.MERGE})
	private List<Tests> listOfTests = new ArrayList<>();

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public int getCenterId() {
		return centerId;
	}

	public void setCenterId(int centerId) {
		this.centerId = centerId;
	}

	public List<Tests> getListOfTests() {
		return listOfTests;
	}

	public void setListOfTests(List<Tests> listOfTests) {
		this.listOfTests = listOfTests;
	}

}
