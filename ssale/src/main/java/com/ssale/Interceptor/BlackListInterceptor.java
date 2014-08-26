package com.ssale.Interceptor;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.ssale.entity.User;

/**
 * @Description: 黑名单拦截.查看用户或者客户中blacklist,0为非黑名单,1为黑名单.
 * @author Tree
 * 
 */
public class BlackListInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = 1423430946469101643L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		String result = "torefuse";

		// 获取session中的用户
		HttpSession session = ServletActionContext.getRequest().getSession();
		User user = (User) session.getAttribute("user");

		// 如果不属于黑名单,则放行
		if (user != null && "0".equals(user.getBlackList())) {
			result = invocation.invoke();
			// }else{
			// 获取用户的所有角色,是否属于管理员.属于管理员仍然放行
			// Set<Role> roles = user.getRoles();
			// for(Role r:roles){
			// if("管理员".equals(r.getName())){
			// result = invocation.invoke();
			// }
			// }
		}
		ServletActionContext.getRequest().getSession().invalidate();
		return result;
	}

}
