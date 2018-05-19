package com.personalizednewsrecommendation.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.personalizednewsrecommendation.manager.pojo.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.*;
import org.springframework.web.bind.annotation.*;

@Controller
@Api(value = "/test", tags = "test接口")
public class test {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ApiOperation(value = "返回视图", notes = "返回jsp页面", httpMethod = "GET", response =org.springframework.web.servlet.ModelAndView.class)
	public String testMethod(){
		return "index";
	}
	@RequestMapping(value = "/test1", method = RequestMethod.GET)
	@ApiOperation(value = "返回视图", notes = "返回jsp页面", httpMethod = "GET", response =org.springframework.web.servlet.ModelAndView.class)
	@ResponseBody
	public java.util.Map<String, String> test1Method(){
		Map<String, String> map = new HashMap<>();
		map.put("key0", "value0");
		map.put("key1", "value1");
		map.put("key2", "value2");
		return map;
	}
	@RequestMapping(value = "/testuser", method = RequestMethod.POST)
	@ApiOperation(value = "返回视图", notes = "返回jsp页面", httpMethod = "POST", response =org.springframework.web.servlet.ModelAndView.class)
	public String testUser(@RequestBody User user){
		System.out.println(user.getName()+"\n"+user.getPassword());
		
		return "index";
	}
}
