package com.personalizednewsrecommendation.manager.dao;

import java.util.List;

import com.personalizednewsrecommendation.manager.pojo.Comment;

public interface CommentDAO {
	public void insertComment(Comment comment);
	public Comment getCommentById(Long id);
	public List<Comment> getByUserId(Long uid);
	public List<Comment> getByNewsId(Long nid);
	public Long getIdByComment(Comment comment);
	public void deleteComment(Long id);
}
