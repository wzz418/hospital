package com.epidemiologicSurvey.action;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.epidemiologicSurvey.service.QuestionService;

@IocBean
@At("/es/question")
public class QuestionAction {

	@Inject
	private QuestionService questionService;
	
	
	/**
	 * 查询记录
	 * @param pager
	 * @param openId
	 * @return
	 */
	@At("/queryRecord")
	@Ok("json")
	@AdaptBy(type=JsonAdaptor.class)
	public JSONObject queryRecord(@Param("..") Pager pager,@Param("openId") String openId){
		return questionService.queryRecord(pager, openId);
		
	}
	
	@At("/queryQuestionRecord")
	@Ok("json")
	@AdaptBy(type=JsonAdaptor.class)
	public JSONObject queryQuestionRecord(@Param("recordId") String recordId){
		return questionService.queryQuestionRecord(recordId);
		
	}
	
	
	
	/**
	 * 查询问题
	 * @return
	 */
	@At("/queryQuestion")
	@Ok("json")
	@AdaptBy(type=JsonAdaptor.class)
	public JSONObject queryQuestion(){
		return questionService.queryQuestion();	
	}
	
	
	/**
	 * PC端查询问题
	 * @return
	 */
	@At("/queryQuestionPC")
	@Ok("json")
	@AdaptBy(type=JsonAdaptor.class)
	public JSONObject queryQuestionPC(){
		return questionService.queryQuestionPC();	
	}
	
	
	/**
	 * 保存问题记录
	 * @return
	 */
	@At("/saveQuestionRecord")
	@Ok("json")
	@AdaptBy(type=JsonAdaptor.class)
	public JSONObject saveQuestionRecord(@Param("info") JSONObject info, @Param("question") JSONArray question){
		return questionService.saveQuestionRecord(info,question);
	}
}
