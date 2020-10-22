package com.epidemiologicSurvey.filter;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.nutz.aop.interceptor.async.Async;
import org.nutz.integration.jedis.JedisAgent;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.lang.Strings;
import org.nutz.lang.Times;
import org.nutz.lang.random.R;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;

public abstract class AbstractAccessTokenService {
	@Inject
	protected JedisAgent jedisAgent;

	public AbstractAccessTokenService() {

	}

	private AccessToken getAccessToken(String userid) {
		return this.get(userid);
	}

	private void insert(AccessToken at) {
		String key = this.getAccesstokenKey(at.getUserId());
		try {
			Jedis jedis = this.jedisAgent.getResource();
			try {
				jedis.set(key, JSON.toJSONString(at));
				jedis.expire(key, this.getTimeout());
			} finally {
				if (jedis != null) {
					jedis.close();
				}
			}
		} catch (Throwable var11) {
			throw var11;
		}
	}

	private void update(AccessToken at) {
		String key = this.getAccesstokenKey(at.getUserId());
		try {
			Jedis jedis = this.jedisAgent.getResource();

			try {
				jedis.set(key, JSON.toJSONString(at));
				jedis.expire(key, this.getTimeout());
			} finally {
				if (jedis != null) {
					jedis.close();
				}

			}

		} catch (Throwable var11) {
			throw var11;
		}
	}

	public AccessToken get(String userId) {
		String key = getAccesstokenKey(userId);
		// 从redis数据库中根据token取出用户相关信息
		try (Jedis jedis = jedisAgent.getResource()) {
			String json = jedis.get(key);
			AccessToken at = JSON.parseObject(json, AccessToken.class);
			return at;
		}
	}

	public abstract String getAccesstokenKey(String var1);

	public abstract int getTimeout();

	@Async
	public void updateAsync(AccessToken at, HttpServletRequest request) {
		if (at.getUpdateTime() == null || System.currentTimeMillis() - at.getUpdateTime().getTime() > 300000L) {
			at.setUpdateTime(Times.now());
			this.update(at);
		}

	}

	public int checkToken(HttpServletRequest request) {
		String token = request.getHeader("token");
		String userId = request.getHeader("userId");
		if (Strings.isNotBlank(userId) && Strings.isNotBlank(token)) {
			AccessToken at = this.getAccessToken(userId);
			if (at == null) {
				return 100;
			} else {
				if (!at.getToken().equals(token)) {
					return 100;
				} else {
					this.updateAsync(at, request);
					return 0;
				}
			}
		} else {
			return 100;
		}
	}

	public String getNewAccessToken(String userId) {
		AccessToken at = this.get(userId);
		if (at == null) {
			at = new AccessToken();
			at.setId(R.UU16());
			at.setCreateTime(Times.now());
			at.setUpdateTime((Date) null);
			at.setUserId(userId);
			at.setToken(R.UU16());
			this.insert(at);
		} else {
			at.setCreateTime(Times.now());
			at.setUpdateTime((Date) null);
			at.setToken(R.UU16());
			this.update(at);
		}

		return at.getToken();
	}
}
