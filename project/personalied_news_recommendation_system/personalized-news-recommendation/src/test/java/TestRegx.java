
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
import java.util.regex.*;
import com.personalizednewsrecommendation.manager.log.*;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml","classpath:spring/springmvc.xml"})
public class TestRegx {
	private static Logger log=LoggerFactory.getLogger(TestRegx.class);
	
	@Before
	public void beforeTest(){
		
	}
	
	@Test
	public void testRegx(){
		String regx = "/api/users/\\w*?";
		String input = "/api/users/sdsd/api/users/";
		Pattern pattern = Pattern.compile(regx);
		Matcher matcher = pattern.matcher(input);
		int count = 0;
		while (matcher.find()) {
			count ++; 
			log.info("count:{}, start:{}, end:{}", count, matcher.start(), matcher.end());
		}
	}
	
	@Test
	public void testMatherD(){
		Pattern pattern = Pattern.compile("/\\d+/??");
		String[] urls = {
				"/api/news/8993493/content"
				, "/api/223232/comment"
				, "/api/232242"
				, "/api/ssvd/"
				, "/api/3434"
				, "/api/news/4/content"
		};
		log.info("0  macher is {} ", UrlMatcher.getRegxClick().matcher(urls[0]).matches());
		log.info("1 macher is {}", UrlMatcher.getRegxComment().matcher(urls[1]).find());
		log.info("2 macher is {}", UrlMatcher.getRegxBehavior().matcher(urls[2]).find());
		
		for (int i = 0; i < urls.length; i++) {
			Matcher matcher = pattern.matcher(urls[i]);
			int count = 0;
			while (matcher.find()) {
				count ++; 
				log.info("url:{}, count:{}, start:{}, end:{}, substring:{}"
						, urls[i]
						, count, matcher.start()
						, matcher.end()
						, urls[i].substring(matcher.start()+1, matcher.end()));
				
				
			}
		}
	}
	@After
	public void aftertest(){
		
	}
}
