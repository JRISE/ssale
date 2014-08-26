<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common.jsp/common.jsp"%>
</head>
<body bgcolor="#EEF2FB">
<table width="100%"  border="0" cellpadding="0" cellspacing="0" >
	<!-- 标题部分 -->
  <tr>
    <td width="17" valign="top" background="${pageContext.request.contextPath}/images/frame/mail_leftbg.gif">
      <img src="${pageContext.request.contextPath}/images/frame/left-top-right.gif" width="17" height="29" />
    </td>
    
	<td valign="top" background="${pageContext.request.contextPath}/images/frame/content-bg.gif">
		<table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
		  <tr>
			<td height="31">
			  <div class="titlebt">
			    <a href="${pageContext.request.contextPath}/userAction_toList?pageNum=1" class="title_bt2"><img class="title_bt3" src="${pageContext.request.contextPath}/images/frame/icon2_090.png" width="14" height="14" />员工列表</a>
			  </div>
			</td>
		  </tr>
		</table>
	</td>
	
    <td width="16" valign="top" background="${pageContext.request.contextPath}/images/frame/mail_rightbg.gif">
      <img src="${pageContext.request.contextPath}/images/frame/nav-right-bg.gif" width="16" height="29" />
    </td>
  </tr>
  
  <!-- 正文部分 -->
  <tr height="550">
    <td valign="middle" background="${pageContext.request.contextPath}/images/frame/mail_leftbg.gif">&nbsp;</td>
    <td valign="top" bgcolor="#F7F8F9"> 
      <table width="70%" class="STYLE5" frame="box" align="center" cellpadding="0" cellspacing="0">
        <tr>
		    <td height="28" background="${pageContext.request.contextPath}/images/frame/news-title-bg28.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
		          <tr>
		            <td width="46%" valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">
		              <tr>
		              	<td width="3%"></td>
		                <td width="5%"><div align="center"><img src="${pageContext.request.contextPath}/images/user/tb.gif" width="16" height="16" /></div></td>
		                <td width="92%" class="STYLE1"><span class="STYLE3">新增员工</span></td>
		              </tr>
		            </table></td>
		            <td width="54%"><table border="0" align="right" cellpadding="0" cellspacing="0">
		            </table></td>
		          </tr>
		        </table></td>
		      </tr>
		    </table></td>
		  </tr>
		  <tr>
		    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td>
		        
	<s:form action="userAction_update" id="signupForm" enctype="multipart/form-data">
	  <s:hidden name="pageNum"/>
        <!-- 表单内容显示 -->
        <div>
            <div class="bbb">
				<table cellpadding="0" cellspacing="0" class="mainForm">
			<tr><td height="12"></td></tr>
			<tr class="InputStyle"><td>姓名：</td>
				<td><s:textfield cssClass="InputStyle1" name="name" /></td>
			</tr>
			<tr><td height="9"></td></tr>
		    <tr class="InputStyle"><td>账号：</td>
				<td><s:textfield cssClass="InputStyle1" name="account" autocomplete="off"/><s:actionerror/></td>
			</tr>
			<tr><td height="9"></td></tr>
		    <tr class="InputStyle"><td>密码：</td>
		    	<td><s:textfield cssClass="InputStyle1" name="password"/></td>
			</tr>
         	<tr><td height="9"></td></tr>
		    <tr class="InputStyle"><td>类别：</td>
            	<td><s:select list="#typeMap" cssClass="SelectStyle1" listKey="key" listValue="value" name="type"/></td>
            </tr>
			<tr><td height="9"></td></tr>
		    <tr class="InputStyle"><td>生日：</td>
				<td><s:textfield cssClass="birthday" id="d121" name="bdate" readonly="true" onfocus="WdatePicker({isShowWeek:true})"/></td>
			</tr>
            <tr><td height="9"></td></tr>
		    <tr class="InputStyle"><td>角色：</td>
            	<td ><s:select list="#roleList" cssClass="SelectStyle1" listKey="id" listValue="name" multiple="true" size="8" name="rids"/><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;按住Ctrl键可以对角色进行复选</td>
            </tr> 
            <tr><td height="9"></td></tr>
			<tr class="InputStyle"><td>是否启用：</td>
            	<td><s:radio name="state" list="#{'0':'启用','1':'停用'}" value="0"/></td>
            </tr>
            <s:hidden name="id"></s:hidden>
                </table>
            </div>
        </div>
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <s:submit cssClass="but_b" name="update" method="update" value="保存"></s:submit>
            <s:submit cssClass="but_b" name="toList" method="toList" value="返回" onclick="javascript:history.go(-1);" ></s:submit>
