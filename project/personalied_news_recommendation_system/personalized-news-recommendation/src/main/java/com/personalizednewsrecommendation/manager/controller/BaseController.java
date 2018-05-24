package com.personalizednewsrecommendation.manager.controller;

import java.util.*;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.personalizednewsrecommendation.manager.service.*;
import com.personalizednewsrecommendation.manager.spider.NewsWebURL;
import com.personalizednewsrecommendation.manager.pojo.*;
import com.personalizednewsrecommendation.manager.dao.*;
import com.personalizednewsrecommendation.manager.log.LogInterceptor;

import javax.annotation.*;
import javax.servlet.http.HttpSession;

import io.swagger.annotations.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


import com.alibaba.fastjson.*;

import com.personalizednewsrecommendation.manager.algorithm.mahout.*;


@Controller
@Api(value = "/recnews/api", tags = "个性化新闻推荐接口文档", hidden = false, consumes = "application/json", produces = "application/json,application/xml", description = "项目controller接口")
public class BaseController {

	private static Logger log=LoggerFactory.getLogger(BaseController.class);

	
	@Resource(name="userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("cacheManager")
	private CacheManager caheManager;
	
	
	
	/**
	 * 用户登录API
	 * @Method: login 
	 * @param user
	 * @throws
	 */
	@ApiOperation(value = "登录", notes = "返回json数据"
		/*, tags = "登录"*/
		, httpMethod = "GET"
		, produces = "application/json, application/xml"
		, consumes = "application/json, application/xml"
		, response = Map.class)
	@ApiResponse(code = 200, message = "success true")
	/*@ApiImplicitParams({
		@ApiImplicitParam(name = "name", value = "姓名", required = true, paramType = "form", dataType = "String"),
		@ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "form", dataType = "String")
		})*/
	 @ApiParam(name = "username", value = "用户名和密码",required = true, 
	 example = "/api/users/test") 
	 /* Map<String, String> user
	 */
	@RequestMapping(value = "/api/users/{username}", method = RequestMethod.GET)
	/*
	 * ,consumes={"application/json, text/html"} ,produces={
	 * "application/json; charset=UTF-8"})
	 * //,params={"username=name","password=pwd"}
	 */
	@ResponseBody
	public Map<String, Object>  login(
			@PathVariable("username") String name, HttpSession session) {
		System.out.println(name);
		User user = new User();
		user.setName(name);
		Map<String, Object> message = new HashMap();
		
		 user = userService.login(user);
		if (user.getId()!=null) {
			message.put("success", true);
			message.put("user", user);
			
			/**
			 * 记录当前登录用户
			 */
			session.setAttribute("currentUser", user);
			log.info("session is {}"
					, session.getAttribute("currentUser").toString());
			
		}else {
			message.put("success", false);
		}
		
		
		/**
		 * 记录当前登录用户
		 * 待更改
		 */
		/*Ehcache cache = caheManager.getCache("user");
		try {
			cache.acquireWriteLockOnKey("user");
			Element element = new Element("user", user);
			cache.put(element);
		} finally {
			cache.releaseWriteLockOnKey("user");
		}*/
		return  message;
	}

	@ApiOperation(value = "注册"
			, notes = "用户注册，返回json数据"
			, httpMethod = "POST"
			, produces = "application/json"
			, consumes = "application/json"
			, response = Map.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "name", value = "姓名", required = true, paramType = "form", dataType = "String"),
		@ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "form", dataType = "String")
	})
	@ApiResponses({ @ApiResponse(code = 200, message = "success true") })
	@RequestMapping(value = "/api/users", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> register(
			@RequestBody @ApiParam(name = "user", value = "用户名,密码"
			, example = "{'name':'zhangsan','password':'123456'}") 
			Map<String, String> user) {
		System.out.println(user);
		User user2 = new User();
		user2.setName(user.get("username"));
		user2.setPassword(user.get("password"));
		Map<String, Object> message = new HashMap();
		Long  id = userService.register(user2);
		if ( id != null) {
			message.put("success", true);
			message.put("userid", id);
		}else {
			message.put("success", false);
		}
		return message;
	}
	
	/**
	 * 退出
	 * @Method: logout 
	 * @throws
	 */
	@ApiOperation(value = "退出", notes = "用户退出系统"
			, httpMethod = "DELETE", produces = "application/json"
			, consumes = "application/json"
			, response = Map.class)
	@ApiParam(name = "session", value = "回话" )
	@RequestMapping(value = "/api/users", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> logout(HttpSession session){
		Map<String, Object> message = new HashMap();
		User user = (User) session.getAttribute("currentUser");
		if (user != null) {
			log.info("session is :{}",user.toString());
			session.setAttribute("oldUser", user);
			session.removeAttribute("curentUser");
			/**
			 * 记录旧的登录用户
			 */
			Cache cache = caheManager.getCache("user");
			try {
				//cache.acquireWriteLockOnKey("oldUser");
				///Element element = new Element("oldUser", user);
				//cache.put(element);
				cache.put("oldUser", user);
			} finally {
				//cache.releaseWriteLockOnKey("oldUser");
			}
			message.put("success", true);
			message.put("user", user);
		}else {
			message.put("success", false);
		}
		return message;
	}
	
	
	/**
	 *新闻系列 
	 */
	@Resource(name= "newsService")
	private NewsService newsService;
	
	@Resource(name = "behaviorLogService")
	private BehaviorLogService behaviorLogService;
	
	@ApiOperation(value = "推荐", notes = "得到推荐结果，返回是json数据"
			, httpMethod = "GET", produces = "application/json"
			, consumes = "application/json"
			, response = JSONArray.class)
	@ApiParam(name = "session"
	, value = "用户回话，session里有user"
	, readOnly = true
	, required = true)
	@RequestMapping(value = "/api/recommender", method = RequestMethod.GET)
	@ResponseBody
	public List<JSONObject> recommender(HttpSession session){
		User user = (User) session.getAttribute("currentUser");
		Long uid ;
		if (user == null) {
			user = ((User) session.getAttribute("oldUser"));
			if (user == null) {
				uid = (long)1;
			}else{
				uid = user.getId();
			}
		}else {
			uid = user.getId();
		}
		List<RecommendedItem> list = null;
		
		List<JSONObject> newses = new ArrayList<JSONObject>();
		try {
			Recommender recommender = RecommenderResult.startRecommend();
			
			if (uid != null) {
				list = recommender.recommend(uid, 3);
				for (RecommendedItem recommendedItem : list) {
					Long nid = recommendedItem.getItemID();
					News news = newsService.findByID(nid);
					float value = recommendedItem.getValue();
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("news", news);
					jsonObject.put("score", value);
					log.info("userid:{}, nid:{}, value:{}", uid, nid, value);
					newses.add(jsonObject);
				}
			}
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newses;
	}
	
	@ApiOperation(value = "获得新闻", notes = "获得不同类别的新闻，返回json数据"
			, httpMethod = "GET", produces = "application/json"
			, consumes = "application/json"
			, response = org.springframework.web.servlet.ModelAndView.class)
	@ApiParam(name = "categoty", value = "类别名", readOnly = true, required = true)
	@RequestMapping(value = "/api/news/{category}", method = RequestMethod.GET)
	@ResponseBody
	public List<News> getNewsByCategory( @PathVariable("category") String category ){
		/*Long cate ;
		if (category.equals("domestic")) {
			cate = ((long)1);
		}else if(category.equals("world")){
			cate = ((long)2);
		}else if (category.equals("society")) {
			cate = ((long)3);
		}else if (category.equals("war")) {
			cate = ((long)4);
		}else if (category.equals("air")) {
			cate = ((long)5);
		}else if (category.equals("UAV")) {
			cate = ((long)6);
		}else {
			cate = (long)9;
		}*/
		List<News> list = newsService.findByCategory(category);
		return list;
	}
	
	@Resource(name = "topNewsService")
	private TopNewsService topNewsService;
	
	@ApiOperation(value = "今日新闻", notes = "获得今日热点的新闻，返回json数据"
			, httpMethod = "GET", produces = "application/json"
			, consumes = "application/json"
			, response = org.springframework.web.servlet.ModelAndView.class)
	@RequestMapping(value = "/api/todaynews", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getHotNews(){
		JSONObject jsonObject = new  JSONObject();
		List<News> list = topNewsService.getTodayNews();
		/*Cache cache = caheManager.getCache("todaynews");
		JSONArray jsonArray = null;
		//Element element = null ;
		ValueWrapper valueWrapper;
		try {
			//cache.acquireReadLockOnKey("todaynews");
			valueWrapper = cache.get("todaynews");
			jsonArray = (JSONArray)valueWrapper.get();
			log.info("todaynews is :{}", jsonArray.toString());
		} finally {
			//cache.releaseReadLockOnKey("todaynews");
		}*/
		
		if (list == null ) {
			jsonObject.put("success", false);
			jsonObject.put("message", "An unknown error has occurred");
		}else {
			jsonObject.put("success", true);
			jsonObject.put("content", list);
		}
    	return jsonObject;
	}

	@ApiOperation(value = "新闻内容", notes = "获得新闻文章，返回json数据"
			, httpMethod = "GET", produces = "application/json"
			, consumes = "application/json"
			, response = org.springframework.web.servlet.ModelAndView.class)
	@ApiParam(name = "id", value = "新闻id", required = true)
	@RequestMapping(value = "/api/news/{id}/content", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getNewsDetail(@PathVariable("id") Long id){
		JSONObject jsonObject = new JSONObject();
		if (id != null) {
			String content =  newsService.getDetails(id);
			
			jsonObject.put("content", content);
			jsonObject.put("success", true);
			
			/**
			 * 行为日志
			 */
			
		}else{
			jsonObject.put("success", false);
			jsonObject.put("message", "user id is null");
		}
		return jsonObject;
	}
	
	/**
	 * 评论系列
	 */
	@Resource(name = "commentService")
	private CommentService commentService;
	
	@ApiOperation(value = "评论列表", notes = "获得对某一新闻的评论列表，返回json数据"
			, httpMethod = "GET", produces = "application/json"
			, consumes = "application/json"
			, response = org.springframework.web.servlet.ModelAndView.class)
	@ApiParam(name = "id" , value = "新闻id", required = true)
	@RequestMapping(value = "/api/{id}/comment", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray getCommnentById(@PathVariable("id") Long id){
		List<Comment> list= commentService.getByNewsId(id);
		JSONArray jsonArray = new JSONArray();
		for (Comment object : list) {
			String name = userService.getName(object.getUid());
			JSONObject jsonObject =(JSONObject) JSONObject.toJSON(object);
			jsonObject.put("username", name);
			jsonArray.add(jsonObject);
		}
		
		return jsonArray;
	}
	
	@ApiOperation(value = "评论", notes = "创建用户评论对某一新闻的评论,返回json数据"
			, httpMethod = "POST", produces = "application/json"
			, consumes = "application/json"
			, response = org.springframework.web.servlet.ModelAndView.class)
	@ApiParam(name = "id", value = "新闻id", required = true)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "uid", value = "用户id", dataType = "Long")
		, @ApiImplicitParam(name = "nid", value = "新闻id", dataType = "Long" )
		, @ApiImplicitParam(name = "content", value = "新闻评论内容", dataType = "String")
	})
	@RequestMapping(value = "/api/{id}/comment", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject createComment(@PathVariable("id") Long id
			, @RequestBody Map<String, Object> comment){
		
		Comment comment1 = new Comment();
		comment1.setNid(id);
		Long uid = Long.valueOf(String.valueOf(comment.get("userid"))).longValue();
		comment1.setUid(uid);
		comment1.setContent((String) comment.get("content"));
		Long id1 = commentService.createComment(comment1);
		JSONObject jsonObject =new JSONObject();
		if(id1!=null){
			jsonObject.put("success", true);
			jsonObject.put("comment", jsonObject.toJSON(comment1));
		}else{
			jsonObject.put("success", false);
		}
		
		/**
		 * 日志
		 */
		
		return jsonObject;
	}
	
	
	/**
	 * 一些行为假设
	 */
	@ApiOperation(value = "收藏或者分享新闻", notes = "返回json数据"
			, httpMethod = "POST", produces = "application/json"
			, consumes = "application/json"
			, response = org.springframework.web.servlet.ModelAndView.class)
	@ApiParam(name = "id", value = "新闻id", required= true)
	@RequestMapping(value = "/api/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> behavior(@PathVariable("id") Long nid
	, @RequestBody Map<String, Object> paramer){
		Map<String, Object> map = new HashMap<>();
		Long uid =(long)(int)paramer.get("userid");
		if (uid != null && nid != null) {
			/**
			 * 做些事情
			 */
			String behavior = (String) paramer.get("behavior");
			if (behavior.equals("collection")) {
				
				log.info("behavior is collection");
				/**
				 * 
				 */
				//behaviorLogService.updateCollectCount(uid, nid);
			}else if (behavior.equals("share")) {
				log.info("behavior is share ");
				/**
				 * 
				 */
				//behaviorLogService.updateShareCount(uid, nid);
			} 
		}else {
			map.put("success", false);
		}
		log.info("newsid:{}, paramer:{}", nid, paramer);
		return map;
	}
	
	
}
