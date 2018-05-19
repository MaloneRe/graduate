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
import com.personalizednewsrecommendation.manager.dao.*;
import com.personalizednewsrecommendation.manager.pojo.*;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml","classpath:spring/springmvc.xml"})



public class CommentTest {

	private static Logger log=LoggerFactory.getLogger(CategoryTest.class);
	@Autowired
	CommentDAO commentDAO;
	
	private Comment comment;
	
	@Before
	public void instance(){
		comment = new Comment();
		comment.setNid((long)3);
		comment.setUid((long)2);
		comment.setContent("dislike");
	}
	@Test
	public void testInstert(){
		commentDAO.insertComment(comment);
	}
	@After
	public void result(){
		log.info("query by id:{}",commentDAO.getCommentById((long)2));
		log.info("query by nid:{}",commentDAO.getByNewsId((long)1));
		log.info("query by uid:{}",commentDAO.getByUserId((long)2));
	}
}
