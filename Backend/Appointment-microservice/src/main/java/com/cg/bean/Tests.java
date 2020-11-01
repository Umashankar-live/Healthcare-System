package com.cg.bean;

public class Tests {

	private int testId;

	private String testName;

	public Tests() {

	}

	public Tests(int testId, String testName) {
		super();
		this.testId = testId;
		this.testName = testName;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

}
