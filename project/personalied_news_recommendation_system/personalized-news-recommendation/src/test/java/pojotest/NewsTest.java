package pojotest;
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
import com.alibaba.fastjson.JSONObject;
import com.personalizednewsrecommendation.manager.dao.*;
import com.personalizednewsrecommendation.manager.pojo.*;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml","classpath:spring/springmvc.xml"})

public class NewsTest {
	private static Logger log=LoggerFactory.getLogger(CategoryTest.class);

	@Autowired
	private NewsDAO newsDAO;
	
	private News news;
	
	private Category category;
	
	@Before 
	public void instance(){
		news = new News();
		News news1 = new News();
		List<News> list=new ArrayList<>();
		/*category = new Category();
		category.setId((long) 2);
		news.setTitle("1test333news22");
		news.setUrl("http://123");
		news.setImageUrl("http://png");
		news.setCategoryId((long)2);
		news.setMedia("self");
		news.setContent("test");
		news1.setTitle("2test45454news2888888888882");
		news1.setUrl("http://3ee123");
		news1.setImageUrl("http://---png");
		news1.setCategoryId((long)3);
		list.add(news1);
		list.add(news);
		newsDAO.insertNewses(list);*/

	}
	
	@Test
	public void testInsert(){
		//newsDAO.insertNews(List<E>);
		log.info("newslist:{}\n",JSONObject.toJSONString(newsDAO.getAll()));
		System.out.println("testinsetr");
	}
	@Test
	public void testQuery(){
		log.info("query by id:{}",newsDAO.getNewsById((long) 2));
		log.info("query by name:{}",newsDAO.getNewsesByName("test"));
		news.setId((long)8);
		news.setTitle("test899news konw");
	}
	@After
	public void verifyResult(){
		newsDAO.updateNews(news);
	}
	
	@Test
	public void getALl(){
		
	}
}
