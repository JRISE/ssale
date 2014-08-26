package com.ssale.dao;

import java.io.Serializable;

import com.ssale.base.BaseDao;
import com.ssale.entity.User;

public interface UserDao extends BaseDao<User> {
	/**
	 * 通过账号和密码获得用户
	 * 
	 * @param account
	 *            账户
	 * @param password
	 *            密码
	 * @return 用户
	 */
	public User getEntityByAccount(Serializable account, Serializable password);

	/**
	 * 通过账号获得用户
	 * 
	 * @param account
	 *            账号
	 * @return 用户
	 */
	public User getEntityByAccount(Serializable account);
}
