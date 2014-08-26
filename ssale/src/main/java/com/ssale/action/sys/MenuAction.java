package com.ssale.action.sys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.ssale.base.BaseAction;
import com.ssale.entity.Menu;
import com.ssale.service.MenuService;
import com.ssale.util.Page;
import com.ssale.util.TreeUtil;

public class MenuAction extends BaseAction<Menu> {

	private static final long serialVersionUID = 3000976600320734597L;
	private Integer pid;
	private String pageNum;
	private MenuService menuService;

	public Integer getMids() {
		return pid;
	}

	public void setMids(Integer pid) {
		this.pid = pid;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public String toList() {
		int count = menuService.getAllCount();
		// num是当前页码
		String num = ServletActionContext.getRequest().getParameter("num");
		// 每页显示的个数
		int pageSize = 15;
		int pNum = TreeUtil.pageNum(num, pageNum, count, pageSize);

		List<Menu> list = (List<Menu>) menuService
				.getEntityPerPage((pNum - 1) * pageSize, pageSize);

		Page<Menu> page = new Page<Menu>(pNum, list, count, pageSize);
		ActionContext.getContext().put("page", page);

		return LISTACTION;
	}

	public String delete() {
		this.menuService.deleteEntity(this.getModel().getId());
		return ACTION2ACTION;
	}

	public String deleteBatch() {
		String ids = ServletActionContext.getRequest().getParameter("ids");
		String[] idsArr = {};
		Integer id;
		if (ids != null) {
			idsArr = ids.split(",");
		}
		for (String s : idsArr) {
			id = Integer.parseInt(s);
			this.menuService.deleteEntity(id);
		}

		return ACTION2ACTION;
	}

	public String createUI() {
		List<Menu> menuList = (List<Menu>) this.menuService.getAllEntity();
		Collections.sort(menuList);
		
		List<Menu> list = new ArrayList<Menu>();
		list.add(menuService.getEntityById(0));
		for (Menu m : menuList) {
			if (m.getParentMenu().getId() == 0) {
				list.add(m);
			}
		}
		
		ActionContext.getContext().put("list", list);
		return CREATE_UI;
	}

	public String add() {
		Menu menu = new Menu();
		menu.setName(this.getModel().getName());
		menu.setDescription(this.getModel().getDescription());
		menu.setUrl(this.getModel().getUrl());
		menu.setParentMenu(menuService.getEntityById(pid));

		this.menuService.saveEntity(menu);
		return ACTION2ACTION;
	}

	public String updateUI() {
		List<Menu> menuList = (List<Menu>) this.menuService.getAllEntity();
		Collections.sort(menuList);
		
		Map<Integer, String> pmenuMap = new HashMap<Integer, String>();
		for (Menu m : menuList) {
			if (m.getParentMenu().getId() == 0) {
				pmenuMap.put(m.getId(), m.getName());
			}
		}
		
		ActionContext.getContext().put("pmenuMap", pmenuMap);
		return UPDATE_UI;
	}

	public String update() {
		Menu menu = new Menu();
		menu.setName(this.getModel().getName());
		menu.setDescription(this.getModel().getDescription());
		menu.setUrl(this.getModel().getUrl());
		menu.setParentMenu(menuService.getEntityById(pid));

		this.menuService.updateEntity(menu);

		return ACTION2ACTION;
	}

}
