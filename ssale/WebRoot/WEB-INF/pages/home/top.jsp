<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<html>
<head>
    <title>top</title>
<script language=JavaScript>
function logout(){
	if (confirm("您确定要退出控制面板吗？")){
	top.location = "${pageContext.request.contextPath}/userLoginAction_logout";
	top.location.history = "null";
	}
	return false;
}
</script>
<link href="${pageContext.request.contextPath}/css/skin.css" rel="stylesheet" type="text/css">
</head>
<body leftmargin="0" topmargin="0">
<table width="100%" height="64" border="0" cellpadding="0" cellspacing="0" class="admin_topbg" >
  <tr>
    <td width="61%" height="64"><img src="${pageContext.request.contextPath}/images/frame/logo.gif" width="262" height="64"></td>
    <td width="39%" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="74%" height="38" class="admin_txt"> ${user.name },您好!</td>
        <td width="22%"><a href="#" target="_self" onClick="logout();"><img src="${pageContext.request.contextPath}/images/frame/out.gif" alt="安全退出" width="46" height="20" border="0"></a></td>
        <td width="4%">&nbsp;</td>
      </tr>
      <tr>
        <td height="19" colspan="3">&nbsp;</td>
        </tr>
    </table></td>
  </tr>
</table>
<s:debug></s:debug>
</body>
</html>

