package com.ssale.service;

import java.io.Serializable;

import com.ssale.base.BaseService;
import com.ssale.entity.User;

public interface UserService extends BaseService<User> {
	
	public User getUserByAccount(Serializable account, Serializable password);
	
	public User getUserByAccount(Serializable account);
}
