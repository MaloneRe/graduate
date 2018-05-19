package com.personalizednewsrecommendation.manager.service;
import java.util.*;

import com.personalizednewsrecommendation.manager.pojo.*;

public interface BehaviorLogService {
	public List<BehaviorLog> getAll();
	public Long updateClickCount(Long uid, Long nid);
	public Long updateReadCount(Long uid, Long nid);
	
	public Long updateCommentCount(Long uid, Long nid);
	
	public Long updateCollectCount(Long uid, Long nid);
	
	public Long updateShareCount(Long uid, Long nid);
	
	public Long Other(Long uid, Long nid);
	
}
