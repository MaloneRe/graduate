package mahoutTest;

import org.springframework.beans.factory.annotation.*;

import com.alibaba.druid.pool.DruidDataSource;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;

import javax.sql.*;

public class NewsDataModel extends MySQLJDBCDataModel { 
	 
		@Autowired
		@Qualifier("dataSource")
		private static DruidDataSource dataSource;
	   // 保存用户对新闻的评分的数据库表名
	   public final static String PERFERENCETABLE = "news_preferences";  
	   public final static String USERID_COLUMN = "user_id";   // 表中用户标识的列名
	   public final static String ITEMID_COLUMN = "news_id";  // 表中新闻标识的列名
	   public final static String PERFERENCE_COLUMN = "preference";  // 表中评分的列名
	   public final static String CREATE_TIME  = "gmt_create";//创建时间
	 
	   public NewsDataModel(DataSource dataSource) throws TasteException {
		   
		   super(dataSource, PERFERENCETABLE, USERID_COLUMN, 
		           ITEMID_COLUMN, PERFERENCE_COLUMN, CREATE_TIME);
		   
	   }
	   public NewsDataModel() { 
	      
	       super(dataSource, PERFERENCETABLE, USERID_COLUMN, 
	           ITEMID_COLUMN, PERFERENCE_COLUMN, CREATE_TIME); 
	   } 
	}
