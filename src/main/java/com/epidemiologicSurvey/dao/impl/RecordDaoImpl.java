package com.epidemiologicSurvey.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Times;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.epidemiologicSurvey.bean.Record;
import com.epidemiologicSurvey.dao.RecordDao;

@IocBean
public class RecordDaoImpl implements RecordDao {
	@Inject
	private Dao dao;

	@Override
	public Record saveOrUpdataRecord(JSONObject info,int type) {
		Record record = JSON.parseObject(info.toJSONString(), Record.class);
		String area = info.getString("area");
		if(area != null){
			String[] areas = area.split("/");
			record.setProvince(areas[0]);
			record.setCity(areas[1]);
			record.setDistrict(areas[2]);
		}
		
		record.setCreateTime(Times.now());
		if(type == 0){
			return dao.insert(record);			
		}else{
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dateNowStr = sdf.format(now); 
			 dao.updateIgnoreNull(record);
			 return record;
		}
//		Record record2 = dao.fetch(Record.class, Cnd.where("idCard","=",record.getIdCard()).desc("createTime"));
//		 record2;
	}

	@Override
	public JSONObject queryRecord(Pager pager, String openId) {
		pager.setRecordCount(dao.count(Record.class, Cnd.where("openId", "=", openId)));
		List<Record> list = dao.query(Record.class, Cnd.where("openId", "=", openId), pager);
		JSONObject rst = new JSONObject();
		rst.put("list", list);
		rst.put("pager", pager);
		return rst;
	}

	@Override
	public JSONObject queryPcRecord(Pager pager, String searchInput,String startDate,String endDate) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select id,name,phone,sex,CONCAT(LEFT(idCard,4), '**********' ,RIGHT(idCard,4)) as idCard,province,city,district,address,createTime,dept from record where ");
		sb.append(" (name like @searchInput or phone like @searchInput or idCard like @searchInput ) ");
		sb.append(" and createTime between @startDate and @endDate");
		sb.append(" order by createTime desc");
		Sql sql = Sqls.create(sb.toString());
		sql.setEntity(dao.getEntity(Record.class));
		sql.setParam("searchInput", "%" + searchInput + "%");
		sql.setParam("startDate", startDate);
		sql.setParam("endDate", endDate);
		pager.setRecordCount((int) Daos.queryCount(dao, sql));
		sql.setPager(pager);
		sql.setCallback(Sqls.callback.entities());
		dao.execute(sql);
		List<Record> list = sql.getList(Record.class);
		JSONObject rst = new JSONObject();
		rst.put("list", list);
		rst.put("pager", pager);
		return rst;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public JSONObject queryPCqueryQuestionRecord( String startDate, String endDate) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT  sum(case when q.answer='是' then 1 else 0 end) Y, sum(case when q.answer='否' then 1 else 0 end) F, q.`question`  ");
		sb.append(" from ( select * from  questionrecord where createTime between @startDate and @endDate)q ");
		sb.append(" group by q.`question` ");
		Sql sql = Sqls.create(sb.toString());
		sql.setParam("startDate", startDate);
		sql.setParam("endDate", endDate);
		sql.setCallback(Sqls.callback.maps());
		dao.execute(sql);
		List<Map> list = sql.getList(Map.class);
		JSONObject rst = new JSONObject();
		rst.put("list", list);
		return rst;
	}

	@Override
	public JSONObject queryRecord(String startDate, String endDate) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select r.*,qs.questionList from record r ");
		sb.append("  left join (   ");
	    sb.append("    select  q.recordId,  CONCAT(  '[',  GROUP_CONCAT( CONCAT( '{ \"question\":', q.question , ', \"answer\":\"', q.answer, '\" }' ) SEPARATOR ', '), ']'   ) questionList ");
		sb.append("  from questionrecord q group by q.recordId) qs ");
		sb.append("  on r.id = qs.recordId ");
	     sb.append(" where r.createTime between @startDate  and @endDate ");
		Sql sql = Sqls.create(sb.toString());
		sql.setParam("startDate", startDate);
		sql.setParam("endDate", endDate);
		sql.setCallback(Sqls.callback.records());
		dao.execute(sql);
		List<org.nutz.dao.entity.Record> list = sql.getList(org.nutz.dao.entity.Record.class);		
		for(org.nutz.dao.entity.Record r : list){		
			System.out.println(r);
		}
		JSONObject rst = new JSONObject();
		rst.put("list", list);
		return rst;
	}

	@Override
	public List<Record> queryRecordIdCard(String idCard, String time) {
		 List<Record> users =dao.query(Record.class,Cnd.where("createTime","like",time+'%').and("idCard","=",idCard));
		return users;
	}

}
