//js的整合




//Checkbox的全选&全部取消js
$(function(){
	//转到增加
	$("#add").click(function(){
		window.location.href = "preSave.do";
	});
	
	//批量更新
	$("#update").click(function(){
		var flag = 0;
		$("table tr td input[type = checkbox]").each(function(){
			var checkeState = $(this).attr("checked");
			if(checkeState == "checked"){
				++flag;
			}
		});
		if(flag != 1){
			alert("请选择一条");
		}else{
			var personId = null;
			$("table tr td input[type = checkbox]").each(function(){
				var checkeState = $(this).attr("checked");
				if(checkeState == "checked"){
					personId = $(this).val();
				}
			});
			window.location.href = "preUpdate.do?personId="+personId;
		}
	});
	
	//全部选中
	$("#checkAll").change(function(){
		if($(this).attr("checked") == "checked"){
			$("table tr td input[type = checkbox]").each(function(){
				$(this).attr("checked","checked");
			});
		}else{
			$("table tr td input[type = checkbox]").each(function(){
				$(this).removeAttr("checked");
			});
		}
	});
	
});

//删除确认
function p_del() {
	if (confirm("您真的确定要删除吗？\n\n请确认！")==true){
	return true;
	}else{
	return false;
	}
}

//下线确认
function p_down() {
	if (confirm("您真的确定要讲该产品信息下线吗？\n\n请确认！")==true){
		return true;
	}else{
		return false;
	}
}
	
//验证确认
function p_check() {
	if (confirm("您真的要验证吗？\n\n请确认！")==true){
	return true;
	}else{
	return false;
	}
}	

//加入确认
function p_add() {
	if (confirm("您真的要把此消息加入吗？\n\n请确认！")==true){
	return true;
	}else{
	return false;
	}
}

//变声失效确认
function p_cast() {
	if (confirm("您真的要变更此生效状态吗？\n\n请确认！")==true){
	return true;
	}else{
	return false;
	}
}

//发布确认
function p_send() {
	if (confirm("您真的要发布吗？\n\n请确认！")==true){
	return true;
	}else{
	return false;
	}
}