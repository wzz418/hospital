package com.epidemiologicSurvey.dao;

import org.nutz.dao.pager.Pager;

import com.alibaba.fastjson.JSONObject;
import com.epidemiologicSurvey.bean.Record;

public interface RecordDao {

	/**
	 * 保存问题记录
	 * 
	 * @param info
	 * @param question
	 */
	Record saveRecord(JSONObject info);

	/**
	 * 查询历史记录
	 * 
	 * @param openId
	 * @param pager
	 * @return
	 */
	JSONObject queryRecord(Pager pager, String openId);
	
	/**
	 * PC端查询历史记录
	 * @param pager
	 * @param searchInput
	 * @return
	 */
	JSONObject queryPcRecord(Pager pager, String searchInput,String startDate,String endDate);
	/**
	 * 关联查询问题记录表
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	JSONObject queryPCqueryQuestionRecord(String startDate,String endDate);
}
