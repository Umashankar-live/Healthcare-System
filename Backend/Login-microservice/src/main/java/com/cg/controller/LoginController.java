package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bean.Login;
import com.cg.bean.UsersOfSystem;
import com.cg.service.LoginService;

/*
 * @Autowired - The process of injection spring bean dependencies while initializing it
 * @RequestMapping - for configuring URI mapping in controller handler methods 
 * @PathVariable -  for mapping dynamic values from the URI to handler method arguments.
 * @CrossOrigin - enables cross-origin resource sharing only for this specific method. By default, its allows all origins, 
 *                all headers, and the HTTP methods specified in the @RequestMapping annotation
 * @ResponseBody - annotation maps the HttpRequest body to a transfer or domain object
 */

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/healthcaresystem")
public class LoginController {

	
	@Autowired
	private LoginService service;

	// http://localhost:8037/login/validate
//	@RequestMapping(value = "/validate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public UsersOfSystem validate(@RequestBody Login login) {
//		UsersOfSystem user = service.validate(login);
//		return user;
//	}

	@RequestMapping(value="/registeruser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public UsersOfSystem addUser(@RequestBody UsersOfSystem user) throws Exception {
		
		String tempUserName=user.getUserName();
		if(tempUserName!=null && !"".equals(tempUserName)){
			UsersOfSystem userObj=service.getUserByUserName(tempUserName);
			if(userObj!=null) {
				throw new Exception("User with username "+tempUserName+" already exist!");
			}
			
		}
		UsersOfSystem userObj=null;
		userObj=service.addUser(user);
		
		return userObj;
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public UsersOfSystem loginUser(@RequestBody UsersOfSystem user) throws Exception {
		String tempUsername=user.getUserName();
		String tempPassword=user.getPassword();
		UsersOfSystem userObj=null;
		if(tempUsername != null && tempPassword!= null) {
			userObj=service.getUserByUserNameAndPassword(tempUsername, tempPassword);
		}
		if(userObj==null) {
			throw new Exception("Bad Credentials");
		}
		
		return userObj;
	}




}
