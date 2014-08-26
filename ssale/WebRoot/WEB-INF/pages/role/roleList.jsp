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
			    <a href="${pageContext.request.contextPath}/roleAction_toList?pageNum=1" class="title_bt2"><img class="title_bt3" src="${pageContext.request.contextPath}/images/frame/icon2_090.png" width="14" height="14" />角色列表</a>
			    <a href="${pageContext.request.contextPath}/roleAction_createUI?pageNum=<s:property value="#page.pageNum" />" class="title_bt2"><img class="title_bt3" src="${pageContext.request.contextPath}/images/user/22.gif" width="14" height="14" />新增角色</a>
			    <!-- <a href="#" class="title_bt2"><img class="title_bt3" src="${pageContext.request.contextPath}/images/user/33.gif" width="14" height="14" />修改用户</a> -->
			    <a href="#" class="title_bt2" id="delete"><img class="title_bt3" src="${pageContext.request.contextPath}/images/user/11.gif" width="14" height="14" />批量删除</a>
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
  <tr height="450">
    <td valign="middle" background="${pageContext.request.contextPath}/images/frame/mail_leftbg.gif">&nbsp;</td>
    <td valign="top" bgcolor="#F7F8F9"> 
      <table width="100%" class="STYLE2" frame="void" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" >
		          <tr>
		            <td width="3%" height="28" background="${pageContext.request.contextPath}/images/frame/news-title-bg28.gif"><div align="center">
		              <input type="checkbox" id="checkAll"/>
		            </div></td>
		            <td width="3%" height="28" background="${pageContext.request.contextPath}/images/frame/news-title-bg28.gif"><div align="center"><span class="STYLE1">序号</span></div></td>
		            <td width="8%" height="28" background="${pageContext.request.contextPath}/images/frame/news-title-bg28.gif"><div align="center"><span class="STYLE1">角色名称</span></div></td>
		            <td width="6%" height="28" background="${pageContext.request.contextPath}/images/frame/news-title-bg28.gif"><div align="center"><span class="STYLE1">角色等级</span></div></td>
		            <td width="50%" height="28" background="${pageContext.request.contextPath}/images/frame/news-title-bg28.gif"><div align="center"><span class="STYLE1">角色说明</span></div></td>
		            <td width="15%" height="28" background="${pageContext.request.contextPath}/images/frame/news-title-bg28.gif"><div align="center"><span class="STYLE1">基本操作</span></div></td>
		          </tr>
		          <s:iterator value="#page.currentRecords" status="s" >
		          <tr bgcolor='<s:property value="#s.odd?'#FFFFFF':'#eaf0f4'"/>'>
		            <td height="20" align="center"><input type="checkbox" value="<s:property value="id" />" /></td>
		            <td height="20" align="center"><span class="STYLE1"><s:property value="#s.count+(#page.pageNum-1)*(#page.pageSize)" /></span></td>
		            <td height="20" align="center"><span class="STYLE1"><s:property value="name" /></span></td>
		            <td height="20" align="center"><span class="STYLE1"><s:property value="rolelv+1" /></span></td>
		            <td height="20" align="center"><span class="STYLE1"><s:property value="description"/></span></td>
		            <s:hidden name="id"></s:hidden>
		            <td height="30" align="center">
		            	<s:a class="title_bt4" href="roleAction_updateUI?id=%{id}&pageNum=%{#page.pageNum}" ><img class="title_bt5" src="${pageContext.request.contextPath}/images/user/edt.gif" width="16" height="16" />
		            		编辑&nbsp; &nbsp;</s:a>
		            	<s:a class="title_bt4" href="roleAction_delete?id=%{id}&pageNum=%{#page.pageNum}" onclick="javascript:return p_del()"><img class="title_bt5" src="${pageContext.request.contextPath}/images/user/del.gif" width="16" height="16" />
		            		删除</s:a>
		            </td>
		          </tr>
		          </s:iterator>
		        </table></td>
		      </tr>
		    </table></td>
		  </tr>
		  <tr>
		        <td class="STYLE4" background="${pageContext.request.contextPath}/images/frame/news-title-bg28.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
		          <tr>
		            <td>&nbsp;&nbsp;共有 <s:property value="#page.totalRecords" />条记录，当前第 <s:property value="#page.pageNum==0?'1':#page.pageNum" />/<s:property value="#page.totalPage==0?'1':#page.totalPage" />  页</td>
		            <td><table border="0" align="right" cellpadding="0" cellspacing="0">
		                <tr>
		                  <td width="40"><s:if test="#page.pageNum==1"><a class="title_bt4" href="#" >首页</a></s:if>
		                  	<s:elseif test="#page.pageNum!=1"><a class="title_bt4" href="${pageContext.request.contextPath}/roleAction_toList?pageNum=1" >首页</a></s:elseif></td>
	               		  <td width="40"><s:if test="#page.pageNum==1"><a class="title_bt4" href="#" >上一页</a></s:if>
	               		  	<s:elseif test="#page.pageNum!=1"><a class="title_bt4" href="${pageContext.request.contextPath}/roleAction_toList?pageNum=<s:property value="#page.pageNum-1" />" >上一页</a></s:elseif></td>
		                  <td width="40"><s:if test="#page.pageNum==#page.totalPage"><a class="title_bt4" href="#" >&nbsp;下一页</a></s:if>
		                  	<s:elseif test="#page.pageNum!=#page.totalPage"><a class="title_bt4" href="${pageContext.request.contextPath}/roleAction_toList?pageNum=<s:property value="#page.pageNum+1" />" >&nbsp;下一页</a></s:elseif></td>
		                  <td width="30"><s:if test="#page.pageNum==#page.totalPage"><a class="title_bt4" href="#" >&nbsp;尾页</a></s:if>
		                  	<s:elseif test="#page.pageNum!=#page.totalPage"><a class="title_bt4" href="${pageContext.request.contextPath}/roleAction_toList?pageNum=<s:property value="#page.totalPage" />" >&nbsp;尾页</a></s:elseif></td>
		                <form action="roleAction_toList" method="get">
		                  <td width="90"><div><span class="title_but_c">&nbsp;&nbsp;转到第
		                    <input name="pageNum" style="height:16px; width:20px; border:1px solid #999999;"/>&nbsp;页 <input name="num" id="num1" type="hidden" value="<s:property value="#page.pageNum" />">
		                  <input type="submit" class="but_c" value="转到" ></span></div></td>
					    </form>
		                </tr>
		            </table></td>
		          </tr>
		        </table></td>
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
<script type="text/javascript">
$(function(){
	//批量删除
	$("#delete").click(function(){
		var flag = 0;
		$("table tr td input[type = checkbox]").each(function(){
			var checkeState = $(this).attr("checked");
			if(checkeState == "checked"){
				++flag;
			}
		});
		if(flag == 0){
			alert("至少选择一条删除");
		}else{
			if(confirm("您真的要删除选择的员工吗？\n\n请确认！")){
				var ids = "";
				$("table tr td input[type = checkbox]").each(function(){
					var checkeState = $(this).attr("checked");
					if(checkeState == "checked"){
						ids = ids +  $(this).val() +",";
					}
				});
				
				ids = ids.substring(0, ids.length -1);
				window.location.href = "roleAction_deleteBatch?num="+$("#num1").val()+"&ids="+ids;
			}
		}
	});
});
</script>
</html>

