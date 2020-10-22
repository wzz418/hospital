package com.epidemiologicSurvey.action;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Param;

import com.alibaba.fastjson.JSONObject;

@IocBean
@At("/es/pcQuestion")
public class QuestionPCAction {
 
	/**
	 * 查询记录
	 * @param pager
	 * @param searchInput
	 * @return
	 */
	@At("/queryRecord")
	public JSONObject queryRecord(@Param("..") Pager pager,@Param("searchInput") String searchInput){
		return null;
		
	}
}
