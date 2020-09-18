package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bean.Login;
import com.cg.bean.User;
import com.cg.dao.UserDao;

@Service
public class LoginServiceImpl implements LoginService {

	// The process of injection spring bean dependencies while initializing it
	@Autowired
	private UserDao userDao;

	@Override
	public User validate(Login login) {
		List<User> allUsers = userDao.findAll();
		for (int i = 0; i < allUsers.size(); i++) {
			if (allUsers.get(i).getUserName().equalsIgnoreCase(login.getName())
					&& allUsers.get(i).getPassword().equalsIgnoreCase(login.getPassword())) {
				return allUsers.get(i);
			}
		}
		return null;
	}

}
