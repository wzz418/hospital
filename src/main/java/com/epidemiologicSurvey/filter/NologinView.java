package com.epidemiologicSurvey.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.mvc.View;

public class NologinView implements View{

	@Override
	public void render(HttpServletRequest request, HttpServletResponse response,
			Object object) throws Throwable {
		response.setContentType("application/json"); 
		response.getWriter().write("{\"code\":100}");
		
	}

}
