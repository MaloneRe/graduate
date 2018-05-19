package com.personalizednewsrecommendation.manager.dao.mongodb;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.WriteResult;


@Repository("mongodbDAO")
public abstract class MongodbDAOImpl<T> extends MongoTemplateInjection implements MongodbDAO<T> {

	@Override
	public void save(T t) {
		// TODO Auto-generated method stub
		mongoTemplate.save(t, getCollectionName());
	}

	@Override
	public void insertAll(List<T> list) {
		// TODO Auto-generated method stub
		mongoTemplate.insert(list, getCollectionName());
	}

	@Override
	public WriteResult delete(T t) {
		return mongoTemplate.remove(t, getCollectionName());
	}

	@Override
	public WriteResult deleteById(String id) {
		Criteria criteria = Criteria.where("id").is(id);
        Query query = new Query(criteria);
        return mongoTemplate.remove(query
        		, getEntityClass()
        		, getCollectionName());
	}

	@Override
	public WriteResult delete(Query query) {
		return mongoTemplate.remove(query
				, getEntityClass()
				, getCollectionName());
	}

	@Override
	public WriteResult deleteAll() {
		return mongoTemplate.remove(new Query(), getCollectionName());
	}

	@Override
	public T update(Query query, Update update) {
		return mongoTemplate.findAndModify(query
				 , update
				 , getEntityClass()
				 , getCollectionName());
	}

	@Override
	public List<T> findAll() {
		return mongoTemplate.findAll(getEntityClass()
				, getCollectionName());
	}

	@Override
	public List<T> find(Query query) {
		
		return mongoTemplate.find(query
				, getEntityClass()
				, getCollectionName());
	}

	@Override
	public List<T> findWithOrderASC(Query query, String... properties) {
		 Sort sort = new Sort(Direction.ASC, properties);
	     query.with(sort);
	     return mongoTemplate.find(query
	    		 , getEntityClass()
	    		 , getCollectionName());
	}

	@Override
	public List<T> findWithOrderDESC(Query query, String... properties) {
		Sort sort = new Sort(Direction.DESC, properties);
        query.with(sort);
        return mongoTemplate.find(query
        		, getEntityClass()
        		, getCollectionName());
	}

	@Override
	public T findOne(Query query) {
		// TODO Auto-generated method stub
		return mongoTemplate.findOne(query
				, getEntityClass()
				, getCollectionName());
	}

	@Override
	public T findById(String id) {
		// TODO Auto-generated method stub
		return mongoTemplate.findById(id
				, getEntityClass()
				, getCollectionName());
		
	}

	@Override
	public T findById(String id, String collectionName) {
		
		return mongoTemplate.findById(id
				, getEntityClass()
				, collectionName);
	}

	@Override
	public long count(Query query) {
		// TODO Auto-generated method stub
		return mongoTemplate.count(query
				, getEntityClass()
				, getCollectionName());
	}

	/*@Override
	public Class<T> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCollectionName() {
		// TODO Auto-generated method stub
		return null;
	}*/

}
