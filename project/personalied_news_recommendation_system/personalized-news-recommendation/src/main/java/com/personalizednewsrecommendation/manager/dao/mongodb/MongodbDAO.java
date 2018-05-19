package com.personalizednewsrecommendation.manager.dao.mongodb;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.WriteResult;

public interface MongodbDAO<T> {
	/**
	 * \brief 增加对象
	 * 
	 * @param t
	 * @attention
	 */
	public void save(T t);

	/**
	 * \brief 插入一个list集合对象
	 * 
	 * @param list
	 * @attention
	 *
	 */
	public void insertAll(List<T> list);

	/**
	 * \brief 删除对象
	 * 
	 * @param t
	 * @return 
	 * @attention 方法的使用注意事项
	 * 
	 */
	public WriteResult delete(T t);

	/**
	 * 根据id 删除对象
	 *
	 * @param id
	 * @return 
	 */
	public WriteResult deleteById(String id);

	/**
	 * 根据条件删除
	 * @return 
	 */
	public WriteResult delete(Query query);

	/**
	 * 删除该collection 的所有的数据
	 * @return 
	 */
	public WriteResult deleteAll();

	/**
	 * \brief 根据条件更新数据信息
	 * 
	 * @param query
	 * @param update
	 * @attention
	 * 
	 */
	public T update(Query query, Update update);

	/**
	 * \brief 查询所有
	 * 
	 * @return
	 * @attention
	 * 
	 */
	public List<T> findAll();

	/**
	 * 根据查询query 查找list
	 *
	 * @param query
	 * @return
	 */
	public List<T> find(Query query);

	/**
	 * 按照字段排序 － 顺序 <br/>
	 * 
	 * @param query
	 *            查询条件 <br/>
	 * @param properties
	 *            排序字段 <br/>
	 * @return
	 */
	public List<T> findWithOrderASC(Query query, String... properties);

	/**
	 * 按照字段排序 － 逆序 <br/>
	 * 
	 * @param query
	 *            查询条件 <br/>
	 * @param properties
	 *            排序字段 <br/>
	 * @return
	 */
	public List<T> findWithOrderDESC(Query query, String... properties);

	/**
	 * 根据查询query 查找一个对象
	 *
	 * @param query
	 * @return
	 */
	public T findOne(Query query);

	/**
	 * 根据 id 查询对象
	 *
	 * @param id
	 * @return
	 */
	public T findById(String id);

	/**
	 * 根据id 和 集合名字查询对象
	 *
	 * @param id
	 * @param collectionName
	 * @return
	 */
	public T findById(String id, String collectionName);

	/**
	 * 查询分页 tips：［不要skip 太多的页数，如果跳过太多会严重影响效率。最大不要skip 20000页］
	 * 
	 * @param page
	 * @param query
	 * @return
	 */
	//public PageInfo<T> findPage(PageInfo<T> page, Query query);

	/**
	 * \brief 统计条数
	 * 
	 * @param query
	 * @return
	 * @attention 方法的使用注意事项
	 * 
	 */
	public long count(Query query);

	/**
	 * 获取需要操作的实体类class <br/>
	 * 例如: StudentScoreDao extends MongodbDao <b>&lt;StudentScore&gt;</b> <br/>
	 * 返回的是 <b>StudentScore</b> 的Class
	 *
	 * @return
	 */
	public Class<T> getEntityClass();

	/**
	 * 获取collection的名字，默认是dao范型T的名字 <br/>
	 * 例如: StudentScoreDao extends MongodbDao <b>&lt;StudentScore&gt;</b> <br/>
	 * 则返回的名字是：<b>StudentScore</b>
	 *
	 * @return
	 */
	public String getCollectionName();
}
