<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>

<head>
    <title>拍卖系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<frameset rows="64,*"  frameborder="0" border="1" framespacing="0" name="cen0">
	<frame src="${pageContext.request.contextPath}/forwardAction_forward?method=top" noresize="noresize" frameborder="NO" name="topFrame" scrolling="no" marginwidth="0" marginheight="0" target="main" />
  <frameset cols="190,*"  rows="" id="frame" name="level2Frame">
	<frame src="${pageContext.request.contextPath}/forwardAction_left" name="leftFrame" noresize="noresize" marginwidth="0" marginheight="0" frameborder="0" scrolling="no" target="main" />
	<frame src="${pageContext.request.contextPath}/forwardAction_forward?method=right" name="main" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" target="_self" />
  </frameset>
</frameset>
<noframes>
  <body>
  </body>
</noframes>
</html>

