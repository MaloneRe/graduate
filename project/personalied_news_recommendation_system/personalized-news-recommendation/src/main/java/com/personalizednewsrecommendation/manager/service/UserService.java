package com.personalizednewsrecommendation.manager.service;

import com.personalizednewsrecommendation.manager.pojo.User;

public interface UserService {
	public Long register(User user);
	public User login(User user);
	public String getName(Long id);
}
