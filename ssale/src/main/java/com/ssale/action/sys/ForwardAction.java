package com.ssale.action.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.ssale.base.BaseAction;
import com.ssale.entity.Menu;
import com.ssale.service.MenuService;

/**
 * @Description: 页面转向action
 * @author Tree
 * 
 */
public class ForwardAction extends BaseAction<Menu> {

	private static final long serialVersionUID = -622760432077731888L;

	private Integer id;

	private String method;
	private MenuService menuService;


	// 跳转页面
	public String leftforward() {
		return "listAction2";
	}



	// 控制显示左边的一级和二级菜单
	public String left() {
		List<Map<String, List<Menu>>> onelist = new ArrayList<Map<String, List<Menu>>>();
		// string是一级菜单的name,list<menu>存二级菜单
		Map<String, List<Menu>> map = null;
		List<Menu> twolist = null;
		// 取出session中,该登录用户拥有的权限菜单
		@SuppressWarnings("unchecked")
		List<Menu> menulist = (List<Menu>) ServletActionContext.getRequest().getSession()
				.getAttribute("menulist");
		for (Menu m : menulist) {
			if (m.getParentMenu().getId() != null && 0 == m.getParentMenu().getId()) {
				if (twolist == null) {
					twolist = new ArrayList<Menu>();
					continue;
				} else {
					map = new HashMap<String, List<Menu>>();
					// 一级菜单onename为key,其子菜单的twolist集合为value,放入到map中
					String onename = twolist.get(0).getParentMenu().getName();
					map.put(onename, twolist);
					onelist.add(map);
					// 清空twolist
					twolist = new ArrayList<Menu>();
					continue;
				}
			}
			twolist.add(m);
		}
		map = new HashMap<String, List<Menu>>();
		// 一级菜单name为key,其子菜单的list集合为value,放入到onelist中
		if (twolist != null) {
			map.put(menuService.getEntityById(twolist.get(0).getParentMenu().getId()).getName(),
					twolist);
			onelist.add(map);
		}

		ActionContext.getContext().put("onelist", onelist);
		return LISTACTION;
	}


	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String forward() {
		return this.method;
	}

	public String todo() {
		return "todo";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
