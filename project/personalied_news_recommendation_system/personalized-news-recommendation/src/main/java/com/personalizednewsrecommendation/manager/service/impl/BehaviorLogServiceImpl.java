package com.personalizednewsrecommendation.manager.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.personalizednewsrecommendation.manager.dao.*;
import com.personalizednewsrecommendation.manager.pojo.Preferences;
import com.personalizednewsrecommendation.manager.service.BehaviorLogService;
import com.personalizednewsrecommendation.manager.pojo.*;

@Service("behaviorLogService")
public class BehaviorLogServiceImpl implements BehaviorLogService {
	
	@Autowired
	private BehaviorLogDAO behaviorLogDAO;
	
	@Override
	public List<BehaviorLog> getAll() {
		return behaviorLogDAO.getAll();
	}

	@Override
	public Long updateClickCount(Long uid, Long nid) {
		// TODO Auto-generated method stub
		BehaviorLog behaviorLog = behaviorLogDAO.getByUidNid(uid, nid);
		Long id = null;
		if(behaviorLog == null ){
			behaviorLog = new BehaviorLog(uid, nid);
			behaviorLog.setClick(1);
			behaviorLogDAO.insertLog(behaviorLog);
			id = behaviorLog.getId();
		}else{
			id = behaviorLog.getId();
			int click = behaviorLog.getClick();
			click++;
			behaviorLog.setClick(click);
			behaviorLogDAO.updateClick(id, click);
		}
		
		return id;
	}

	@Override
	public Long updateReadCount(Long uid, Long nid) {
		// TODO Auto-generated method stub
		BehaviorLog behaviorLog = behaviorLogDAO.getByUidNid(uid, nid);
		Long id = null ;
		if(behaviorLog == null ){
			behaviorLog = new BehaviorLog(uid, nid);
			behaviorLog.setRead(1);
			behaviorLogDAO.insertLog(behaviorLog);
			id = behaviorLog.getId();
		}else{
			id = behaviorLog.getId();
			int read = behaviorLog.getRead();
			read++;
			behaviorLog.setRead(read);
			behaviorLogDAO.updateRead(id, read);
		}
		
		return id;
	
	}

	@Override
	public Long updateCommentCount(Long uid, Long nid) {
		BehaviorLog behaviorLog = behaviorLogDAO.getByUidNid(uid, nid);
		Long id = null;
		if(behaviorLog == null ){
			behaviorLog = new BehaviorLog(uid, nid);
			behaviorLog.setComment(1);
			behaviorLogDAO.insertLog(behaviorLog);
			id = behaviorLog.getId();
		}else{
			id = behaviorLog.getId();
			int comm = behaviorLog.getComment();
			comm++;
			behaviorLogDAO.updateComment(id, comm);
		}
		
		return id;
	}

	@Override
	public Long updateCollectCount(Long uid, Long nid) {
		BehaviorLog behaviorLog = behaviorLogDAO.getByUidNid(uid, nid);
		Long id = null;
		if(behaviorLog == null ){
			behaviorLog = new BehaviorLog(uid, nid);
			behaviorLog.setCollect(1);
			behaviorLogDAO.insertLog(behaviorLog);
			id = behaviorLog.getId();
		}else{
			id = behaviorLog.getId();
			int collect = behaviorLog.getCollect();
			collect++;
			behaviorLogDAO.updateCollect(id, collect);
		}
		
		return id;
	}

	@Override
	public Long updateShareCount(Long uid, Long nid) {
		BehaviorLog behaviorLog = behaviorLogDAO.getByUidNid(uid, nid);
		Long id = null;
		if(behaviorLog == null ){
			behaviorLog = new BehaviorLog(uid, nid);
			behaviorLog.setForward(1);
			behaviorLogDAO.insertLog(behaviorLog);
			id = behaviorLog.getId();
		}else{
			id = behaviorLog.getId();
			int share = behaviorLog.getForward();
			share++;
			behaviorLogDAO.updateForward(id, share);
		}
		
		return id;
	}

	@Override
	public Long Other(Long uid, Long nid) {
		BehaviorLog behaviorLog = behaviorLogDAO.getByUidNid(uid, nid);
		Long id = null;
		if(behaviorLog == null ){
			behaviorLog = new BehaviorLog(uid, nid);
			behaviorLog.setOther(1);
			behaviorLogDAO.insertLog(behaviorLog);
			id = behaviorLog.getId();
		}else{
			id = behaviorLog.getId();
			int other = behaviorLog.getOther();
			other++;
			behaviorLogDAO.updateOther(id, other);
		}
		
		return id;
	}

}
