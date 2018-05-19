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
public class BehaviorLogTest {

	private static Logger log=LoggerFactory.getLogger(BehaviorLogTest.class);
	
	@Autowired
	private NewsDAO newsDAO;
	@Autowired
	private BehaviorLogDAO behaviorLogDAO;
	
	private BehaviorLog behaviorLog;
	@Before
	public void init(){
		behaviorLog = new BehaviorLog();
		behaviorLog.setUid((long) 1);
		behaviorLog.setNid((long) 2);
	
	}
	@Test
	public void testinsert(){
		//behaviorLogDAO.insertLog(behaviorLog);
		log.info("insert success; id is:{}", behaviorLog.getId());
		behaviorLog.setNid((long)3);
		//behaviorLogDAO.insertLog(behaviorLog);
	}
	@Test
	public void selecttest(){
		//log.info("getById; :{}", behaviorLogDAO.getById(behaviorLog.getId()));
		Map<String, Long> map = new HashMap<>();
		map.put("userid", (long)1);
		map.put("newsid", (long)2);
		//log.info("getByIds; :{}"
		//		, JSONObject.toJSONString(behaviorLogDAO.getByIds(map)));
		//log.info("getByUserId; :{}"
		//		, JSONObject.toJSONString(behaviorLogDAO.getByUserId((long)1)));
		//log.info("getByNewsId; :{}", behaviorLogDAO.getByNewsId((long)2));
		
	}
	@Test
	public void testBehavior(){
		behaviorLogDAO.updateClick((long)1, 3);
		log.info("updateClick; :{}"
				, behaviorLogDAO.getById((long)1).getClick());
		behaviorLogDAO.updateOther((long)1, 90);
		log.info("updateOther; :{}"
				, behaviorLogDAO.getById((long)1).getOther());
	}
	@After
	public void rehabilitative(){
		
	}
	
}
