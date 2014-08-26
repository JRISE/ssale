package com.ssale.dao;

import java.util.Collection;

import com.ssale.base.BaseDao;
import com.ssale.entity.Menu;



public interface MenuDao extends BaseDao<Menu> {
	/**
	 * 获取所有的父菜单
	 * @return 父菜单的集合
	 */
	public Collection<Menu> getAllPidMenu();
}
