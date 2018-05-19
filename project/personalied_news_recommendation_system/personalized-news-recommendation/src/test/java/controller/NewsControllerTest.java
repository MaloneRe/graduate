package controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.*;
import com.personalizednewsrecommendation.manager.pojo.User;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml","classpath:spring/springmvc.xml"})
public class NewsControllerTest {
	private static Logger log=LoggerFactory.getLogger(NewsControllerTest.class);
	
	@Autowired  
    private WebApplicationContext wac;
	 //Spring提供的测试类
	private MockMvc mockMvc;
	
	
	public void other(){
		
		//加载web容器上下文 
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        //初始化具体某个controller
        //this.mockMvc = MockMvcBuilders.standaloneSetup(new testuserController()).build();
        
		 try{
			 /*MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/users/test3")
				 	 .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))  
                     .contentType(MediaType.APPLICATION_JSON)  
                     .param("name", "test")  
                     .param("password", "test"))
		         .andExpect(MockMvcResultMatchers.status().is(200))
		         .andDo(MockMvcResultHandlers.print())
		         .andReturn();*/
			 /**/
			 
			 Map<String, String> map1 = new HashMap<>();
			 map1.put("username", "test90");
			 map1.put("password", "zhanghui");
			 MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			 map.add("username", "test90");
			 map.add("password", "zhanghui");
			 MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/news/{id}/content")
				 	 .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))  
                     .contentType(MediaType.APPLICATION_JSON)
                     .content(JSONObject.toJSONString(map1))
                    )
		         .andExpect(MockMvcResultMatchers.status().is(200))
		         .andDo(MockMvcResultHandlers.print())
		         .andReturn();
			 int status = mvcResult.getResponse().getStatus();
		     System.out.println("请求状态码：" + status);
		     String result = mvcResult.getResponse().getContentAsString();
		     System.out.println("接口返回结果：" + result);
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	}
	@Before
	public void beforeTest(){
		 this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	@Test
	public void testGetList(){
		MvcResult mvcResult;
		try {
			mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/news/esc232")
				 	 .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))  
			        .contentType(MediaType.APPLICATION_JSON)
			        
			       )
			     .andExpect(MockMvcResultMatchers.status().is(200))
			     .andDo(MockMvcResultHandlers.print())
			     .andReturn();
			int status = mvcResult.getResponse().getStatus();
			log.info("请求状态码：{}", status);
			String result = mvcResult.getResponse().getContentAsString();
			log.info("接口返回结果：{}", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testNewsContent(){
		User user = new User((long)2, "test3", "123456");
		MvcResult mvcResult;
		try {
			mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/news/4/content")
				 	 .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))  
			        .contentType(MediaType.APPLICATION_JSON)
			        .sessionAttr("currentUser", user)
			        
			       )
			     .andExpect(MockMvcResultMatchers.status().is(200))
			     .andDo(MockMvcResultHandlers.print())
			     .andReturn();
			int status = mvcResult.getResponse().getStatus();
			log.info("请求状态码：{}", status);
			String result = mvcResult.getResponse().getContentAsString();
			log.info("接口返回结果：{}", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetComment(){
		User user = new User((long)2, "test3", "123456");
		
		
 		MvcResult mvcResult;
		try {
			mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/1/comment")
				 	 .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))  
			        .contentType(MediaType.APPLICATION_JSON)
			        .sessionAttr("currentUser", user)
			        
			       )
			     .andExpect(MockMvcResultMatchers.status().is(200))
			     .andDo(MockMvcResultHandlers.print())
			     .andReturn();
			int status = mvcResult.getResponse().getStatus();
			log.info("请求状态码：{}", status);
			String result = mvcResult.getResponse().getContentAsString();
			log.info("接口返回结果：{}", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test 
	public void testCreateComment(){
		User user = new User((long)2, "test3", "123456");
		Map<String, Object> map = new HashMap<>();
		map.put("userid", 2);
		map.put("content", "comment news Id is 1 user id is 2");
		MvcResult mvcResult;
		try {
			mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/1/comment")
				 	 .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))  
			        .contentType(MediaType.APPLICATION_JSON)
			        .sessionAttr("currentUser", user)
			        .content(JSONObject.toJSONString(map))
			       )
			     .andExpect(MockMvcResultMatchers.status().is(200))
			     .andDo(MockMvcResultHandlers.print())
			     .andReturn();
			int status = mvcResult.getResponse().getStatus();
			log.info("请求状态码：{}", status);
			String result = mvcResult.getResponse().getContentAsString();
			log.info("接口返回结果：{}", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testOther(){
		User user = new User((long)2, "test3", "123456");
		Map<String, Object> map = new HashMap<>();
		map.put("userid", 2);
		
		map.put("behavior", "share");
		MvcResult mvcResult;
		try {
			mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/1")
				 	 .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))  
			        .contentType(MediaType.APPLICATION_JSON)
			        .sessionAttr("currentUser", user)
			        .content(JSONObject.toJSONString(map))
			       )
			     .andExpect(MockMvcResultMatchers.status().is(200))
			     .andDo(MockMvcResultHandlers.print())
			     .andReturn();
			int status = mvcResult.getResponse().getStatus();
			log.info("请求状态码：{}", status);
			String result = mvcResult.getResponse().getContentAsString();
			log.info("接口返回结果：{}", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTop(){
		User user = new User((long)2, "test3", "123456");
		Map<String, Object> map = new HashMap<>();
		map.put("userid", 2);
		
		map.put("behavior", "share");
		MvcResult mvcResult;
		try {
			mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/todaynews")
				 	 .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))  
			        .contentType(MediaType.APPLICATION_JSON)
			        .sessionAttr("currentUser", user)
			        .content(JSONObject.toJSONString(map))
			       )
			     .andExpect(MockMvcResultMatchers.status().is(200))
			     .andDo(MockMvcResultHandlers.print())
			     .andReturn();
			int status = mvcResult.getResponse().getStatus();
			log.info("请求状态码：{}", status);
			String result = mvcResult.getResponse().getContentAsString();
			log.info("接口返回结果：{}", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@After
	public void afterTest(){
		
	}
}
