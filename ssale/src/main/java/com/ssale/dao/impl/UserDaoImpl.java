package com.ssale.dao.impl;

import java.io.Serializable;
import java.util.List;

import com.ssale.base.impl.BaseDaoImpl;
import com.ssale.dao.UserDao;
import com.ssale.entity.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public User getEntityByAccount(Serializable account, Serializable password) {
		@SuppressWarnings("unchecked")
		List<User> elist = (List<User>) this.find(
				"from User where password=? and account=?",password,account);
		if (elist.size() == 0) {
			return null;
		} else {
			return elist.get(0);
		}
	}
	
	@Override
	public User getEntityByAccount(Serializable account) {
		@SuppressWarnings("unchecked")
		List<User> eList = (List<User>) this.find(
				"from User where account=?", account);
		if (eList.size() == 0) {
			return null;
		} else {
			return eList.get(0);
		}
	}
	
}
