package com.cg.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	/***
	 * This Function is used to add User
	 */
	@Override
	public User addUser(User user) {
		return this.userDao.save(user);
		
	}

	/***
	 * This function is used to delete user
	 */
	@Override
	public void deleteUser(Integer userId) {
		User user=this.userDao.findAll().stream().filter(x -> userId.equals(x.getUserId())).findAny().orElse(null);
		if(user==null) {
			logger.warn("check the userId is correct or not");
			throw new NotPossibleException("Given ID is not present so User Deletion operation is not possible...");
		}
		else
		this.userDao.deleteById(userId);

	}

	/***
	 * This Function is used to search the user
	 */
	@Override
	public User searchUser(Integer userId) {
		User user = this.userDao.findAll().stream().filter(x -> userId.equals(x.getUserId())).findAny().orElse(null);
		if(user==null) {
			logger.warn("check the userId is correct or not");
			throw new NoValueFoundException("No such User is available...");
		}
		else
		return user;
	}

	/***
	 * This Function is used to update the user
	 */
	@Override
	public User updateUser(User user) {
        if(this.userDao.save(user) == null)
			throw new NotPossibleException("Cannot update this user...");
		return this.userDao.save(user);
	}

	/***
	 * This function is used to get all user 
	 */
	@Override
	public List<User> getAllUser() {
		if(this.userDao.findAll() == null) {
			 logger.warn("Check if database is empty or not");
			 throw new NoValueFoundException("There is no user in Table...");
		}
		return this.userDao.findAll();
	}

}
