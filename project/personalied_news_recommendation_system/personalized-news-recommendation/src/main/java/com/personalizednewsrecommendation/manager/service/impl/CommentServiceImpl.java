package com.personalizednewsrecommendation.manager.service.impl;

import java.util.*;
import java.util.List;

import com.personalizednewsrecommendation.manager.pojo.*;
import com.personalizednewsrecommendation.manager.service.*;
import com.personalizednewsrecommendation.manager.dao.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDAO commentDAO;
	
	@Override
	public List<Comment> getByNewsId(Long nid) {
		
		return commentDAO.getByNewsId(nid);
	}

	@Override
	public List<Comment> getByUserId(Long uid) {
		
		return commentDAO.getByUserId(uid);
	}

	@Override
	public List<Comment> getById(Long uid, Long nid) {
		List<Comment> list = new ArrayList();
		List<Comment> list1 = commentDAO.getByUserId(uid);
		for (Comment comment : list1) {
			if (comment.getNid()==nid) {
				list.add(comment);
			}
		}
		return list;
	}

	@Override
	public Long createComment(Comment comment) {
		commentDAO.insertComment(comment);
		return comment.getId();
	}

	@Override
	public void deleteComment(Long id) {
		// TODO Auto-generated method stub
		commentDAO.deleteComment(id);
	}

}
