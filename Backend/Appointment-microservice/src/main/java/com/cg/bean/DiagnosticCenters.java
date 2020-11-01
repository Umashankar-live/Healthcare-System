package com.cg.bean;

import java.util.ArrayList;
import java.util.List;

public class DiagnosticCenters {

	private int centerId;

	private String centerName;

	private List<Tests> listOfTests = new ArrayList<>();

	public DiagnosticCenters(int centerId, String centerName) {
		super();
		this.centerId = centerId;
		this.centerName = centerName;
	}

	public DiagnosticCenters() {

	}

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
