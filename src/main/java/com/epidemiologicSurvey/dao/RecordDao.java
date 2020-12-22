package com.epidemiologicSurvey.dao;

import java.util.List;

import org.nutz.dao.pager.Pager;

import com.alibaba.fastjson.JSONObject;
import com.epidemiologicSurvey.bean.Record;

public interface RecordDao {

	/**
	 * 保存或修改问题记录
	 * 
	 * @param info
	 * @param question
	 */
	Record saveOrUpdataRecord(JSONObject info,int type);

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
	/**
	 * 查询历史记录全部信息
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	JSONObject queryRecord(String startDate,String endDate );
	
	/**
	 * 查询当天该人员提交的次数
	 * @param idCard
	 * @param time
	 * @return
	 */
	List<Record> queryRecordIdCard(String idCard,String time);
}
