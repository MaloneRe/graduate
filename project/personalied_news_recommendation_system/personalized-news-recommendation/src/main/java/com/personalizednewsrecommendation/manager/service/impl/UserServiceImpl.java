package com.personalizednewsrecommendation.manager.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personalizednewsrecommendation.manager.dao.UserDAO;
import com.personalizednewsrecommendation.manager.pojo.User;
import com.personalizednewsrecommendation.manager.service.UserService;

@Service("userSerivce")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public Long register(User user) {
		userDAO.insertUser(user);
		System.out.println("this is userservice: user'id is :"+ user.getId());
		/*return userDAO.getIdByName(user.getName());*/
		return user.getId();
	}

	@Override
	public User login(User user) {
		return userDAO.getUserByName(user.getName());
	}

	@Override
	public String getName(Long id) {
		return userDAO.getUserById(id).getName();
	}

	@Override
	public User getUser(Long id) {
		
		return userDAO.getUserById(id);
	}
   
}
