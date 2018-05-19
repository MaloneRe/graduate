package com.personalizednewsrecommendation.manager.spider;

import java.util.Map;

import org.springframework.stereotype.Component;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * 自定义Pipeline处理抓取的数据
 * @author zhanghui
 *
 */
@Component("customPipeline")
public class CustomPipeline implements Pipeline {

	@Override
	public void process(ResultItems resultItems, Task task) {
		// TODO Auto-generated method stub
        //System.out.println("get page: " + resultItems.getAll());
		System.out.println("get page: " + resultItems.getRequest().getUrl());
        System.out.println();
        
        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
            System.out.println(entry.getKey() + ":\t" + entry.getValue().toString().substring(0,200));
        }
        
        //System.out.println(resultItems.getAll()+" ");
	}

}
