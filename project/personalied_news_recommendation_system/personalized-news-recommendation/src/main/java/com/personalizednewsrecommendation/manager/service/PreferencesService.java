package com.personalizednewsrecommendation.manager.service;

import com.personalizednewsrecommendation.manager.pojo.*;

public interface PreferencesService {
	public Preferences findByUidNid(Long uid, Long nid);

	public Long createPreference(Preferences preferences);

	public void updatePreference(Long id, int score);
}
