package com.epidemiologicSurvey.action;


import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.alibaba.fastjson.JSONObject;
import com.epidemiologicSurvey.service.LoginService;
import com.epidemiologicSurvey.utils.Toolkit;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.text.renderer.DefaultWordRenderer;
import cn.apiclub.captcha.text.renderer.WordRenderer;

@At("/es/login")
@IocBean
public class LoginAction {
 
	@Inject
	private LoginService loginService;
	
	/**
	 * 获取验证码
	 * @param session
	 * @return
	 */
	@At("/captchaImg")
	@Ok("raw:png")
	public BufferedImage next(HttpSession session) {
		List<Color> colors = new ArrayList<Color>();
		colors.add(Color.BLACK);
		// colors.add(Color.BLUE);

		List<Font> fonts = new ArrayList<Font>();
		fonts.add(new Font("Geneva", 2, 24));
		fonts.add(new Font("Courier", 3, 24));

		WordRenderer wordRenderer = new DefaultWordRenderer(colors, fonts);
		Captcha captcha = new Captcha.Builder(70, 26).addText(wordRenderer).build();// 80为宽，22为高
		String text = captcha.getAnswer();
		session.setAttribute(Toolkit.captcha_attr, text);
		return captcha.getImage();
	}
	
	/**
	 * 登陆
	 * zbz
	 * @param session
	 * @param params
	 * @return
	 */
	@At("/login")
	@Ok("json")
	@AdaptBy(type = JsonAdaptor.class)
	public JSONObject pcLogin(HttpSession session, @Param("..") JSONObject params) {
		return loginService.login(session, params);
	}
	/**
	 * zbz
	 * 更新pc端密码
	 * @param params
	 * @return
	 */
	@At("/updatePwd")
	@Ok("json")
	@AdaptBy(type = JsonAdaptor.class)
	public JSONObject pc_updatePwd(@Param("..") JSONObject params) {
		return loginService.updatePwd(params);
	}
}
