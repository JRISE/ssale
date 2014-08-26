package com.ssale.service;

import com.ssale.base.BaseService;
import com.ssale.entity.Role;

public interface RoleService extends BaseService<Role> {
	public void updateRole(Role role, Integer[] mids);

}
