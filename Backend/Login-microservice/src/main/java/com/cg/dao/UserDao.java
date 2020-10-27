package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.bean.UsersOfSystem;

@Repository
public interface UserDao extends JpaRepository<UsersOfSystem, Integer> {

	UsersOfSystem findByUserName(String userName);

	UsersOfSystem findByUserNameAndPassword(String tempUsername, String tempPassword);

}
