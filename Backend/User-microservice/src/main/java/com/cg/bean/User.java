package com.cg.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@Entity
@Table(name = "User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;

	// private List<DiagnosticCenter> centerList;

	@NotEmpty(message = "User name is required")
	@Pattern(regexp = "^[A-Z][A-Za-z0-9_-]{3,19}$", message = "Enter Valid Username:" )
    //@Max(5)
    private String userName;

    @NotEmpty(message = "Password is required")
    //At least one upper case English letter,At least one lower case English letter,At least one digit,At least one special character,Minimum eight in length
    //@Pattern(regexp ="^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,}$", message = "Invalid Password")
    private String password;

    @NotEmpty(message = "Phone number is required")
	@Pattern(regexp = "[7-9][0-9]{9}" , message = "Enter 10 digit with starting number between 7 and 9")
	private String mobileNo;

	@NotEmpty(message = "Email is required")
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$" , message = "Enter Valid Email")
    @Email
	private String email;

	private String gender;
	
	private String role;

	private int age;
	
	public User() {
		
	}

	public User(int userId, String userName, String password, String mobileNo, String email, String gender, String role,
			int age) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.mobileNo = mobileNo;
		this.email = email;
		this.gender = gender;
		this.role = role;
		this.age = age;
	}



	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
