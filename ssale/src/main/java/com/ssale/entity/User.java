package com.ssale.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @Description 员工实体类
 * @Author Tree
 */
public class User implements Serializable {

	private static final long serialVersionUID = 2459413008284964998L;

	private Long id;
	private String name;
	private String account;
	private String password;
	private String type;
	private Date createDate;
	private Date lastLogin;
	private Date birthday;
	private String state = "0"; // 0启用,1停用
	private String blackList = "0"; // 0不加入黑名单,1加入黑名单.初始化为0
	private Set<Role> roles;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getBlackList() {
		return blackList;
	}

	public void setBlackList(String blackList) {
		this.blackList = blackList;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
