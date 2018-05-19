package logtest;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml","classpath:spring/springmvc.xml"})
public class LogTest {
	private static Logger log=LoggerFactory.getLogger(LogTest.class);
	@Autowired  
	private WebApplicationContext wac;
	//Spring提供的测试类
	private MockMvc mockMvc;
	
	@Before
	public void beforeTest(){
		//加载web容器上下文 
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        //初始化具体某个controller
        //this.mockMvc = MockMvcBuilders.standaloneSetup(new testuserController()).build();
        
	}
	@Test
	public void testInterceptor(){
		MvcResult mvcResult;
		try {
			mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/343")
				 	 .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))  
			        .contentType(MediaType.APPLICATION_JSON)  
			       )
			     .andExpect(MockMvcResultMatchers.status().is(200))
			     .andDo(MockMvcResultHandlers.print())
			     .andReturn();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@After
	public void afterTest(){
		
	}

}
