package mongodbtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
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

import org.springframework.data.mongodb.core.query.*;
import org.springframework.data.mongodb.core.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Criteria.where;
//import static org.springframework.data.mongodb.core.query.Query;
import static org.springframework.data.mongodb.core.query.Update.update;


import com.alibaba.fastjson.*;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.personalizednewsrecommendation.manager.pojo.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml","classpath:spring/springmvc.xml"})

public class MongoDBTest {
	private static Logger log=LoggerFactory.getLogger(MongoDBTest.class);
	@Autowired
	@Qualifier("mongoTemplate")
	public MongoTemplate mongoTemplate; 
	
	@Before
	public void beforetest(){
		
	}
	
	@Test
	public void testMongo(){
		/*
		 * List<Map> list= mongoTemplate
				.find(query(where("title")
						.is("this is test"))
						, Map.class, "test");
		 */
		Map<String, Object> map1 = new HashMap<>();
		map1.put("testid", 1223232);
		map1.put("content", "test test test");
		
		DBObject dbObject = new BasicDBObject();
		dbObject.put("test", "I dont know");
		mongoTemplate.insert(dbObject, "test");
		List<BasicDBObject> list= mongoTemplate
				.findAll(BasicDBObject.class, "test");
		
		log.info("test start:\n");
		for (int i = 0; i < list.size(); i++) {
			log.info("test start:\n");
			log.info("list index i is object string:"
					, JSONObject.toJSONString(list.get(i)).toString());
			BasicDBObject map =
					(BasicDBObject)list.get(i);
			
			log.info("test mongodb is, id:{}, key:{}"
					+ ", value:{}"
					, ((ObjectId)map.get("_id")).toString()
					, map.get("test")
					, map.get("content")
					);
		}
		
	}
	@Test
	public void testQuery(){
		People people = mongoTemplate.findOne(new Query()
				, People.class, "test");
		log.info("id:{}, name:{}, age:{}"
				, people.getId()
				, people.getName()
				, people.getAge());
	}
	
	@Test
	public void testInsertAll(){
		People sPeople = new People("lisi", 32);
	
		mongoTemplate.insert(sPeople, "test");
		log.info("test id is :{}", sPeople.getId());
		List<People> list = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			People people = new People("zhangsan", (int)(1+Math.random()*(10-1+1)));
			
			//dbObject.put("random", (int)(1+Math.random()*(10-1+1)));
			list.add(people);
		}
		//mongoTemplate.insert(list, "test");
		
	}
	
	@Test
	public void testRemove(){
		Criteria criteria = Criteria.where("id")
				.is("5afee0d955f04f23bcbd2709");
        Query query = new Query(criteria);
        WriteResult writeResult = 
        		mongoTemplate.remove(query, People.class, "test");
        log.info("WriteResult:{}", writeResult.toString());
        log.info("WriteResukt json :{}", JSONObject.toJSONString(writeResult));
	}
	
	@Test
	public void testUpdate(){
		Update update = new Update();
		update.set("name", "testname");
		mongoTemplate.upsert(
				query(where("id").is("5afee0d955f04f23bcbd2709"))
				, update
				, People.class, "test");
	}
	
	@Test
	public void testBacthDelete(){
		mongoTemplate.remove(new Query(), "test"); 
	}
	
	@After
	public void afeterTest(){
		
	}
}
