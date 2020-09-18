package com.cg.service;

import com.cg.bean.Login;
import com.cg.bean.User;

public interface LoginService {

	public User validate(Login login);

}
