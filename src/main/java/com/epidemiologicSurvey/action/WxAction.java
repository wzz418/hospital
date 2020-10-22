package com.epidemiologicSurvey.action;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.alibaba.fastjson.JSONObject;
import com.epidemiologicSurvey.service.WxService;

@IocBean
@At("/es/wx")
public class WxAction {

	@Inject
	private WxService wxService;
	
	//微信授权获取openid
	@At("/get/openid")
	@Ok("json")
	@AdaptBy(type=JsonAdaptor.class)
	public JSONObject get_openid(@Param("code") String code){
		return wxService.getOpenid(code);
	}
	//获取微信授权url
	@At("/get/oauth2buildAuthorizationUrl")
	@Ok("json")
	@AdaptBy(type=JsonAdaptor.class)
	public JSONObject get_oauth2buildAuthorizationUrl(@Param("url") String url){
		return wxService.oauth2buildAuthorizationUrl(url);
	}
}
