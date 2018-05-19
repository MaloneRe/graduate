package com.personalizednewsrecommendation.manager.dao;

import java.util.*;

import com.personalizednewsrecommendation.manager.pojo.*;
public interface PreferencesDAO {
	public void insertPreferences(Preferences preferences);
	public Preferences getById(Long id);
	public Preferences getByIds(Preferences preferences);
	public Preferences getByUidNid(Long uid, Long nid);
	public List<Preferences> getByUserId(Long uid);
	public List<Preferences> getByNewsId(Long nid);
	/**
	 * 必须要有uid nid 如果只有其中之一者删除某id的全部
	 * @Method: deletePreference 
	 * @param preferences
	 * @throws
	 */
	public void deletePreference(Preferences preferences);
	/**
	 * 同上说明
	 * @Method: updatePreference 
	 * @param preferences
	 * @throws
	 */
	public void updatePreference(Preferences preferences);
	
}
