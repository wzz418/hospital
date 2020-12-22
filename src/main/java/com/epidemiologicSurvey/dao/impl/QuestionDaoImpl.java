package com.epidemiologicSurvey.dao.impl;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.alibaba.fastjson.JSONObject;
import com.epidemiologicSurvey.bean.Question;
import com.epidemiologicSurvey.bean.QuestionPC;
import com.epidemiologicSurvey.dao.QuestionDao;
import com.epidemiologicSurvey.utils.ResponseVo;

@IocBean
public class QuestionDaoImpl implements QuestionDao {

	@Inject
	private Dao dao;

	@Override
	public JSONObject queryQuestion() {
		List<Question> list = dao.query(Question.class, Cnd.where("fsfsc","=","0").and("fsfqy","=","1"));
		JSONObject rst = new JSONObject();
		rst.put("list", list);
		return ResponseVo.ok(rst);
	}
	
	@Override
	public JSONObject queryQuestionPC(String str) {
		JSONObject rst = new JSONObject();
		if(!str.isEmpty()&&!"2".equals(str)){//1为查询问题管理页面数据
			List<QuestionPC> list = dao.query(QuestionPC.class, Cnd.where("question","like","%"+str+"%").and("fsfsc","=","0"));
			rst.put("list", list);
		}else if("2".equals(str)){//该查询数据为 调查记录页面新增功能问题查询
			List<QuestionPC> list = dao.query(QuestionPC.class, Cnd.where("fsfsc","=","0").and("fsfqy","=","1"));
			rst.put("list", list);
		}else{
			List<QuestionPC> list = dao.query(QuestionPC.class, Cnd.where("fsfsc","=","0"));
			rst.put("list", list);
		}
		return ResponseVo.ok(rst);
	}

}
