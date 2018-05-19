package com.personalizednewsrecommendation.manager.service.impl;

import com.personalizednewsrecommendation.manager.pojo.Preferences;
import com.personalizednewsrecommendation.manager.dao.*;
import com.personalizednewsrecommendation.manager.service.PreferencesService;
import org.springframework.stereotype.*;

import java.util.List;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import javax.annotation.*;

@Service("preferencesService")
public class PreferencesServiceImpl implements PreferencesService {

	@Autowired
	private PreferencesDAO preferencesDAO;
	@Override
	public Preferences findByUidNid(Long uid, Long nid) {
		
		return preferencesDAO.getByUidNid(uid, nid);
	}
	@Override
	public Long createPreference(Preferences preferences) {
		
		preferencesDAO.insertPreferences(preferences);
		return preferences.getId();
	}
	@Override
	public void updatePreference(Long id, int score) {
		Preferences preferences = new Preferences(id, score);
		preferencesDAO.updatePreference(preferences);
	}

}
