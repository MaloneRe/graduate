package com.personalizednewsrecommendation.manager.dao;

import java.util.List;

import com.personalizednewsrecommendation.manager.pojo.Category;

public interface CategoryDAO {
	public void insertCategory(Category category);
	public Category getById(Long id);
	public Category getByName(String name);
	public List<Category> fuzzyQuery(String name);
	public void updateCategory(Category category);
	public List<Category> getAll();
	
}
