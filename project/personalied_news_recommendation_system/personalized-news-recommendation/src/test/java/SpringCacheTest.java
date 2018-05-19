import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
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

import com.alibaba.fastjson.JSONArray;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml","classpath:spring/springmvc.xml"})
public class SpringCacheTest {
	private final Logger log = LoggerFactory.getLogger(SpringCacheTest.class);
	
	@Autowired
	@Qualifier("cacheManager")
	private EhCacheCacheManager caheManager;
	
	private Cache cache;
	@Before
	public void beforeTest(){
		cache = caheManager.getCache("todaynews");
		//cache.g
		cache.put("key1", "value is nuknow");
		log.info("before hello world config is {}"
				, cache.get("key1").get());
	}
	
	@Test
	public void testCache(){
		log.info("test cache:+====> {}\n key1:{}"
				, JSONArray.toJSONString(caheManager.getCacheNames())
				, cache.getName());
	}
	@After
	public void afterTest(){
		log.info("after hello world config is {}"
				, cache.get("key1").get());
	}
}
