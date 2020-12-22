package com.epidemiologicSurvey.dao;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.epidemiologicSurvey.bean.QuestionRecord;

public interface QuestionRecordDao {
	/**
	 * 保存问题记录
	 * @param info
	 * @param question
	 */
	void saveOrUpdataRecord( JSONArray question,int recordId,int type);
	
	/**
	 * Pc端新增或修改问题
	 * @param question
	 * @param type
	 */
	void addOrUpDataQuestionRecord(JSONObject question,String type);
	
	/**
	 * 删除问题
	 * @param question
	 * @param type
	 */
	void delQuestion(String id);
	
	/**
	 * 查询问题详情
	 */
	List<QuestionRecord>  queryQuestionRecord(String recordId);
	
	
}
