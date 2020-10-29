package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bean.User;
import com.cg.dao.UserDao;

@Service
public class LoginServiceImpl implements LoginService {

	// The process of injection spring bean dependencies while initializing it
	@Autowired
	private UserDao userDao;


	@Override
	public User getUserByUserName(String userName) {
		return userDao.findByUserName(userName);
		 
	}

	@Override
	public User addUser(User user) {
		user.setRole("user");
		return userDao.save(user);
	}

	@Override
	public User getUserByUserNameAndPassword(String tempUsername, String tempPassword) {
		return userDao.findByUserNameAndPassword(tempUsername,tempPassword);
	}

}
