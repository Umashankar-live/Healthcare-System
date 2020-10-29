package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.bean.User;
import com.cg.dao.UserDao;

@Service
public class UserServiceImpl implements UserServiceInterface {

	@Autowired
	private UserDao userDao;

	@Override
	public User addUser(User user) {
		return this.userDao.save(user);
	}

	@Override
	public void deleteUser(Integer userId) {
		this.userDao.deleteById(userId);

	}

	@Override
	public User searchUser(Integer userId) {
		return this.userDao.findAll().stream().filter(x -> userId.equals(x.getUserId())).findAny().orElse(null);
	}

	@Override
	public User updateUser(User user) {

		return this.userDao.save(user);
	}

}
