package com.epidemiologicSurvey.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Times;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.epidemiologicSurvey.bean.QuestionRecord;
import com.epidemiologicSurvey.bean.Record;
import com.epidemiologicSurvey.dao.QuestionDao;
import com.epidemiologicSurvey.dao.QuestionRecordDao;
import com.epidemiologicSurvey.dao.RecordDao;
import com.epidemiologicSurvey.service.QuestionService;
import com.epidemiologicSurvey.utils.ResponseVo;

@IocBean
public class QuestionServiceImpl implements QuestionService {
	private final static Log logger = Logs.get();

	@Inject
	private QuestionDao questionDao;

	@Inject
	private QuestionRecordDao questionRecordDao;

	@Inject
	private RecordDao recordDao;

	@Override
	public JSONObject queryQuestion() {
		return questionDao.queryQuestion();
	}
	@Override
	public JSONObject queryQuestionPC(String str) {
		return questionDao.queryQuestionPC(str);
	}

	@Override
	public JSONObject saveQuestionRecord(JSONObject info, JSONArray question) {
		logger.debug("saveQuestionRecord start info:" + info + " question:" + question);
		try {
			Record user = JSON.parseObject(info.toJSONString(), Record.class);
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dateNowStr = sdf.format(now); 
			List<Record> list = recordDao.queryRecordIdCard(user.getIdCard(),dateNowStr);
			if(list.size()==0){//新增
				Record record = recordDao.saveOrUpdataRecord(info,0);
				questionRecordDao.saveOrUpdataRecord(question, record.getId(),0);
				return ResponseVo.ok();
			}else{//修改
				Record ruser = list.get(0);
				String area = info.getString("area");
				String[] areas = area.split("/");
				ruser.setProvince(areas[0]);
				ruser.setCity(areas[1]);
				ruser.setDistrict(areas[2]);
				JSONObject jsonObject = (JSONObject) JSONObject.toJSON(ruser);		
				recordDao.saveOrUpdataRecord(jsonObject,1);
				questionRecordDao.saveOrUpdataRecord(question,ruser.getId(),1);
				return ResponseVo.ok();
			}
			
		} catch (Exception e) {
			logger.error("saveQuestionRecord error " + e.getMessage());
			return ResponseVo.error();
		}
	}

	@Override
	public JSONObject queryRecord(Pager pager, String openId) {
		try {
			JSONObject rst = recordDao.queryRecord(pager, openId);
			return ResponseVo.ok(rst);
		} catch (Exception e) {
			logger.error("queryRecord error " + e.getMessage());
			return ResponseVo.error();
		}
	}

	@Override
	public JSONObject queryQuestionRecord(String recordId) {
		try {
			List<QuestionRecord> map = questionRecordDao.queryQuestionRecord(recordId);
			JSONObject rst = new JSONObject();
			rst.put("record",map);
			return ResponseVo.ok(rst);
		} catch (Exception e) {
			logger.error("queryQuestionRecord error " + e.getMessage());
			return ResponseVo.error();
		}
	}

	@Override
	public JSONObject queryPCRecord(Pager pager, String searchInput,String startDate,String endDate) {
		try {
			JSONObject rst = recordDao.queryPcRecord(pager, searchInput,startDate,endDate);
			return ResponseVo.ok(rst);
		} catch (Exception e) {
			logger.error("queryPCRecord error " + e.getMessage());
			return ResponseVo.error();
		}
	}

	@Override
	public JSONObject queryPCqueryQuestionRecord(String startDate, String endDate) {
		try {
			JSONObject rst = recordDao.queryPCqueryQuestionRecord(startDate,endDate);
			return ResponseVo.ok(rst);
		} catch (Exception e) {
			logger.error("queryPCqueryQuestionRecord error " + e.getMessage());
			return ResponseVo.error();
		}
	}

	@Override
	public JSONObject queryRecord(String startDate, String endDate) {
		try {
			JSONObject rst = recordDao.queryRecord(startDate, endDate);
			return ResponseVo.ok(rst);
		} catch (Exception e) {
			logger.error("queryRecord error " + e.getMessage());
			return ResponseVo.error();
		}
	}
	@Override
	public JSONObject addOrUpDataQuestionRecord(JSONObject question,String type) {
		try {
			questionRecordDao.addOrUpDataQuestionRecord(question,type);
			return ResponseVo.ok();
		} catch (Exception e) {
			logger.error("queryRecord error " + e.getMessage());
			return ResponseVo.error();
		}
	}
	
	@Override
	public JSONObject delQuestion(String id) {
		try {
			questionRecordDao.delQuestion(id);
			return ResponseVo.ok();
		} catch (Exception e) {
			logger.error("queryRecord error " + e.getMessage());
			return ResponseVo.error();
		}
	}

}
