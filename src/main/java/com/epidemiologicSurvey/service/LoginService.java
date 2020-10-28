package com.epidemiologicSurvey.service;

import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;

public interface LoginService {

	/**
	 * zbz
	 * 登陆
	 * @param session
	 * @param params
	 * @return
	 */
	JSONObject login(HttpSession session,JSONObject params);
	/**
	 * zbz
	 * 更新pc端密码
	 * @param params
	 * @return
	 */
	JSONObject updatePwd(JSONObject params);
	
}
