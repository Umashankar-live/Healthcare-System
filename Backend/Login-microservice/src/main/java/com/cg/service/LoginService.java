package com.cg.service;

import com.cg.bean.Login;
import com.cg.bean.UsersOfSystem;

public interface LoginService {

	public UsersOfSystem validate(Login login);
	public UsersOfSystem getUserByUserName(String userName);
	public UsersOfSystem addUser(UsersOfSystem user);
	public UsersOfSystem getUserByUserNameAndPassword(String tempUsername, String tempPassword);

}
