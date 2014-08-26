package com.ssale.service.impl;

import java.util.Collection;

import com.ssale.base.impl.BaseServiceImpl;
import com.ssale.dao.MenuDao;
import com.ssale.entity.Menu;
import com.ssale.service.MenuService;

public class MenuServiceImpl extends BaseServiceImpl<Menu, MenuDao> implements MenuService {

	@Override
	public Collection<Menu> getAllPidMenu() {
		return this.getBaseDao().getAllPidMenu();
	}

}
