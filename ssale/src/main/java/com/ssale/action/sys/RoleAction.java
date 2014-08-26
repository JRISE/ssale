package com.ssale.action.sys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.ssale.base.BaseAction;
import com.ssale.entity.Menu;
import com.ssale.entity.Role;
import com.ssale.service.MenuService;
import com.ssale.service.RoleService;
import com.ssale.util.Page;
import com.ssale.util.TreeUtil;

public class RoleAction extends BaseAction<Role> {

	private static final long serialVersionUID = 3000976600320734597L;
	private Integer[] mids;
	private String pageNum;
	private RoleService roleService;
	private MenuService menuService;

	public Integer[] getMids() {
		return mids;
	}

	public void setMids(Integer[] mids) {
		this.mids = mids;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public String toList() {
		int count = roleService.getAllCount();
		// num是当前页码
		String num = ServletActionContext.getRequest().getParameter("num");
		// 每页显示的个数
		int pageSize = 15;
		int pNum = TreeUtil.pageNum(num, pageNum, count, pageSize);

		List<Role> list = (List<Role>) roleService
				.getEntityPerPage((pNum - 1) * pageSize, pageSize);

		Page<Role> page = new Page<Role>(pNum, list, count, pageSize);
		ActionContext.getContext().put("page", page);

		return LISTACTION;
	}

	public String delete() {
		this.roleService.deleteEntity(this.getModel().getId());
		return ACTION2ACTION;
	}

	public String deleteBatch() {
		String ids = ServletActionContext.getRequest().getParameter("ids");
		String[] idsArr = {};
		if (ids != null) {
			idsArr = ids.split(",");
		}
		for (String s : idsArr) {
			this.roleService.deleteEntity(s);
		}

		return ACTION2ACTION;
	}

	public String createUI() {
		List<Menu> menuList = (List<Menu>) this.menuService.getAllEntity();
		// 移除父菜单
		for (int i = menuList.size() - 1; i >= 0; i--) {
			if (menuList.get(i).getParentMenu().getId() < 1) {
				menuList.remove(i);
			}
		}
		Collections.sort(menuList);
		ActionContext.getContext().put("menuList", menuList);
		return CREATE_UI;
	}

	public String add() {
		Role role = new Role();
		role.setName(this.getModel().getName());
		role.setType(this.getModel().getType());
		role.setDescription(this.getModel().getDescription());

		if (this.mids != null && this.mids.length > 0) {
			List<Menu> menus = (List<Menu>) this.menuService.getEntrysByIDS(this.mids);

			// 对增加父菜单到Menu集合中去
			List<Integer> asList = new ArrayList<Integer>(Arrays.asList(this.mids));
			for (Menu m : menus) {
				asList.add(m.getParentMenu().getId());
			}
			// 转成set集合去重,再转换成nteger数组;
			HashSet<Integer> hashSet = new HashSet<Integer>(asList);
			this.mids = hashSet.toArray(new Integer[hashSet.size()]);
			menus = (List<Menu>) this.menuService.getEntrysByIDS(mids);
			role.setMenus(new HashSet<Menu>(menus));
		}

		this.roleService.saveEntity(role);
		return ACTION2ACTION;
	}

	public String updateUI() {
		Role role = this.roleService.getEntityById(this.getModel().getId());
		List<Menu> menuList = (List<Menu>) menuService.getAllEntity();
		// 移除父菜单
		for (int i = menuList.size() - 1; i >= 0; i--) {
			if (menuList.get(i).getParentMenu().getId() < 1) {
				menuList.remove(i);
			}
		}

		Set<Menu> menus = role.getMenus();
		this.mids = new Integer[menus.size()];
		int index = 0;
		for (Menu m : menus) {
			this.mids[index] = m.getId();
			index++;
		}

		ActionContext.getContext().getValueStack().push(role);
		ActionContext.getContext().put("menuList", menuList);
		return UPDATE_UI;
	}

	public String update() {
		Role role = roleService.getEntityById(this.getModel().getId());
		role.setName(this.getModel().getName());
		role.setType(this.getModel().getType());
		role.setDescription(this.getModel().getDescription());

		roleService.updateRole(role, mids);

		return ACTION2ACTION;
	}

}
