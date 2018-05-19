package pojotest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.*;
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
import org.springframework.web.context.WebApplicationContext;

import com.personalizednewsrecommendation.manager.dao.CategoryDAO;
import com.personalizednewsrecommendation.manager.pojo.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml","classpath:spring/springmvc.xml"})

public class CategoryTest {
	private static Logger log=LoggerFactory.getLogger(CategoryTest.class);

	@Autowired
	private CategoryDAO categoryDAO;
	private Category categoryTest;
	@Before
	public void instance(){
		categoryTest =  new Category();
		categoryTest.setName("nvf");
	}
	@Test
	public void testInsert(){
		categoryDAO.insertCategory(categoryTest);
	}
	
	@Test
	public void testUpdate(){
		categoryTest.setId((long) 4);
		categoryTest.setName("secury");
		categoryDAO.updateCategory(categoryTest);
		log.info("");
	}
	@Test
	public void testQuery(){
		log.info("Query:{}",categoryDAO.getById((long) 2));
		log.info("Query by name:{}",categoryDAO.getByName("war"));
		log.info("Query all:{}",categoryDAO.getAll());
	}
}
