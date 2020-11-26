package com.epidemiologicSurvey.service;

import org.nutz.dao.pager.Pager;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface QuestionService {


	/**
	 * 查询问题
	 * @return
	 */
	JSONObject queryQuestion();
	
	/**
	 * PC端查询问题
	 * @return
	 */
	JSONObject queryQuestionPC();
	/**
	 * 保存问题记录
	 * @param info
	 * @param question
	 */
	JSONObject saveQuestionRecord(JSONObject info,JSONArray question);
	/**
	 * 查询历史记录
	 * @param openId
	 * @param pager
	 * @return
	 */
	JSONObject queryRecord(Pager pager ,String openId);
	/**
	 * 查询PC历史记录
	 * @param searchInput
	 * @param pager
	 * @return
	 */
	JSONObject queryPCRecord(Pager pager ,String searchInput,String startDate,String endDate);
	/**
	 * 根据记录id查询记录详情
	 * @param recordId
	 * @return
	 */
	JSONObject queryQuestionRecord(String recordId);
	
	/**
	 * 关联查询问题记录表
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	JSONObject queryPCqueryQuestionRecord(String startDate,String endDate);
	/**
	 * 查询历史记录全部信息
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	JSONObject queryRecord(String startDate,String endDate);
}
