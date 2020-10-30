package com.cg.service;

import java.util.List;

import com.cg.bean.User;

public interface UserServiceInterface {

	public User addUser(User user);

	public void deleteUser(Integer userId);

	public User searchUser(Integer userId);
	
	public List<User> getAllUser();

	public User updateUser(User user);

}
