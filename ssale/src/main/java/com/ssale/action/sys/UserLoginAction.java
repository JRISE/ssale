package com.ssale.action.sys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.ssale.base.BaseAction;
import com.ssale.entity.Menu;
import com.ssale.entity.Role;
import com.ssale.entity.User;
import com.ssale.service.UserService;
import com.ssale.util.TreeJudgement;

/**
 * 
 * @Description: 用户登录Action
 * @author Tree
 * 
 */
public class UserLoginAction extends BaseAction<User> {

	private static final long serialVersionUID = 6807676881456949368L;

	private String loginAccount;
	private String loginPassword;
	private UserService userService;

	public String getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String login() {
		// 验证用户密码是否正确
		if (!TreeJudgement.isNotNull(loginAccount) || !TreeJudgement.isNotNull(loginPassword)) {
			this.addActionError("请填写用户名和密码");
			return LOGIN;
		}
		User user = this.userService.getUserByAccount(loginAccount, loginPassword);
		if (user == null) {
			this.addActionError("用户名或者密码错误");
			return LOGIN;
			// 验证账户是否停用
		} else if ("1".equals(user.getState())) {
			this.addActionError("账户已停用");
			return LOGIN;
		} else {
			// 账户验证通过,修改最后登录时间,并把user存到session中
			user.setLastLogin(new Date());
			userService.updateEntity(user);
			Set<Role> roles = user.getRoles();
			Set<Menu> menus = new HashSet<Menu>();
			for (Role r : roles) {
				Set<Menu> ms = r.getMenus();
				menus.addAll(ms);
			}
			// 把该用户拥有的权限菜单,取出来,放入到session中
			List<Menu> menulist = new ArrayList<Menu>(menus);
			// 排序
			Collections.sort(menulist);
			ServletActionContext.getRequest().getSession().setAttribute("user", user);
			ServletActionContext.getRequest().getSession().setAttribute("menulist", menulist);
			// ActionContext.getContext().getValueStack().push(userGroup);
			ActionContext.getContext().getValueStack().push(user);
			return INDEX;
		}
	}

	public String logout() {
		// 用户注销，清空session
		ServletActionContext.getRequest().getSession().invalidate();
		return LOGIN;

	}

}
