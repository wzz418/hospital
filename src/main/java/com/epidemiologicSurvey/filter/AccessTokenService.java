package com.epidemiologicSurvey.filter;

import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class AccessTokenService extends AbstractAccessTokenService {

	private static final int timeout = 7200;
	private static final String ACCESSTOKEN_NAMESPACE = "u:token:pc:";

	public AccessTokenService() {
		
	}

	public String getAccesstokenKey(String userId) {
		return ACCESSTOKEN_NAMESPACE + userId;
	}

	public int getTimeout() {
		return timeout;
	}

}
