package com.epidemiologicSurvey.dao;

import com.epidemiologicSurvey.bean.User;

public interface UserDao {
	
	/**
	 * zbz
	 * 根据id查询用户
	 * @param userId
	 * @return
	 */
	User fetchUserById(String userId);
	
	/**
	 * zbz
	 * 更新用户密码
	 * @param user
	 */
	void update(User user);
	
	/**
	 * zbz
	 * 根据账号查询用户
	 * @param account
	 * @return
	 */
	User fetchUserByAccount(String account);

}
