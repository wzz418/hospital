package com.epidemiologicSurvey.dao;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.epidemiologicSurvey.bean.QuestionRecord;

public interface QuestionRecordDao {
	/**
	 * 保存问题记录
	 * @param info
	 * @param question
	 */
	void saveQuestionRecord( JSONArray question,int recordId);
	/**
	 * 查询问题详情
	 */
	List<QuestionRecord>  queryQuestionRecord(String recordId);
}
