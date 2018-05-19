package com.personalizednewsrecommendation.manager.service.impl;

import com.personalizednewsrecommendation.manager.service.CategoryService;

import com.personalizednewsrecommendation.manager.dao.*;
import com.personalizednewsrecommendation.manager.pojo.*;
import com.personalizednewsrecommendation.manager.service.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.*;

import java.io.IOException;
import java.util.*;

import org.springframework.stereotype.*;
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDAO categoryDAO; 
	
	@Override
	public Long insertCategory(String name) {
		Category category =new Category();
		category.setName(name);
		categoryDAO.insertCategory(category);	
		return category.getId();
	}

}
