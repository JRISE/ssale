<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>拍卖平台</title>
<!-- 
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="cache-control" content="no-store"/>
<meta http-equiv="expires" content="0"/>
 -->
 
<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css"/>
	
<script language=javascript>
	function formSubmit (url,sTarget){
    document.forms[0].target = sTarget;
    document.forms[0].action = url;
    document.forms[0].submit();
    return true;
	}
	
	function butOnClick() { 
	if (event.keyCode == 13) { 
	var button = document.getElementById("sbutton"); //bsubmit 为botton按钮的id 
	button.click(); 
	return false; 
} 
} 
</script>
	
</head>

<body>
<s:form >
<div id="warpbox">
	<div class="main">
		 <div class="zck">
		  <div class="zc">
				<div class="zc_line">用户名：<s:textfield name="loginAccount" cssStyle="background:#ffffff;"></s:textfield></div>
			    <div class="zc_line">密　码：<s:password name="loginPassword" cssStyle="background:#ffffff;" onkeydown="javascript:butOnClick();"></s:password></div>
		  </div>
			<div class="dl">
				<input  class="dl_img" value="" id="sbutton" type="button" onclick="formSubmit('${pageContext.request.contextPath}/userLoginAction_login','_self');"
				  onmouseover="this.style.background='url(${pageContext.request.contextPath}/images/login/dl_h.jpg) no-repeat'" 
				  onmouseout="this.style.background='url(${pageContext.request.contextPath}/images/login/dl_a.jpg) no-repeat'"
				/>
			</div>
		</div>
		<div class="bqxx" style="text-align:right;margin-top:0px;">
		<a href="#">系统帮助</a> | <a href="#" >加入收藏</a>
	    </div>
		  <s:actionerror cssStyle="color:red;"/>

		</div>
</div>
</s:form>
</body>
</html>


