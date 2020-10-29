package com.cg.service;

import com.cg.bean.User;

public interface UserServiceInterface {

	public User addUser(User user);

	public void deleteUser(Integer userId);

	public User searchUser(Integer userId);

	public User updateUser(User user);

}
