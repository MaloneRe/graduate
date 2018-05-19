package com.personalizednewsrecommendation.manager.algorithm.mahout;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidAbstractDataSource;
import com.alibaba.druid.pool.DruidDataSource;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.ReloadFromJDBCDataModel;

import javax.sql.*;

public class NewsDataModel extends MySQLJDBCDataModel {

	// 保存用户对新闻的评分的数据库表名
	public final static String PERFERENCETABLE = "news_preferences";
	public final static String USERID_COLUMN = "user_id"; // 表中用户标识的列名
	public final static String ITEMID_COLUMN = "news_id"; // 表中新闻标识的列名
	public final static String PERFERENCE_COLUMN = "preference"; // 表中评分的列名
	public final static String CREATE_TIME = "gmt_create";// 创建时间
	private static DataSource dataSource = DataSourceWrap.dataSource;
	
	/**
	 * spring注入不支持static
	 */
	
	@Autowired
	@Qualifier("dataSource")
	public void setDataSource(DataSource dataSource){
		NewsDataModel.dataSource = dataSource;
	}
	/*private static  DataSource druidDataSource ;
	static{
		druidDataSource = new DruidDataSource();
		((DruidAbstractDataSource) druidDataSource).setDriverClassName("com.mysql.jdbc.Driver");
		((DruidAbstractDataSource) druidDataSource).setUrl("jdbc:mysql://localhost:3306/news_feeds");
		((DruidAbstractDataSource) druidDataSource).setUsername("root");
		((DruidAbstractDataSource) druidDataSource).setPassword("root");
		((DruidAbstractDataSource) druidDataSource).setInitialSize(0);
		((DruidAbstractDataSource) druidDataSource).setMaxActive(20);
		((DruidAbstractDataSource) druidDataSource).setMaxIdle(20);
		((DruidAbstractDataSource) druidDataSource).setMinIdle(1);
		((DruidAbstractDataSource) druidDataSource).setMaxWait(60000);
	} ;*/

	public NewsDataModel(DataSource dataSource) {
		super(dataSource, PERFERENCETABLE
				, USERID_COLUMN, ITEMID_COLUMN
				, PERFERENCE_COLUMN, CREATE_TIME);

	}

	public NewsDataModel() {
		/*super(dataSource, PERFERENCETABLE
				, USERID_COLUMN, ITEMID_COLUMN
				, PERFERENCE_COLUMN, CREATE_TIME);*/
	
		//this(druidDataSource);
		this(dataSource);
		//System.out.println("newsDataModel:-->" + dataSource.toString());
	}
	
		
}
