package com.epidemiologicSurvey.dao.impl;

import java.util.List;
import java.util.Map;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Times;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.epidemiologicSurvey.bean.Question;
import com.epidemiologicSurvey.bean.QuestionRecord;
import com.epidemiologicSurvey.bean.Record;
import com.epidemiologicSurvey.dao.QuestionRecordDao;

@IocBean
public class QuestionRecordDaoImpl implements QuestionRecordDao {
	@Inject
	private Dao dao;

	@SuppressWarnings("rawtypes")
	@Override
	public void saveOrUpdataRecord(JSONArray question, int recordId,int type) {
		List<Map> list = JSON.parseArray(question.toJSONString(), Map.class);
	
		for (Map map : list) {
			QuestionRecord record = new QuestionRecord();
			record.setRecordId(recordId);
			record.setQuestion((String) map.get("question"));
			record.setAnswer((String) map.get("trueAnswer"));
			record.setInputText((String) map.get("inputText"));
			record.setCreateTime(Times.now());
			if(type ==0 ){
				dao.insert(record);
			}else{
				dao.update(QuestionRecord.class,Chain.make("answer",record.getAnswer()).add("inputText",record.getInputText())
						,Cnd.where("question","=",record.getQuestion()).and("recordId","=",recordId));
				
			}
			
		}
	}

	@Override
	public List<QuestionRecord> queryQuestionRecord(String recordId) {
		return dao.query(QuestionRecord.class, Cnd.where("recordId", "=", recordId));
	}

	@Override
	public void addOrUpDataQuestionRecord(JSONObject question, String type) {
		Question user = JSON.parseObject(question.toJSONString(), Question.class);
		user.setCreateTime(Times.now());
		if("0".equals(type) ){
			dao.insert(user);
		}else{
			dao.update(user);			
		}
		
	}
	@Override
	public void delQuestion(String id) {
		dao.update(Question.class,Chain.make("fsfsc", 1),Cnd.where("id","=",id));
	}

}
