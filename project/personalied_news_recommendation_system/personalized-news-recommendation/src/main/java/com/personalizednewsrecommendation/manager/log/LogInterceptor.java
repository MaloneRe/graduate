package com.personalizednewsrecommendation.manager.log;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.personalizednewsrecommendation.manager.service.BehaviorLogService;

import controller.BaseControllerTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.regex.*;
import javax.servlet.http.*;

import com.alibaba.fastjson.JSONObject;
import com.personalizednewsrecommendation.manager.pojo.*;

public class LogInterceptor extends HandlerInterceptorAdapter {
	private static Logger log = LoggerFactory.getLogger(LogInterceptor.class);

	@Resource(name = "behaviorLogService")
	private BehaviorLogService behaviorLogService;
	
	private HttpSession session;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		log.info("prehandle============>");
		session = request.getSession();
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request
			, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("postHandle============>");
		// StringBuffer stringBuffer = new StringBuffer("/api/");
		String url = request.getRequestURI().toString();
		String methodType = request.getMethod().toString();
		
		User user = (User) session.getAttribute("currentUser");
		Long uid = null;
		if (user != null) {
			uid = user.getId();
			Pattern pattern = Pattern.compile("/\\d+/??");
			Matcher matcher = pattern.matcher(url);
			String paramId = null;
			if (matcher.find()) {
				paramId = url.substring(matcher.start() + 1, matcher.end());

			}
			// org.junit.Assert.assert
			// Pattern patternClick = Pattern.compile(regex)
			log.info("url:{}, userId:{}, paramer:{}", url, uid, paramId);
			if (UrlMatcher.getRegxClick().matcher(url).matches() && methodType.equals("GET")) {
				if (uid != null && paramId != null) {
					Long nid = Long.parseLong(paramId);

					behaviorLogService.updateClickCount(uid, nid);
					behaviorLogService.updateReadCount(uid, nid);
				}
			} else if (UrlMatcher.getRegxComment().matcher(url).matches() && methodType.equals("POST")) {
				if (uid != null && paramId != null) {
					behaviorLogService.updateCommentCount(uid, Long.parseLong(paramId));
				}

			} else if (UrlMatcher.getRegxBehavior().matcher(url).matches() && methodType.equals("POST")) {
				if (uid != null && paramId != null) {
					try {
						BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
						String line = null;
						StringBuilder sb = new StringBuilder();
						while ((line = br.readLine()) != null) {
							sb.append(line);
						}

						String reqBody = URLDecoder.decode(sb.toString(), HTTP.UTF_8);
						log.info("Request Body:" + reqBody);
						JSONObject json = JSONObject.parseObject(reqBody);
						log.info("[getRequestPostJson][" + json + "]-- get request body with json success.");
						String behavior = (String) json.get("behavior");
						if (behavior.equals("collection")) {
							/**
							 * 
							 */
							behaviorLogService.updateCollectCount(uid, Long.parseLong(paramId));
						} else if (behavior.equals("share")) {
							/**
							 * 
							 */
							behaviorLogService.updateShareCount(uid, Long.parseLong(paramId));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		log.info("request url is :{}, method type is :{}", url, methodType);
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws java.lang.Exception {
		// TODO Auto-generated method stub
		log.info("afterCompletion=========>");
		super.afterCompletion(request, response, handler, ex);
	}

}
