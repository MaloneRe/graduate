package com.personalizednewsrecommendation.manager.dao;

import com.personalizednewsrecommendation.manager.pojo.*;
import java.util.*;
public interface UserAppendDAO {

	/**
	 * 获得用户多条评论
	 * @Method: findCommentById 
	 * @return
	 * @throws
	 */
	public List<Comment> getCommentById(long uid);
	/**
	 * 获得用户评论的所有新闻
	 * @Method: getNewsesById 
	 * @param uid
	 * @return
	 * @throws
	 */
	public List<News> getNewsesById(long uid);
}
