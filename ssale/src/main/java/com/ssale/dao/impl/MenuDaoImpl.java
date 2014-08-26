package com.ssale.dao.impl;

import java.util.Collection;

import com.ssale.base.impl.BaseDaoImpl;
import com.ssale.dao.MenuDao;
import com.ssale.entity.Menu;

public class MenuDaoImpl extends BaseDaoImpl<Menu> implements MenuDao {
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Menu> getAllPidMenu() {
		return (Collection<Menu>) this.find("from Menu m where m.id=m.pid order by m.id");
	}

}
