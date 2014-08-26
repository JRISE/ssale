package com.ssale.service;

import java.util.Collection;

import com.ssale.base.BaseService;
import com.ssale.entity.Menu;

public interface MenuService extends BaseService<Menu> {

	public Collection<Menu> getAllPidMenu();

}
