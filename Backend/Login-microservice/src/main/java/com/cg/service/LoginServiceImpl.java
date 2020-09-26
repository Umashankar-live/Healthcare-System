package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bean.Login;
import com.cg.bean.UsersOfSystem;
import com.cg.dao.UserDao;

@Service
public class LoginServiceImpl implements LoginService {

	// The process of injection spring bean dependencies while initializing it
	@Autowired
	private UserDao userDao;

	@Override
	public UsersOfSystem validate(Login login) {
		List<UsersOfSystem> allUsers = userDao.findAll();
		for (int i = 0; i < allUsers.size(); i++) {
			if (allUsers.get(i).getUserName().equalsIgnoreCase(login.getName())
					&& allUsers.get(i).getPassword().equalsIgnoreCase(login.getPassword())) {
				return allUsers.get(i);
			}
		}
		return null;
	}

	@Override
	public UsersOfSystem getUserByUserName(String userName) {
		// TODO Auto-generated method stub
		return userDao.findByUserName(userName);
		 
	}

	@Override
	public UsersOfSystem addUser(UsersOfSystem user) {
		// TODO Auto-generated method stub
		user.setRole("Customer");
		return userDao.save(user);
	}

	@Override
	public UsersOfSystem getUserByUserNameAndPassword(String tempUsername, String tempPassword) {
		// TODO Auto-generated method stub
		return userDao.findByUserNameAndPassword(tempUsername,tempPassword);
	}

}
