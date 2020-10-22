package com.epidemiologicSurvey.action;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Param;

import com.alibaba.fastjson.JSONObject;

@IocBean
@At("/es/question")
public class QuestionAction {

	/**
	 * 查询记录
	 * @param pager
	 * @param openId
	 * @return
	 */
	@At("/queryRecord")
	public JSONObject queryRecord(@Param("..") Pager pager,@Param("openId") String openId){
		return null;
		
	}
	
	/**
	 * 查询问题
	 * @return
	 */
	@At("/queryQuestion")
	public JSONObject queryQuestion(){
		return null;
		
	}
}
