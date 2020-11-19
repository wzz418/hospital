package com.epidemiologicSurvey.action;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.alibaba.fastjson.JSONObject;
import com.epidemiologicSurvey.filter.ApiAccessTokenFilter;
import com.epidemiologicSurvey.filter.ApiAccessTokenService;
import com.epidemiologicSurvey.service.QuestionService;

@At("/es/api")
@IocBean
public class ApiAction {
	
	@Inject
	private QuestionService questionService;
	@Inject
	private ApiAccessTokenService apiAccessTokenService;

	@At("/queryRecordList")
	@Ok("json")
	@AdaptBy(type = JsonAdaptor.class)
	@Filters(@By(type = ApiAccessTokenFilter.class))
	public JSONObject queryRecord(@Param("startDate") String startDate, @Param("endDate") String endDate) {
		return questionService.queryRecord(startDate, endDate);
	}
	
	@At("/getToken")
	@Ok("json")
	@AdaptBy(type = JsonAdaptor.class)
	public JSONObject getToken(@Param("userName") String userName, @Param("password") String password) {
		return apiAccessTokenService.getNewToken(userName, password);
	}
	
	
}
