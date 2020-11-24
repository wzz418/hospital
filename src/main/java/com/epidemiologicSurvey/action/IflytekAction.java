package com.epidemiologicSurvey.action;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;

import com.alibaba.fastjson.JSONObject;
import com.epidemiologicSurvey.service.IflytekService;

@At("/es/iflytek")
@IocBean
public class IflytekAction {

	@Inject
	private IflytekService iflytekService;

	@At("/listen")
	@Ok("json")
	@AdaptBy(type = UploadAdaptor.class)
	public JSONObject listen(@Param("file") TempFile tmpFile) {
		return	iflytekService.listenToWrite(tmpFile);
	}

}
