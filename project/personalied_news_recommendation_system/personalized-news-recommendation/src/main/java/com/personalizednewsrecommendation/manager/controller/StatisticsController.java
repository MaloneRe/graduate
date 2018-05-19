package com.personalizednewsrecommendation.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value = "/statistics", tags = "统计文档", hidden = false, consumes = "application/json", produces = "application/json,application/xml", description = "项目StatisticsController接口")
public class StatisticsController {
	
	@RequestMapping(value = "/statistics", method = RequestMethod.GET)
	@ApiOperation(value = "推荐算法的相关比较", notes = "返回json数据"
	, httpMethod = "GET"
	, produces = "application/json, application/xml"
	, consumes = "application/json, application/xml"
	, response = org.springframework.web.servlet.ModelAndView.class)
	@ResponseBody
	public Object statistcs(){
		
		return null;
	}
}
