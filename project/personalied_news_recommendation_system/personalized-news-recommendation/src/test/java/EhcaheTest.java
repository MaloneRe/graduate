import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
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

import com.alibaba.fastjson.JSONArray;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml","classpath:spring/springmvc.xml"})
public class EhcaheTest {
    private final Logger log = LoggerFactory.getLogger(EhcaheTest.class);

	@Autowired
	private CacheManager caheManager;
	
	private Cache testCache;
	@Before
	public void getCahe(){
		testCache = caheManager.getCache("helloworld");
		Element element=new Element("key1", "this is hello world value");
		testCache.put(element);
		
		
	}
	
	@Test
	public void testEhcahe(){
		Element element =testCache.get("key1");
		System.out.println("key1: "+element.getObjectKey()+"\nvalue:"+element.getObjectValue());
		log.info("key1:{}, value:{}", element.getObjectKey(), element.getObjectValue());
	    // 获取Cache的元素数
        log.info("cache size:{}", testCache.getSize());
        System.out.println("cache size:"+testCache.getSize());
        // 获取MemoryStore的元素数
        System.out.println("MemoryStoreSize:"+testCache.getMemoryStoreSize());
        log.info("MemoryStoreSize:{}", testCache.getMemoryStoreSize());
        // 获取DiskStore的元素数
        System.out.println("DiskStoreSize:"+testCache.getDiskStoreSize());
        log.info("DiskStoreSize:{}", testCache.getDiskStoreSize());
        Element element2 = new Element("key1", "test test");
        log.info("key2:{}, value:{}", element2.getObjectKey(), element2.getObjectValue());
        testCache.put(element2);
        // 移除Element
        ///testCache.remove("key1"); 
	}
	@Test
	public void testEcache(){
		log.info("test Ehcache {}, names:{}"
				, JSONArray.toJSONString(caheManager.getConfiguration())
				, JSONArray.toJSONString(caheManager.getCacheNames()));
	}
	
	@After
	public void afterCache(){
		System.out.println("(After) cache size:"+testCache.getSize()); 
		log.debug("(After) cache size:{}", testCache.getSize());
		log.info("(After) cache size:{}", testCache.getSize());
		Element element =testCache.get("key1");
		System.out.println("key1: "+element.getObjectKey()+"\nvalue:"+element.getObjectValue());
		log.info("key1:{}, value:{}", element.getObjectKey(), element.getObjectValue());
	}
}
