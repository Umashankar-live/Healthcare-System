package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.cg.bean.User;
import com.cg.dao.UserDao;
import com.cg.exception.NoValueFoundException;
import com.cg.exception.NotPossibleException;

@Service
public class UserServiceImpl implements UserServiceInterface {

	@Autowired
	private UserDao userDao;

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
			throw new NotPossibleException("User Deletion operation is not possible...");
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
		if(this.userDao.findAll() == null)
			 throw new NoValueFoundException("There is no user in Table...");
		return this.userDao.findAll();
	}

}
