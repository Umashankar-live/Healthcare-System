package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bean.Login;
import com.cg.bean.User;
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

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public User loginUser(@RequestBody Login user) {
		String tempUsername = user.getUserName();
		String tempPassword = user.getPassword();
		User userObj ;
		if (tempUsername != null && tempPassword != null) {
			userObj = service.getUserByUserNameAndPassword(tempUsername, tempPassword);
			return userObj ;
		}
			
	   return null ;

	}

}
