package pojotest;
import javax.annotation.Resource;

import org.junit.After;
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

import com.personalizednewsrecommendation.manager.dao.UserDAO;
import com.personalizednewsrecommendation.manager.pojo.User;
import com.personalizednewsrecommendation.manager.service.UserService;
import com.personalizednewsrecommendation.manager.service.impl.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml","classpath:spring/springmvc.xml"})
public class UserTest {
    private final Logger log = LoggerFactory.getLogger(UserTest.class);

	@Resource(name="userSerivce")
	private UserServiceImpl userService;
	
	@Autowired
	private UserDAO userDAO;
	
	@Test
	public void testUser(){
		User user =new User();
		user.setName("test4");
		user.setPassword("654321");
		System.out.println(userService.register(user));
		User res = userService.login(user);
		/*userDAO.insertUser(user);
		System.out.println(userDAO.getIdByName(user.getName()));*/
		log.info("user ID:{}; user Name:{};user password:{}"
				, res.getId()
				, res.getName()
				, res.getPassword());
	}
}
