package com.ssale.tag;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class CheckboxTag extends BodyTagSupport {

	private static final long serialVersionUID = 8503265813404272157L;
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	// ----标签开始时调用此方法-------
	@Override
	public int doStartTag() throws JspException {
		return super.doStartTag();
	}

	// ----标签体执行完后调用此方法----
	@Override
	public int doAfterBody() {
		String str = this.getBodyContent().getString();
		ServletRequest request = pageContext.getRequest();
		@SuppressWarnings("rawtypes")
		Set set = (Set) request.getAttribute("set");
		if (set != null && set.size() > 0) {
			for (Object o : set) {
				try {
					String oid = o.getClass().getMethod("getId").invoke(o).toString();
					if (str.equals(oid)) {
						// 清除标签主体内容
						this.getBodyContent().clear();
						str = "checked=\"\"";
						pageContext.getOut().print(str);
						return SKIP_BODY;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
		return SKIP_BODY;
	}

	// ----标签结束时调用此方法-------
	@Override
	public int doEndTag() {
		try {
			// ----输出标签体的内容----
			bodyContent.writeOut(bodyContent.getEnclosingWriter());
		} catch (IOException e) {
			System.out.println(e);
		}
		return EVAL_PAGE;
	}

}
