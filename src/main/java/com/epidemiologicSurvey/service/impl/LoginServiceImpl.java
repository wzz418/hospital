package com.epidemiologicSurvey.service.impl;

import javax.servlet.http.HttpSession;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.lang.random.R;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.alibaba.fastjson.JSONObject;
import com.epidemiologicSurvey.bean.User;
import com.epidemiologicSurvey.dao.UserDao;
import com.epidemiologicSurvey.filter.AccessTokenService;
import com.epidemiologicSurvey.service.LoginService;
import com.epidemiologicSurvey.utils.ResponseVo;
import com.epidemiologicSurvey.utils.Toolkit;

@IocBean
public class LoginServiceImpl implements LoginService {

	private static final Log logger = Logs.get();

	@Inject
	private AccessTokenService accessTokenService;
	@Inject
	private UserDao userDao;

	@Override
	public JSONObject updatePwd(JSONObject params) {
		String userId = params.getString("userId");
		String oldPwd = params.getString("oldPwd");
		String newPwd = params.getString("newPwd");
		User user = userDao.fetchUserById(userId);

		if (user == null) {
			return ResponseVo.error("账号不存在！");
		}

		if (Lang.md5(oldPwd.toLowerCase() + user.getSalt()).equals(user.getPassword())) {
			user.setSalt(R.UU16());
			user.setPassword(Lang.md5(newPwd.toLowerCase() + user.getSalt()));
			userDao.update(user);
			return ResponseVo.ok("更新密码成功");
		} else {
			return ResponseVo.error("原密码错误！");
		}
	}

	@Override
	public JSONObject login(HttpSession session, JSONObject params) {
		logger.debug("user login: params:" + params);
		String account = params.getString("account");
		String password = params.getString("password");
		String captcha = params.getString("captcha");
		if (!captcha.equals(session.getAttribute(Toolkit.captcha_attr))) {
			return ResponseVo.error("验证码不正确");
		}
		password = password.toLowerCase();
		User user = userDao.fetchUserByAccount(account);
		if (user == null) {
			return ResponseVo.error("用户不存在！");
		}
		String pwd = Strings.isEmpty(user.getSalt()) ? Lang.md5(password) : Lang.md5(password + user.getSalt());
		if (!pwd.equals(user.getPassword())) {
			return ResponseVo.error("用户或密码不正确！");
		}

		// 这时已经验证密码正确性，如果未加盐值，则给密码添加盐值
		if (Strings.isEmpty(user.getSalt())) {
			user.setSalt(R.UU16());
			user.setPassword(Lang.md5(user.getPassword() + user.getSalt()));
			userDao.update(user);
		}
		JSONObject rst = new JSONObject();
		rst.put("userid", user.getId());
		rst.put("token", accessTokenService.getNewAccessToken(user.getId()));
		rst.put("userName", user.getName());
		return ResponseVo.ok(rst);
	}

}
