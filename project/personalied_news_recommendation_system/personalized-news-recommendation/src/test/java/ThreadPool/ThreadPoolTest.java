package ThreadPool;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

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
import com.personalizednewsrecommendation.manager.pojo.*;



@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:spring/springmvc.xml" })
public class ThreadPoolTest {
	private static Logger log = LoggerFactory.getLogger(ThreadPoolTest.class);

	
	
	@Before
	public void beforeTest(){
		
	}
	@Test
	public void testThreadPool(){
		executor();
	}
	
	@After
	public void afterTest(){
		
	}
	public void executor() {

		log.info("----程序开始运行----");
		Date date1 = new Date();

		int taskSize = 5;
		// 创建一个线程池
		ExecutorService pool = Executors.newFixedThreadPool(taskSize);
		// 创建多个有返回值的任务
		List<Future> list = new ArrayList<Future>();
		for (int i = 0; i < taskSize; i++) {
			Callable<?> c = new MyCallable(i + " ");
			// 执行任务并获取Future对象
			Future<?> f = (Future) pool.submit(c);
			
			list.add(f);
		}
		// 关闭线程池
		pool.shutdown();

		// 获取所有并发任务的运行结果
		for (Future f : list) {
			// 从Future对象上获取任务的返回值，并输出到控制台
			try {
				log.info(">>> {}",  f.get().toString());
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Date date2 = new Date();
		log.info("----程序结束运行----，程序运行时间[{}毫秒]"
				, (date2.getTime() - date1.getTime()));
	}

	class MyCallable implements Callable<Object> {
		private String taskNum;

		MyCallable(String taskNum) {
			this.taskNum = taskNum;
		}

		public MyCallable() {
			super();
		}

		@Override
		public Object call() throws Exception {
			log.info(">>>{}任务启动", taskNum);
			Date dateTmp1 = new Date();
			Thread.sleep(1000);
			Date dateTmp2 = new Date();
			long time = dateTmp2.getTime() - dateTmp1.getTime();
			log.info(">>> {} 任务终止", taskNum);
			return taskNum + "任务返回运行结果,当前任务时间[" + time + "毫秒]";
		}

	}

}
