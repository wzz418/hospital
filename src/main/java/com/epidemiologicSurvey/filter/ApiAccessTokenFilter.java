package com.epidemiologicSurvey.filter;

import javax.servlet.http.HttpServletRequest;

import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;
import org.nutz.mvc.view.UTF8JsonView;

import com.epidemiologicSurvey.utils.ResponseVo;


public class ApiAccessTokenFilter implements ActionFilter{

	private ApiAccessTokenService accessTokenService;

	@Override
	public View match(ActionContext ac) {
		init(ac);	
		HttpServletRequest request = ac.getRequest();
		boolean rst = accessTokenService.checkToken(request.getHeader("token"));
		if (!rst) {	
			return (View) new UTF8JsonView().setData(ResponseVo.error("token错误"));
		}
		return null;
	}

	public void init(ActionContext ac) {
		if (accessTokenService == null) {
			accessTokenService = ac.getIoc().get(ApiAccessTokenService.class);
		}
	}
	
	public void destroy() {
		
	}

}
