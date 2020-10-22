package com.epidemiologicSurvey.service.impl;

import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;

import com.alibaba.fastjson.JSONObject;
import com.epidemiologicSurvey.service.WxService;
import com.epidemiologicSurvey.utils.ResponseVo;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;

@IocBean
public class WxServiceImpl implements WxService {

	@Inject
	private PropertiesProxy conf;
	private WxMpService wxMpService;

	public void init() {
		WxMpDefaultConfigImpl wxConfig = new WxMpDefaultConfigImpl();
		wxConfig.setAppId(conf.get("wx.appId")); // 设置微信公众号的appid
		wxConfig.setSecret(conf.get("wx.appSecret")); // 设置微信公众号appSerct
		wxConfig.setToken(conf.get("wx.token"));
		wxMpService = new WxMpServiceImpl();
		wxMpService.setWxMpConfigStorage(wxConfig);
	}

	// 微信授权获取openid
	@Override
	public JSONObject getOpenid(String code) {
		JSONObject rst = new JSONObject();
		if (Strings.isBlank(code)) {
			return ResponseVo.error("code为空");
		}
		try {
			WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.getOAuth2Service().getAccessToken(code);
			// WxMpUser wxMpUser =
			// wxMpService.getOAuth2Service().getUserInfo(wxMpOAuth2AccessToken,
			// null);
			rst.put("openId", wxMpOAuth2AccessToken.getOpenId());
			return ResponseVo.ok(rst);
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
		return ResponseVo.error("获取openid异常");
	}

	// 获取微信授权url
	@Override
	public JSONObject oauth2buildAuthorizationUrl(String url) {
		JSONObject rst = new JSONObject();
		if (Strings.isBlank(url)) {
			return ResponseVo.error("url为空");
		}
		rst.put("value",
				wxMpService.getOAuth2Service().buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, null));
		return ResponseVo.ok(rst);
	}
}
