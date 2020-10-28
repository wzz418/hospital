package com.epidemiologicSurvey.action;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.alibaba.fastjson.JSONObject;
import com.epidemiologicSurvey.service.QuestionService;

@IocBean
@At("/es/pcQuestion")
public class QuestionPCAction {

	@Inject
	private QuestionService questionService;

	/**
	 * 查询记录
	 * 
	 * @param pager
	 * @param searchInput
	 * @return
	 */
	@At("/queryRecord")
	@Ok("json")
	@AdaptBy(type = JsonAdaptor.class)
	public JSONObject queryRecord(@Param("..") Pager pager, @Param("searchInput") String searchInput,
			@Param("startDate") String startDate, @Param("endDate") String endDate) {
		return questionService.queryPCRecord(pager, searchInput, startDate, endDate);

	}

	@At("/queryQuestionRecord")
	@Ok("json")
	@AdaptBy(type = JsonAdaptor.class)
	public JSONObject queryQuestionRecord(@Param("startDate") String startDate, @Param("endDate") String endDate) {
		return questionService.queryPCqueryQuestionRecord(startDate, endDate);
	}

}
