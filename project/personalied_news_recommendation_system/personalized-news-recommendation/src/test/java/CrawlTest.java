import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.personalizednewsrecommendation.manager.spider.CrawlManager;
import com.personalizednewsrecommendation.manager.spider.CrawlManagerImpl;
import com.personalizednewsrecommendation.manager.spider.NewsWebURL;

import com.personalizednewsrecommendation.manager.spider.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml","classpath:spring/springmvc.xml"})
public class CrawlTest {
	private static Logger log = LoggerFactory.getLogger(CrawlTest.class);

	@Resource(name="crawlManager")
	private CrawlManagerImpl crawlManager;
	
	
	@Test
	public void testNewsCrawl(){
		//crawlManager.newsCrawl(1,NewsWebURL.WANGYI);
	}
	
	@Test
	public void testCallBack(){
		String[] urls={NewsWebURL.WANGYI_HOT
				, NewsWebURL.WANGYI_DOMESTIC
				, NewsWebURL.WANGYI_WORLD
				, NewsWebURL.WANGYI_SOCIETY
				, NewsWebURL.WANGYI_WAR
				, NewsWebURL.WANGYI_AIR
				, NewsWebURL.WANGYI_UAV};
		crawlManager.callbackDataCrawl(1,urls);
	}
	
	@Test
	public void testSpide(){
		String[] urls = {//NewsWebURL.WANGYI_DOMESTIC
				 NewsWebURL.WANGYI_AIR};
		crawlManager.callbackDataCrawl(1,urls);
	}
	
	@Test
	public void testTimefomat(){
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String string = "05/18/2018 09:18:21";
		Date date = new Date(string);
		String string2 = dataFormat.format(date);
		log.info("time is :{}", string2);
	}
	
	@Test
	public void testCrawlTask(){
		CrawlTask.runSpider();
	}
}
