import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:spring/springmvc.xml" })
public class JsoupTest {
	private static Logger log = LoggerFactory.getLogger(JsoupTest.class);

	@Test
	public void testJsoup() {

		Document doc;
		Elements news;
		Elements temp;
		Elements temp1;
		Elements temp2;
		try {
			doc = Jsoup.connect("http://news.163.com/18/0503/10/DGSHPFGG0001875P.html").get();
			/**
			 * 新闻主体内容
			 */
			news = doc.select("div#epContentLeft.post_content_main");
			/**
			 * 新闻内容下的推荐列表
			 */
			temp = doc.select("div#post_recommend.post_recommend");
			/**
			 * 新闻中的评论框
			 */
			temp1 = news.select("div#post_comment_area.post_comment");
			/*temp1 = news.select("div#post_comment_area.post_comment w9 ");*/
			/**
			 * 新闻内容中分享
			 */
			temp2 = news.select("div.post_topshare_wrap");
			news.select("div.post_btmshare");
			
			news.select("div#post_recommend.post_recommend").remove();
			news.select("div.post_topshare_wrap").remove();
			news.select("div.post_btmshare").remove();
			//news.remove(temp);
			log.info("news:{}", news);
			log.info("recommend:{}", temp);
			log.info("teizi:{}", temp1);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
