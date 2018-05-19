package com.personalizednewsrecommendation.manager.service;

import com.personalizednewsrecommendation.manager.pojo.*;
import java.util.*;

public interface CommentService {
	public List<Comment> getByNewsId(Long nid);
	public List<Comment> getByUserId(Long uid);
	public List<Comment> getById(Long uid, Long nid);
	public Long createComment(Comment comment);
	public void deleteComment(Long id);
}
