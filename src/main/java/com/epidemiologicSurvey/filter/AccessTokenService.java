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

	// @Inject
	// protected JedisAgent jedisAgent;
	//
	// public AccessToken getNewPCAccessToken(String userId) {
	// Jedis jedis = jedisAgent.getResource();
	// String json = jedis.get(userId);
	// AccessToken at = JSON.parseObject(json, AccessToken.class);
	// if (at != null) {
	// at.setToken(R.UU16());
	// jedis.set(userId, JSON.toJSONString(at));
	// } else {
	// at = new AccessToken();
	// at.setUserId(userId);
	// at.setToken(R.UU16());
	// jedis.set(userId, JSON.toJSONString(at));
	// }
	// jedis.close();
	// return at;
	// }
	//
	// public int checkToken(HttpServletRequest request) {
	// String token = request.getHeader("token");
	// String userId = request.getHeader("userId");
	// logger.info("checkToken: begin token:" + token + " userId:" + userId);
	// Jedis jedis = jedisAgent.getResource();
	// String json = jedis.get(userId);
	// AccessToken at = JSON.parseObject(json, AccessToken.class);
	// jedis.close();
	// if (at == null || !at.getToken().equals(token)) {
	// logger.info("checkToken: end pc");
	// return 100;
	// }
	// return 0;
	//
	// }
	//
	// @Override
	// public String getAccesstokenKey(String var1) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public int getTimeout() {
	// // TODO Auto-generated method stub
	// return 0;
	// }

}
