package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.bean.User;
import com.cg.dao.UserDao;
import com.cg.exception.NoValueFoundException;
import com.cg.exception.NotPossibleException;

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
		User user=this.userDao.findAll().stream().filter(x -> userId.equals(x.getUserId())).findAny().orElse(null);
		if(user==null) {
			throw new NotPossibleException("This operation is not possible");
		}
		else
		this.userDao.deleteById(userId);

	}

	@Override
	public User searchUser(Integer userId) {
		User user = this.userDao.findAll().stream().filter(x -> userId.equals(x.getUserId())).findAny().orElse(null);
		if(user==null) {
			throw new NoValueFoundException("No such User is available");
		}
		else
		return user;
	}

	@Override
	public User updateUser(User user) {

		return this.userDao.save(user);
	}

	@Override
	public List<User> getAllUser() {
		return this.userDao.findAll();
	}

}
