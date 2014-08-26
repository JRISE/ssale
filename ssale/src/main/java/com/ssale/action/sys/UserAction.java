package com.ssale.action.sys;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.ActionContext;
import com.ssale.base.BaseAction;
import com.ssale.dao.RoleDao;
import com.ssale.entity.Role;
import com.ssale.entity.User;
import com.ssale.service.RoleService;
import com.ssale.service.UserService;
import com.ssale.util.Page;
import com.ssale.util.TreeUtil;

public class UserAction extends BaseAction<User> {

	private static final long serialVersionUID = -7244869939806211706L;

	private String method;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	private Long[] rids;
	private String bdate;
	private String pageNum;
	private UserService userService;
	private RoleService roleService;
	private RoleDao roleDao;

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	public Long[] getRids() {
		return rids;
	}

	public void setRids(Long[] rids) {
		this.rids = rids;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public String toList() {
		int count = userService.getAllCount();
		// num是当前页码
		String num = ServletActionContext.getRequest().getParameter("num");
		// 每页显示的个数
		int pageSize = 30;
		int pNum = TreeUtil.pageNum(num, pageNum, count, pageSize);

		List<User> list = (List<User>) userService
				.getEntityPerPage((pNum - 1) * pageSize, pageSize);

		Page<User> page = new Page<User>(pNum, list, count, pageSize);
		ActionContext.getContext().put("page", page);

		return LISTACTION;
	}

	public String delete() {
		this.userService.deleteEntity(this.getModel().getId());
		return ACTION2ACTION;
	}

	public String deleteBatch() {
		String ids = ServletActionContext.getRequest().getParameter("ids");
		String[] idsArr = {};
		if (ids != null) {
			idsArr = ids.split(",");
		}
		for (String s : idsArr) {
			this.userService.deleteEntity(s);
		}

		return ACTION2ACTION;
	}

	public String createUI() {
		Collection<Role> roleList = this.roleService.getAllEntity();
		Map<String,String> typeMap = new HashMap<String, String>();
		typeMap.put("1", "系统管理员");
		typeMap.put("2", "后台管理员");
		typeMap.put("3", "普通用户");
		typeMap.put("4", "VIP用户");
		
		ActionContext.getContext().put("typeMap", typeMap);
		ActionContext.getContext().put("roleList", roleList);
		return CREATE_UI;
	}

	public String add() {
		// 判断数据库中是否有相同的用户名
		if (userService.getUserByAccount(this.getModel().getAccount(), this.getModel().getName()) != null) {
			return NONE;
		}
		User user = new User();
		BeanUtils.copyProperties(this.getModel(), user);

		if (bdate != null && !"".equals(bdate)) {
			try {
				Date birthday = (new SimpleDateFormat("yyyy-MM-dd")).parse(bdate);
				user.setBirthday(birthday);
			} catch (ParseException e) {
				e.printStackTrace();
				return TOERROR;
			}
		}

		if (this.rids != null && this.rids.length > 0) {
			List<Role> roles = (List<Role>) this.roleService.getEntrysByIDS(this.rids);
			user.setRoles(new HashSet<Role>(roles));
		}
		user.setCreateDate(new Date());
		this.userService.saveEntity(user);
		return ACTION2ACTION;
	}

	public String updateUI() {
		User user = this.userService.getEntityById(this.getModel().getId());
		if (user.getBirthday() != null){
			this.bdate = (new SimpleDateFormat("yyyy-MM-dd")).format(user.getBirthday());
		}
		Set<Role> roles = user.getRoles();
		this.rids = new Long[roles.size()];
		int index = 0;
		for (Role role : roles) {
			this.rids[index] = role.getId();
			index++;
		}

		Collection<Role> roleList = this.roleService.getAllEntity();
		Map<String,String> typeMap = new HashMap<String, String>();
		typeMap.put("1", "系统管理员");
		typeMap.put("2", "后台管理员");
		typeMap.put("3", "普通用户");
		typeMap.put("4", "VIP用户");
		
		ActionContext.getContext().put("typeMap", typeMap);
		ActionContext.getContext().getValueStack().push(user);
		ActionContext.getContext().put("roleList", roleList);
		return UPDATE_UI;
	}

	public String update() {
		if (userService.getUserByAccount(this.getModel().getAccount(), this.getModel().getName()) != null) {
			return NONE;
		}
		User user = userService.getEntityById(this.getModel().getId());
		user.setName(this.getModel().getName());
		user.setAccount(this.getModel().getAccount());
		user.setPassword(this.getModel().getPassword());
		user.setType(this.getModel().getType());

		try {
			Date birthday = (new SimpleDateFormat("yyyy-MM-dd")).parse(bdate);
			user.setBirthday(birthday);
		} catch (ParseException e) {
			e.printStackTrace();
			return TOERROR;
		}

		if (rids != null && !"".equals(rids)) {
			List<Role> roles = (List<Role>) this.roleDao.getEntrysByIDS(this.rids);
			user.setRoles(new HashSet<Role>(roles));
		}
		this.userService.updateEntity(user);
		return ACTION2ACTION;
	}

	public String method() {
		return this.method;
	}

}
