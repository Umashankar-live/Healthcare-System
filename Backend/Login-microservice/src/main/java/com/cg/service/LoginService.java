package com.cg.service;

import com.cg.bean.User;

public interface LoginService {

	public User getUserByUserName(String userName);

	public User addUser(User user);

	public User getUserByUserNameAndPassword(String tempUsername, String tempPassword);

}
