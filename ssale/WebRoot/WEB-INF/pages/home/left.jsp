<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<head>
<title>left</title>
<script src="${pageContext.request.contextPath}/js/prototype.lite.js"	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/moo.fx.js"	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/moo.fx.pack.js"	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/menuleft.css" rel="stylesheet" type="text/css" />

</head>

<body>
	<table width="100%" height="280px" border="0" cellpadding="0"
		cellspacing="0" bgcolor="#EEF2FB">
		<tr>
			<td width="182" valign="top"><div id="container">
<s:iterator value="#onelist">
  <s:iterator >
	<h1 class="type">
	  <a href="javascript:void(0)"><s:property value="key"/></a>
	</h1>
	<div class="content">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
		  <td><img src="${pageContext.request.contextPath}/images/frame/menu_topline.gif" width="182" height="5" /></td>
		</tr>
	  </table>
	  <ul class="MM">
		<s:iterator value="value">
		  <li><a href=<s:property value="url"/> target="main"><s:property value="name"/></a></li>
		</s:iterator>
	  </ul>
	</div>
  </s:iterator>
</s:iterator>
<s:if test="#onelist.size ==0"><br/><br/><br/><br/>&nbsp;&nbsp;您没有任何权限!<br/><br/>&nbsp;&nbsp;请联系管理员</s:if>
				</div> <script type="text/javascript">
					var contents = document.getElementsByClassName('content');
					var toggles = document.getElementsByClassName('type');

					var myAccordion = new fx.Accordion(toggles, contents, {
						opacity : true,
						duration : 400
					});
					myAccordion.showThisHideOpen(contents[0]);
				</script></td>
		</tr>
	</table>
</body>
</html>

