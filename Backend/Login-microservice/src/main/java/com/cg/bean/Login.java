package com.cg.bean;

public class Login {
	private String username;
	private String password;

	public Login() {
		
	}

	public Login(String name, String password) {
		super();
		this.username = name;
		this.password = password;
	}

	public String getName() {
		return username;
	}

	public void setName(String name) {
		this.username = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
