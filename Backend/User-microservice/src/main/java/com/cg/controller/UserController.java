package com.cg.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bean.User;
import com.cg.service.UserServiceInterface;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/user")
@Validated
public class UserController {

	@Autowired
	private UserServiceInterface userService;

	//http://localhost:9008/user/addUser
	@RequestMapping(value = "/addUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public User addUser(@Valid @RequestBody User user) {
		user.setRole("user");
		return this.userService.addUser(user);
	}
	

	//http://localhost:9008/user/getAllUser
	@GetMapping(value = "/getAllUser")
	public List<User> getAllTest() {
		return this.userService.getAllUser();
	}

	//http://localhost:9008/user/deleteUser/12
	@DeleteMapping(value = "/deleteUser/{userId}")
	public void deleteUser(@PathVariable Integer userId) {
		this.userService.deleteUser(userId);
	}

	//http://localhost:9008/user/searchUser/1
	@GetMapping(value = "/searchUser/{userId}")
	public User searchUserById(@PathVariable Integer userId) {
		return this.userService.searchUser(userId);
	}
	
	//http://localhost:9008/user/updateUser/1
	@RequestMapping(value = "/updateUser/{userId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public User updateUser(@RequestBody User user,@PathVariable Integer userId) {
		User newUser = this.userService.searchUser(userId);
		newUser.setUserName(user.getUserName());
		newUser.setPassword(user.getPassword());
		newUser.setMobileNo(user.getMobileNo());
		newUser.setEmail(user.getEmail());
		newUser.setGender(user.getGender());
		newUser.setRole("user");
		newUser.setAge(user.getAge());
		return this.userService.updateUser(newUser);
		
	}
	
	
}