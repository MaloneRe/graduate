package com.personalizednewsrecommendation.manager.dao;

import com.personalizednewsrecommendation.manager.pojo.*;
import java.util.*;

import org.apache.ibatis.annotations.Param;

public interface BehaviorLogDAO {
	public void insertLog(BehaviorLog behaviorLog);
	
	public BehaviorLog getById(Long id);
	
	public List<BehaviorLog> getAll();
	
	public List<BehaviorLog> getByUserId(Long uid);
	
	public List<BehaviorLog> getByNewsId(Long nid);
	
	public BehaviorLog getByIds(Map<String, Long> map);
	public BehaviorLog getByUidNid(Long uid, Long nid);
	
	public void updateClick(Long id, Integer clickCount);
	public void updateRead(Long id, Integer readCount);
	public void updateComment(Long id, Integer commentCount);
	public void updateCollect(Long id, Integer collectCount);
	public void updateForward(Long id, Integer forwardCount);
	public void updateOther(Long id,@Param("otherCount") Integer otherCount);
	
}
