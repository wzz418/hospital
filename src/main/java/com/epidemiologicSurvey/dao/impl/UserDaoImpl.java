package com.epidemiologicSurvey.dao.impl;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.epidemiologicSurvey.bean.User;
import com.epidemiologicSurvey.dao.UserDao;

@IocBean
public class UserDaoImpl implements UserDao {

	@Inject
	private Dao dao;

	@Override
	public User fetchUserById(String userId) {
		return dao.fetch(User.class, userId);
	}

	@Override
	public void update(User user) {
		dao.update(user);
	}

	@Override
	public User fetchUserByAccount(String account) {
		return dao.fetch(User.class, Cnd.where("account", "=", account));
	}

}
