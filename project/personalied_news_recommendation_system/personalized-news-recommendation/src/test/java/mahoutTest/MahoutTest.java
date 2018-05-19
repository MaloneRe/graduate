package mahoutTest;
import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.annotation.*;
import java.util.*;
import java.*;
import java.sql.SQLException;
import java.util.Map;

import org.junit.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import com.personalizednewsrecommendation.manager.dao.*;
import com.personalizednewsrecommendation.manager.pojo.*;
import com.personalizednewsrecommendation.manager.service.*;
import com.personalizednewsrecommendation.manager.service.impl.*;
import com.personalizednewsrecommendation.manager.algorithm.mahout.*;
import com.personalizednewsrecommendation.manager.algorithm.mahout.RecommendFactory;
import com.personalizednewsrecommendation.manager.algorithm.mahout.RecommenderResult;
import com.personalizednewsrecommendation.*;

import org.apache.mahout.cf.taste.impl.common.*;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml","classpath:spring/springmvc.xml"})
public class MahoutTest {
	private final Logger log = LoggerFactory.getLogger(MahoutTest.class);
	
	private com.personalizednewsrecommendation.manager.algorithm.mahout.RecommenderResult result;
	
	private DataModel dataModel;
	private Recommender recommender;
	@Autowired
	@Qualifier("dataSource")
	public void setDataSource(DataSource dataSource1){
		dataSource = dataSource1;
	}
	private static DataSource dataSource;
	
	@Before 
	public void  beforeTest() throws TasteException{
		log.info("test wrap:{}", DataSourceWrap.dataSource.toString());
		result = new com.personalizednewsrecommendation.manager.algorithm.mahout.RecommenderResult();
		/*dataModel = newsDataModel =  new com.personalizednewsrecommendation
				.manager.algorithm
				.mahout.NewsDataModel();*/
		dataModel = com.personalizednewsrecommendation
				.manager.algorithm.mahout
				.RecommendFactory
				.buildDataModelByDB();
		
		//dataModel = RecommendFactory.buildDataModelByDB();
	}
	private com.personalizednewsrecommendation.manager.algorithm.mahout.NewsDataModel newsDataModel;
	@Test
	public void testnNewsDataModel(){
		try {
			LongPrimitiveIterator iterator =dataModel.getItemIDs();
			LongPrimitiveIterator iterator2 = dataModel.getUserIDs();
			
			while (iterator.hasNext()) {
				Long iid = (Long) iterator.next();
				log.info("itemID:==>{}",iid);
			}
			while (iterator2.hasNext()) {
				Long uid = (Long) iterator2.next();  
				log.info("userID:==>{}",uid);
				
			}
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testSvd(){
		try {
			//recommender = 
				//	result.startRecommend();
			recommender =
					com.personalizednewsrecommendation
					.manager.algorithm.mahout
					.RecommenderResult.startRecommend();
			log.info("-----------svd=============");
			/*com.personalizednewsrecommendation
				.manager.algorithm.mahout
				.RecommenderResult.svd(dataModel);*/
			log.info("-----------itemCF=============");
			com.personalizednewsrecommendation
			.manager.algorithm.mahout
			.RecommenderResult.itemCF(dataModel);
			List<RecommendedItem> list= recommender.recommend((long)5, 3);
			for (RecommendedItem recommendedItem : list) {
				long id = recommendedItem.getItemID();
				float value = recommendedItem.getValue();
				log.info("userID is 2==> recommend itemID:{}<--->value:{}", id, value);
			}
			
			com.personalizednewsrecommendation
			.manager.algorithm.mahout
			.RecommenderEvaluator.itemCF(dataModel, 2);
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}
	@After
	public void after(){
		log.info("test finish:");
		
	}
}
