package com.ssale.service.impl;

import java.io.Serializable;

import com.ssale.base.impl.BaseServiceImpl;
import com.ssale.dao.UserDao;
import com.ssale.entity.User;
import com.ssale.service.UserService;

public class UserServiceImpl extends BaseServiceImpl<User, UserDao> implements UserService {

	@Override
	public User getUserByAccount(Serializable account, Serializable password) {
		return this.getBaseDao().getEntityByAccount(account, password);
	}

	@Override
	public User getUserByAccount(Serializable account) {
		return this.getBaseDao().getEntityByAccount(account);
	}
	
}
