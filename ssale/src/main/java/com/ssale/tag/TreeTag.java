package com.ssale.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class TreeTag extends TagSupport {

	private static final long serialVersionUID = 476358561545148070L;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int doStartTag() throws JspException {
		String ip = pageContext.getRequest().getRemoteAddr();
		try {
			pageContext.getOut().write(name + ":");
			pageContext.getOut().write(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}
}
