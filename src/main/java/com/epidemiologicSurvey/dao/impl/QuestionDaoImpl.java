package com.epidemiologicSurvey.dao.impl;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.alibaba.fastjson.JSONObject;
import com.epidemiologicSurvey.bean.Question;
import com.epidemiologicSurvey.dao.QuestionDao;
import com.epidemiologicSurvey.utils.ResponseVo;

@IocBean
public class QuestionDaoImpl implements QuestionDao {

	@Inject
	private Dao dao;

	@Override
	public JSONObject queryQuestion() {
		List<Question> list = dao.query(Question.class, Cnd.NEW());
		JSONObject rst = new JSONObject();
		rst.put("list", list);
		return ResponseVo.ok(rst);
	}

}
