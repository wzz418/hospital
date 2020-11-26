package com.epidemiologicSurvey.dao;

import com.alibaba.fastjson.JSONObject;

public interface QuestionDao {
	
	/**
	 * 查询问题
	 * @return
	 */
	JSONObject queryQuestion();

	JSONObject queryQuestionPC();

}
