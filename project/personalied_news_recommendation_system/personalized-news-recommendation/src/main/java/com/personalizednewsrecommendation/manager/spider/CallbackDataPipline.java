package com.personalizednewsrecommendation.manager.spider;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.ehcache.EhCacheCacheManager;
//import org.json.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.selector.Selectable;

import com.personalizednewsrecommendation.manager.activemq.CrawlQueueSender;
import com.personalizednewsrecommendation.manager.pojo.*;
import com.personalizednewsrecommendation.manager.service.*;

import controller.BaseControllerTest;

import javax.annotation.*;

import net.sf.ehcache.*;
@Component("callbackDataPipline")
public class CallbackDataPipline implements Pipeline {
	
	@Autowired
	@Qualifier("cacheManager")
	private EhCacheCacheManager caheManager;
	
	@Resource(name = "topNewsService")
	private TopNewsService topNewsService;
	
	private org.springframework.cache.Cache cache;
	
	@Resource(name = "newsService")
	private NewsService newsService;
	
	@Resource(name = "crawlQueueSender")
	private CrawlQueueSender crawlQueueSender;
	
	private SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static Logger log=LoggerFactory.getLogger(CallbackDataPipline.class);

	
	@Override
	public void process(ResultItems resultItems, Task task) {
		// TODO Auto-generated method stub
		
		for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
            System.out.println(entry.getKey() );
            		//+ ":\t" + entry.getValue().toString().substring(0,200));

           /* if (entry.getKey().equals(NewsWebURL.WANGYI_DOMESTIC)) {
            	JSONArray jsonArray ;
            	jsonArray = JSONArray.parseArray((String) entry.getValue());
            	for (int i = 0; i < 3; i++) {
            		System.out.println("jsonArray==="+i+"\n");
            		Set<Entry<String, Object>> entrySet = ((JSONObject) jsonArray.get(i)).entrySet();  
                    //输出长度  
                    System.out.println(entrySet.size());  
                    for (Entry<String, Object> s : entrySet) {  
                        //输出key+value  
                        System.out.println(s.getKey() + "+" + s.getValue()); 
                        if(s.getKey().equals("keywords")){
                        	System.out.println("keywords的第一个\n"
                        			+((JSONArray)s.getValue()).get(0));
                        }
                    }   
				}
            	Set<Entry<String, Object>> entrySet = jsonArray.entrySet();  
                //输出长度  
                System.out.println(entrySet.size());  
                for (Entry<String, Object> s : entrySet) {  
                    //输出key+value  
                    System.out.println(s.getKey() + "+" + s.getValue());  
                }   
			}*/
            String siteUrl = entry.getKey();
            if (siteUrl.equals(NewsWebURL.WANGYI_HOT)) {
            	
            	/*net.sf.ehcache.Element cElement = cache.get("top");
            	if (cElement == null) {
            		jsonArray = new JSONArray();
					cElement = new net.sf.ehcache.Element("top", jsonArray);
					cache.put(cElement);
					
				}else{
				
					jsonArray = (JSONArray) cElement.getObjectValue();
				}
            	
            	jsonArray.clear();
            	*/
            	List<News> list = new ArrayList<>();
            	
            	Selectable select = (Selectable) entry.getValue();
            	Document doc = Jsoup.parse(select.toString(),"utf-8");
            	Elements elements = doc.getElementsByTag("a");
            	for (Element element : elements) {
            		News news = new News();
            		String url = element.attr("href");
            		String title = element.attr("title");
            		news.setCategoryId((long)9);
            		news.setTitle(title);
            		news.setUrl(url);
            		log.info("title:{}, url:{}", title, url);
            		Long nid = newsService.createNews(news);
            		if (nid != null) {
						System.out.println(JSONObject.toJSONString(news));
						list.add(news);
					}
            		
            		/*System.out.println(url
            				+ " : "+title);
            		System.out.println(cate.AIR.getValue());*/
            		
				}
            	/**
            	 * 热点新闻存入缓存
            	 * 存入mongodb
            	 */
            	 TopNews topNews = new TopNews(list, "todaynews");
            	 topNewsService.saveTodayNews(topNews);
            	
            }else {
				
            	JSONArray jsonArray ;
            	jsonArray = JSONArray.parseArray((String) entry.getValue().toString());
            	int size = jsonArray.size();
            	List<News> list = new ArrayList<News>();
            	for (int i = 0; i < size; i++) {
            		News news = new News();
            		JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            		String title = jsonObject.get("title").toString();
            		news.setTitle(title);
					//System.out.println(title);
					//System.out.println(jsonObject.get("digest"));
					String docurl = jsonObject.get("docurl").toString();
					news.setUrl(docurl);
					//System.out.println(docurl);
					//System.out.println(jsonObject.get("commenturl"));
					//System.out.println(jsonObject.get("tienum"));
					//System.out.println(jsonObject.get("tlastid"));
					//System.out.println(jsonObject.get("tlink"));
					//System.out.println(jsonObject.get("label"));
					/**
					 * 
					 */
					StringBuffer stringBuffer =new StringBuffer(); 
					JSONArray temp = (JSONArray) jsonObject.get("keywords");
					for (Object object : temp) {
						JSONObject jsonObject2 = (JSONObject) object;
						//System.out.println(jsonObject2.get("akey_link"));
						//System.out.println(jsonObject2.get("keyname"));
						stringBuffer.append(jsonObject2.get("keyname").toString())
						.append(",");
					}
					news.setContent(stringBuffer.toString());
					String time = jsonObject.get("time").toString();
					
					if (time != null && !time.equals("")) {
						//log.info("title:,test time is :{}\n", time);
						news.setTime(dataFormat.format(new Date(time)));
					}
					//System.out.println(time);
					//System.out.println(jsonObject.get("newstype"));
					/**
					 * 
					 */
					JSONArray temp1 = (JSONArray) jsonObject.get("pics3");
					for (Object object : temp1) {
						JSONObject jsonObject3 = (JSONObject) object;
						//System.out.println(jsonObject3.toJSONString());
						
					}
					//System.out.println(jsonObject.get("channelname"));
					//System.out.println(jsonObject.get("channelname"));
					String imgurl = jsonObject.get("imgurl").toString();
					news.setImageUrl(imgurl);
					//System.out.println(imgurl);
					//System.out.println(jsonObject.get("add1"));
					//System.out.println(jsonObject.get("add2"));
					//System.out.println(jsonObject.get("add3"));
					//System.out.println("+++++++++++++++++++++++++++++");
					
					
					/**
					 * 添加到list中
					 */
					list.add(news);
					
					/**
					 * 插入数据库
					 */
					//newsService.createNews(news);
				}
            	log.info("============ activemq start sender==============");
            	Map<String, Object> map = new HashMap<>();
            	/*JSONArray jsonNews = (JSONArray) JSONArray.toJSON(list);
            	JSONObject jsonObject = new JSONObject();
            	jsonObject.put("url", siteUrl);
            	jsonObject.put("newses", jsonNews);*/
            	map.put("url", siteUrl);
            	String s = JSONArray.toJSONString(list).toString();
            	map.put("newses", list);
            	crawlQueueSender.send("crawlProcess.queue", map);
            	//log.info("list parse json string :\n{}",JSONArray.toJSON(list));
			}
		}
		
	}
	
	public void insertNewsDB(String url, List<News> newses){
		for (News news : newses) {
			if (url.equals(NewsWebURL.WANGYI_DOMESTIC)) {
				news.setCategoryId((long)1);
			}else if(url.equals(NewsWebURL.WANGYI_WORLD)){
				news.setCategoryId((long)2);
			}else if (url.equals(NewsWebURL.WANGYI_SOCIETY)) {
				news.setCategoryId((long)3);
			}else if (url.equals(NewsWebURL.WANGYI_WAR)) {
				news.setCategoryId((long)4);
			}else if (url.equals(NewsWebURL.WANGYI_AIR)) {
				news.setCategoryId((long)5);
			}else if (url.equals(NewsWebURL.WANGYI_UAV)) {
				news.setCategoryId((long)6);
			}else {
				news.setCategoryId((long)9);
			}
			newsService.createNews(news);
		}
	}

}

enum cate{
	DOMESTIC{
		public String getValue() {
			return "domestic";
		}

		@Override
		public Long getId() {
			
			return (long)1;
		}
	}
	, WORLD{
		public String getValue() {
			return "world";
		}

		@Override
		public Long getId() {
			// TODO Auto-generated method stub
			return (long)2;
		}
	}
	, SOCIETY{
		public String getValue() {
			return "society";
		}

		@Override
		public Long getId() {
			// TODO Auto-generated method stub
			return (long)3;
		}
	}
	, WAR{
		public String getValue() {
			return "war";
		}

		@Override
		public Long getId() {
			// TODO Auto-generated method stub
			return (long)4;
		}
	}
	, AIR{
		public String getValue() {
			return "air";
		}

		@Override
		public Long getId() {
			// TODO Auto-generated method stub
			return (long)5;
		}
	}
	, UAV{
		public String getValue() {
			return "UAV";
		}

		@Override
		public Long getId() {
			// TODO Auto-generated method stub
			return (long)6;
		}
	};
	public abstract String getValue();
	public abstract Long getId();
}
