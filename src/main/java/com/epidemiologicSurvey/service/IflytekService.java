package com.epidemiologicSurvey.service;

import org.nutz.mvc.upload.TempFile;

import com.alibaba.fastjson.JSONObject;

public interface IflytekService {
	
	JSONObject listenToWrite(TempFile tmpFile);
}
