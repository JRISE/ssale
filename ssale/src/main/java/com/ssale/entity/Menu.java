package com.ssale.entity;

import java.io.Serializable;
import java.util.Set;

/**
 * @Description 菜单实体类,实现Comparable接口,自动进行排序
 * @Author Tree
 */
public class Menu implements Serializable, Comparable<Menu> {

	private static final long serialVersionUID = -6328421210333038677L;

	// id不使用自动生成,手动给菜单的id赋值,以便于进行排序
	private Integer id;
	private String name;
	private String description;
	private String url;
	private Set<Role> roles;
	private Menu parentMenu;

	// 重写compareTo方法,使用arraylist进行排序的时候,按照id进行排序
	@Override
	public int compareTo(Menu menu) {
		if (this.id != menu.id) {
			return this.id - menu.id;
		}
		return 0;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Menu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}

}
