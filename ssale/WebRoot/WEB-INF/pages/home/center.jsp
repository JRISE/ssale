<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
<head>
    <title>center</title>
<script language="JavaScript">
function shleft(){
	if (parent.level2Frame.cols=="0,8,*"){
		parent.level2Frame.cols="191,10,*";
		document.getElementById("shleft").src="${pageContext.request.contextPath}/images/frame/center0.gif";
	}
	else{
		parent.level2Frame.cols="0,8,*";
		document.getElementById("shleft").src="${pageContext.request.contextPath}/images/frame/center1.gif";
	}
}

</script>
</head>
 
<body bgcolor="#f8f9fb">

<div 
	style="width:6px;height:1000px;	border-style: solid;
	border-color: #b5d6e6;
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 0px;
	border-left-width: 1px;
	 " onclick="shleft()">
<input style="position:absolute;top:40%;z-index:1" type="image" src="${pageContext.request.contextPath}/images/frame/center0.gif" name="shleft" id="shleft"> 
</div>
</body>
</html>

