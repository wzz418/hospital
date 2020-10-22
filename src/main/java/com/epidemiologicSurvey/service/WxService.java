package com.epidemiologicSurvey.service;

import com.alibaba.fastjson.JSONObject;

public interface WxService {
	// 微信授权获取openid
	JSONObject getOpenid(String code);

	// 获取微信授权url
	JSONObject oauth2buildAuthorizationUrl(String url);
}