<!--             <input type="image" class="title_bt3" src="${pageContext.request.contextPath}/images/user/save.png"/> -->
<!--             <a href="javascript:history.go(-1);"><img class="title_bt3" src="${pageContext.request.contextPath}/images/user/goBack.png"/></a> -->
        </div>
    </s:form >
		        
				</td></tr></table></td></tr>
				<tr>
				   <td height="28" background="${pageContext.request.contextPath}/images/frame/news-title-bg28.gif"></td>
				</tr>
      </table>
    </td>
    <td background="${pageContext.request.contextPath}/images/frame/mail_rightbg.gif">&nbsp;</td>
  </tr>
  
  <!-- 底部背景图片 -->
  <tr>
    <td valign="bottom" background="${pageContext.request.contextPath}/images/frame/mail_leftbg.gif"><img src="${pageContext.request.contextPath}/images/frame/buttom_left2.gif" width="17" height="17" /></td>
    <td background="${pageContext.request.contextPath}/images/frame/buttom_bgs.gif"><img src="${pageContext.request.contextPath}/images/frame/buttom_bgs.gif" width="17" height="17"></td>
    <td valign="bottom" background="${pageContext.request.contextPath}/images/frame/mail_rightbg.gif"><img src="${pageContext.request.contextPath}/images/frame/buttom_right2.gif" width="16" height="17" /></td>
  </tr>
</table>
</body>
<script>
//表单验证
$().ready(function(){
	/*设置默认属性*/
	$.validator.setDefaults({
		submitHandler:function(form){
			form.submit();
		}
	});

	//数字字母中文验证
	jQuery.validator.addMethod("StringCheck", function(value, element) {
	var chrnum = /^([\w\u4e00-\u9fa5]+)$/;
	return this.optional(element) || (chrnum.test(value));
	}, "只能输入中文、数字和字母(A-Z, a-z)");

	//数字字母验证
	jQuery.validator.addMethod("chrnum", function(value, element) {
	var chrnum = /^([\w]+)$/;
	return this.optional(element) || (chrnum.test(value));
	}, "只能输入数字和字母(A-Z, a-z)");

	//中文字两个字节
	jQuery.validator.addMethod("byteRangeLength", function(value, element, param) {
		var length = value.length;
			for (var i = 0; i < value.length; i++) {
			if (value.charCodeAt(i) > 127) {
				length++;
			}
		}
		return this.optional(element) || (length >= param[0] && length <= param[1]);
	},$.validator.format("请确保输入的长度为{0}-{1}(一个中文长度为2)"));

	//验证配置
	$("#signupForm").validate({
		rules: {
			name: {
				required: true,
				StringCheck: true,
				byteRangeLength: [3,12]
			},
			account:{
				required: true,
				chrnum: true,
				byteRangeLength: [3,18]
			},
			password: {
				required: true,
				chrnum: true,
				byteRangeLength: [3,18]
			},
			ugid: {
				required: true
			},
			rids: {
				required: true
			}
		},
		//验证信息配置
		messages: {
			name: {
				required:"请输入姓名"
			},
			account: {
				required:"请输入账号"
			},
			password: {
				required:"请输入密码"
			},
			ugid: {
				required:"请选择组别"
			},
			rids: {
				required:"请选择角色"
			}
		}
	});

});
</script>
</html>

