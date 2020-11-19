package com.epidemiologicSurvey.filter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.epidemiologicSurvey.bean.ApiUser;
import com.epidemiologicSurvey.utils.ResponseVo;

@IocBean
public class ApiAccessTokenService {
	@Inject
	private Dao dao;

	private static final int EXPIRE_DATE = 7 * 24 * 60 * 60 * 1000;// 7天过期

	private static final String TOKEN_SECRET = "e7eQ7C2vBjiAdHMwiX98v9IU5hqrYvR6";

	public JSONObject getNewToken(String userName, String password) {
		String token = "";
		// 查询是否存在用户
		ApiUser user = dao.fetch(ApiUser.class, Cnd.where("userName", "=", userName).and("password", "=", password));
		if (user == null) {
			return ResponseVo.error("用户名或密码错误");
		}
		try {
			// 过期时间
			Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);
			// 秘钥及加密算法
			Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
			// 设置头部信息
			Map<String, Object> header = new HashMap<>();
			header.put("typ", "JWT");
			header.put("alg", "HS256");
			// 携带userName，password信息，生成签名
			token = JWT.create().withHeader(header).withClaim("userName", userName).withClaim("password", password)
					.withExpiresAt(date).sign(algorithm);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseVo.error(e.getMessage());
		}
		JSONObject rst = new JSONObject();
		rst.put("token", token);
		return ResponseVo.ok(rst);
	}

	public boolean checkToken(String token) {
		if (Strings.isBlank(token)) {
			return false;
		}
		try {
			Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
			JWTVerifier verifier = JWT.require(algorithm).build();
			verifier.verify(token);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
