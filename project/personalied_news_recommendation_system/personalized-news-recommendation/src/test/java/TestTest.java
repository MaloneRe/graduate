import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
import java.util.List;

import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.personalizednewsrecommendation.manager.dao.NewsDAO;
import com.personalizednewsrecommendation.manager.pojo.Category;
import com.personalizednewsrecommendation.manager.pojo.News;
import com.personalizednewsrecommendation.manager.dao.*;
import com.personalizednewsrecommendation.manager.pojo.*;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml","classpath:spring/springmvc.xml"})


public class TestTest {
	
	private static Logger log=LoggerFactory.getLogger(TestTest.class);
	
	@Before
	public void before(){
	log.info("test before");
	}
	@Test
	public void test1(){
		log.info("test test1");
	}
	
	@Test
	public void test2(){
		log.info("test test2");
	}
	@Test
	public void test3(){
		log.info("test test3");
	}
	@After
	public void after(){
		log.info("test after");
	}

}
