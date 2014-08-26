<%@ page language="java" pageEncoding="UTF-8"%>

<html>
<head>
    <title>转到错误页面</title>
<script type="text/javascript">
 window.onload=function() 
 { 
top.location.href="${pageContext.request.contextPath}/login.jsp";
};
</script> 
</head>
 
<body>


</body>
</html>

