package com.ssale.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.ssale.base.impl.BaseServiceImpl;
import com.ssale.dao.MenuDao;
import com.ssale.dao.RoleDao;
import com.ssale.entity.Menu;
import com.ssale.entity.Role;
import com.ssale.service.RoleService;
import com.ssale.util.TreeJudgement;

public class RoleServiceImpl extends BaseServiceImpl<Role, RoleDao> implements RoleService {
	private MenuDao menuDao;

	public MenuDao getMenuDao() {
		return menuDao;
	}

	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}
	
	@Override
	public void updateRole(Role role, Integer[] mids) {
		Role target = this.getBaseDao().getEntityById(role.getId());
		target.setType(role.getType());
		if (TreeJudgement.isNotNull(role.getName())) {
			target.setName(role.getName());
		}
		if (TreeJudgement.isNotNull(role.getDescription())) {
			target.setDescription(role.getDescription());
		}

		if (mids != null && mids.length > 0) {
			List<Menu> menus = (List<Menu>) this.getMenuDao().getEntrysByIDS(mids);
			System.out.println(1111);
			// 对增加父菜单到Menu集合中去
			List<Integer> asList = new ArrayList<Integer>(Arrays.asList(mids));
			for (Menu m : menus) {
				asList.add(m.getParentMenu().getId());
			}
			// 转成set集合去重,再转换成Integer数组;
			HashSet<Integer> hashSet = new HashSet<Integer>(asList);
			mids = hashSet.toArray(new Integer[hashSet.size()]);
			menus = (List<Menu>) this.getMenuDao().getEntrysByIDS(mids);
			role.setMenus(new HashSet<Menu>(menus));
		}
		this.getBaseDao().updateEntity(role);
	}

}
