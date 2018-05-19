package com.personalizednewsrecommendation.manager.spider;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
/**
 * 
 * @author zhanghui
 *
 */
public class NewsSpider implements PageProcessor{

	private Site site = Site.me().setTimeOut(20000).setRetryTimes(3)
			.setSleepTime(2000).setCharset("GBK");
	
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		//page.putField("result", page.getHtml().toString());
		//page.putField("domestic", page.getUrl().regex("http://news\\.163\\.com/(\\w+)").toString());
		//System.out.println(page.getHtml().links().regex("http://news\\.163\\.com/(\\w+)").all().toString());
		//System.out.println(page.getHtml().xpath("//div[@class='ns_area list']/ul").regex("http://news\\.163\\.com/(\\w+)").all().toString());
		List<String> url=page.getHtml().xpath("//div[@class='ns_area list']/ul").regex("http://news\\.163\\.com/(\\w+)").all();
		page.addTargetRequests(url);
		/*for (Iterator iterator = url.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			page.putField(string, page.getUrl().regex("http://news\\.163\\.com/"+string).toString());
		}*/
		page.putField(page.getUrl().toString(), page.getHtml());
		System.out.println(page.getUrl().all().toString());
		System.out.println("头条: "+page.getHtml().xpath("//div[@class='today_news']/html()").toString());
		System.out.println(page.getHtml().xpath("//title/text()").toString());
		
		/*try {
			System.out.println(page.getHtml().xpath("//title/text()").toString().getBytes("GBK"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}*/
		
		
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}

}
