package com.epidemiologicSurvey.dao.impl;

import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Times;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.epidemiologicSurvey.bean.QuestionRecord;
import com.epidemiologicSurvey.dao.QuestionRecordDao;

@IocBean
public class QuestionRecordDaoImpl implements QuestionRecordDao {
	@Inject
	private Dao dao;

	@SuppressWarnings("rawtypes")
	@Override
	public void saveQuestionRecord(JSONArray question, int recordId) {
		List<Map> list = JSON.parseArray(question.toJSONString(), Map.class);
		for (Map map : list) {
			QuestionRecord record = new QuestionRecord();
			record.setRecordId(recordId);
			record.setQuestion((String) map.get("question"));
			record.setAnswer((String) map.get("trueAnswer"));
			record.setCreateTime(Times.now());
			dao.insert(record);
		}
	}

	@Override
	public List<QuestionRecord> queryQuestionRecord(String recordId) {
		return dao.query(QuestionRecord.class, Cnd.where("recordId", "=", recordId));
	}
}
