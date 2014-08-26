package com.ssale.entity;

import java.io.Serializable;
import java.util.Set;

/**
 * @Description 角色实体类
 * @Author Tree
 */
public class Role implements Serializable {

	private static final long serialVersionUID = -807722757616422483L;

	private Long id;
	private String name;
	private Integer type;
	private String description;
	private Set<User> users;
	private Set<Menu> menus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}

}
