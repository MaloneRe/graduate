package com.personalizednewsrecommendation.manager.algorithm.mahout;

import com.alibaba.druid.pool.*;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
@Component
public class DataSourceWrap {
	public static DataSource dataSource;
	@Autowired
	@Qualifier("dataSource")
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	
	public DataSource getDataSource(){
		return  this.dataSource;
	}
	
	
}
