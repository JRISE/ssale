package com.ssale.Interceptor;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.ssale.entity.Menu;

/**
 * @Description: 全局用户登录验证
 * @Author Tree
 */
public class LoginInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = 6756595159043498378L;

	@SuppressWarnings("unchecked")
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		String result = "toerror";
		// 获取session中,是否有用户或者客户,如果没有,则转向登录页面;如果有,则放行
		HttpSession session = ServletActionContext.getRequest().getSession();
		List<Menu> menulist = (List<Menu>) session.getAttribute("menulist");
		if (menulist != null) {
			for (Menu m : menulist) {
				if (invocation.getProxy().getActionName().equals(m.getUrl())) {
					result = invocation.invoke();
				}
			}
		}
		return result;

	}

}
