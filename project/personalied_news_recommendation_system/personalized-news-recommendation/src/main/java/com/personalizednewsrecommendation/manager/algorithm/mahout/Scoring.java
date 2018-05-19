package com.personalizednewsrecommendation.manager.algorithm.mahout;
import com.personalizednewsrecommendation.manager.pojo.*;
import com.personalizednewsrecommendation.manager.service.*;
import javax.annotation.*;
import java.util.*;

/**
 * 根据用户行为及新闻产生user-item评分
 * @ClassName Scoring
 * @Description TODO
 * @author zhanghui
 * @date May 8, 2018 9:06:15 PM
 */
public class Scoring {
	private static BehaviorLogService behaviorLogService;
	private static PreferencesService preferencesService;
	
	@Resource(name = "behaviorLogService")
	public  void setBehaviorLogService(BehaviorLogService behaviorLogService) {
		Scoring.behaviorLogService = behaviorLogService;
	}
	@Resource(name = "preferencesService")
	public  void setPreferencesService(PreferencesService preferencesService) {
		Scoring.preferencesService = preferencesService;
	}

	public static void score(){
		List<BehaviorLog> behaviorList = behaviorLogService.getAll();
		for (BehaviorLog behaviorLog : behaviorList) {
			long uid = behaviorLog.getUid();
			long nid = behaviorLog.getNid();
			int score = 0;
			score = calculation(behaviorLog);
			Preferences preferences = preferencesService.findByUidNid(uid, nid);
			if (preferences == null || preferences.getId()==null) {
				if (score != 0) {
					Preferences  preferences2 = new Preferences(uid, nid, score);
					preferencesService.createPreference(preferences2);
				}
			}else if (preferences.getId() != null) {
				preferencesService.updatePreference(preferences.getId(), score);
			}
		}
	}
	
	public static int calculation(BehaviorLog behaviorLog){
		int score = 0;
		if (behaviorLog.getClick() > 0) {
			score++;
		}
		if (behaviorLog.getRead() > 0) {
			score++;
		}
		if (behaviorLog.getComment() > 0) {
			score++;
		}
		if (behaviorLog.getForward() > 0) {
			score++;
		}
		if (behaviorLog.getCollect() > 0) {
			score++;
		}
		if (behaviorLog.getOther() > 0) {
			score++;
		}
		return score;
	}
}
