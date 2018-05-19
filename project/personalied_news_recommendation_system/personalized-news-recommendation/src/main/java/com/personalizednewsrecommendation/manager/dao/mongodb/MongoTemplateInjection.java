package com.personalizednewsrecommendation.manager.dao.mongodb;

import org.springframework.beans.factory.annotation.*;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoTemplateInjection {
	 @Autowired
	 @Qualifier("mongoTemplate")
	 public MongoTemplate mongoTemplate; 
}
