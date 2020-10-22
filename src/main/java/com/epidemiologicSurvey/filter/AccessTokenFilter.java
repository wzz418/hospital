package com.epidemiologicSurvey.filter;

import javax.servlet.http.HttpServletRequest;

import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;


public class AccessTokenFilter implements ActionFilter {

	private AccessTokenService accessTokenService;

	@Override
	public View match(ActionContext ac) {
		init(ac);
		HttpServletRequest request = ac.getRequest();
		int code = accessTokenService.checkToken(request);
		if (code == 0) {
			return null;
		} else if (code == 100) {
		return new NologinView();
		}
		return null;
	}

	public void init(ActionContext ac) {
		if (accessTokenService == null) {
			accessTokenService = ac.getIoc().get(AccessTokenService.class);
		}
	}

}
