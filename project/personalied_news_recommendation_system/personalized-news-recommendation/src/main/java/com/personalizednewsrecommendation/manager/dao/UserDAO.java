package com.personalizednewsrecommendation.manager.dao;

import org.springframework.stereotype.Component;

import com.personalizednewsrecommendation.manager.pojo.User;


public interface UserDAO {
	
	public void insertUser(User user);
	public Long getIdByName(String name);
	public User getUserByName(String name);
	public User getUserById(Long id);
}
