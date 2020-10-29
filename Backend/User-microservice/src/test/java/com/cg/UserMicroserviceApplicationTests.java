package com.cg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.bean.User;
import com.cg.dao.UserDao;
import com.cg.service.UserServiceImpl;

@SpringBootTest
class UserMicroserviceApplicationTests {



	@Autowired
	private UserServiceImpl service;

	@MockBean
	private UserDao repository;

	@Test
	public void SearchUserTest() {
		when(repository.findAll()).thenReturn(Stream.of(new User(4, "Admin1", "root","9874563210","abc@gmail.com","Male","User",20),new User(40, "Admin10", "root","9874563210","abc@gmail.com","Male","User1",22)).collect(Collectors.toList()));
		Assertions.assertEquals(0, service.getAllUser().size());
	}

	//private void assertEquals(int i, int size) {
		// TODO Auto-generated method stub
		
   //}

	@Test
	public void addUserTest() {
		User user = new User(4, "Admin1", "root","9874563210","abc@gmail.com","Male","User",20);
		User user1 = new User(40, "Admin10", "root","9874563210","abc@gmail.com","Male","User1",22);
		when(repository.save(user)).thenReturn(user);
		assertEquals(user, service.addUser(user));
	}

	//private void assertEquals(User user,User addUser) {
		// TODO Auto-generated method stub
		
	//}

	@Test
	public void  deleteUserById() {
         User user = new User(4, "Admin1", "root","9874563210","abc@gmail.com","Male","User",20);
		service.deleteUser(user.getUserId());
		verify(repository, times(1)).deleteById(user.getUserId());
	}


}
