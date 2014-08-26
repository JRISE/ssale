package com.ssale.test;

import java.util.List;

import org.junit.Test;

import com.ssale.entity.Menu;
import com.ssale.entity.Role;
import com.ssale.service.MenuService;
import com.ssale.service.RoleService;
import com.ssale.util.SpringUtils;

public class RoleServiceTest extends SpringUtils {

	@Test
	public void SaveTest() {
		RoleService roleService = (RoleService) context.getBean("roleService");
		Role role = new Role();
		role.setName("角色2");
		roleService.saveEntity(role);
	}

	@Test
	public void deleteTest() {
		RoleService roleService = (RoleService) context.getBean("roleService");
		Role role = roleService.getEntityById("2c907e1045a6eb740145a6eb77ea0001");
		System.out.println(role.getId());
		roleService.deleteEntity(role.getId());
	}

	@Test
	public void updateTest() {
		Integer[] i = { 220 };
		RoleService roleService = (RoleService) context.getBean("roleService");
		MenuService menuService = (MenuService) context.getBean("menuService");
		Role role = roleService.getEntityById("2c907e1046c80ef60146c813c3db0002");
		System.out.println(role.getId());
		role.setName("角色1");
		roleService.updateEntity(role);
		List<Menu> menus = (List<Menu>) menuService.getEntrysByIDS(i);
		for (Menu m : menus) {
			System.out.println(m.getName());
		}
	}

	@Test
	public void getTest() {
		RoleService roleService = (RoleService) context.getBean("roleService");
		Role role = roleService.getEntityById("2c907e1045a6eb940145a6eb97460001");
		System.out.println(role.getName() + ":" + role.getId());
	}

}
