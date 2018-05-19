package com.personalizednewsrecommendation.manager.spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.alibaba.fastjson.JSONArray;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

public class CallBackDataSpider implements PageProcessor{

	private Site site = Site.me().setTimeOut(20000).setRetryTimes(3)
			.setSleepTime(2000).setCharset("GBK");
	@Override
	public void process(Page page) {
		
		
		/*if (page.getUrl().toString().equals(NewsWebURL.WANGYI_DOMESTIC)) {
			
			System.out.println(page.getJson().toString().substring(0, 200));
		}*/
		String url = page.getUrl().toString();
		if (!url.equals(NewsWebURL.WANGYI_HOT)) {
			JSONArray jsonArray = JSONArray.parseArray(page.getJson().toString()
					.substring(14, page.getJson().toString().length()-1));
			page.putField(url,jsonArray);
		}else {
			
			Selectable selectable= page.getHtml().xpath("//div[@class='today_news']/html()");
			//Document doc = Jsoup.parse(selectable.toString(),"utf-8");  

			/*System.out.println(selectable.toString());
			
			System.out.println(selectable.links().all().toArray().toString());
			System.out.println(selectable.$("url>li>a", "title").all());
			*/
			page.putField(url
					, selectable);
			
		}
		
		//processDocs(page, jsonArray);
	}

	private void processDocs(Page page, JSONArray jsonArray) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return this.site;
	}

	
}
