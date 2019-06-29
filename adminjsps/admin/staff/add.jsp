<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>添加员工信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="<c:url value='/adminjsps/admin/css/book/add.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/jquery/jquery.datepick.css'/>">
<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jquery/jquery.datepick.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jquery/jquery.datepick-zh-CN.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value='/themes/default/easyui.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/themes/icon.css'/>" >
<!-- 引入文件顺序不能调乱 -->
<script type="text/javascript" src="<c:url value='/js/jquery.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.easyui.min.js'/>"></script>
<script type="text/javascript">
$(function (){
	 $("#btn").addClass("btn1");
	 $('#btn').add
	 /*
	$("#btn").hover(
		function() {
			$("#btn").removeClass("btn1");
			$("#btn").addClass("btn2");
		},
		function() {
			$("#btn").removeClass("btn2");
			$("#btn").addClass("btn1");
		}
	); */
	$.post("${pageContext.request.contextPath }/action/TypeAction?",{"dicid":"dicname"},function(data){
		$(data).each(function(i,n){
			$('#mtype').append("<option value='"+n.code+"'>"+n.value+"</option>");
		});
	},"json");
	//alert('成功添加管理员！');
});


</script>
  </head>
  
  <body>
  <div>
   <p style="font-weight: 900; color: red;">${msg }</p>
   <!-- <form action="javascript:function()" enctype="multipart/form-data" method="post" id="form"> -->
  <form action="<c:url value='/saveManager.action'/>" method="post" id="form" onsubmit="">
		<table>
			<tr>
				<td colspan="1">
					 员工用户名：<input id="musername" type="text" name="musername"  
	    					class="easyui-validatebox" data-options="required:true"/>
				</td>
			</tr>
			<tr>
				<td colspan="1"> 
					密&nbsp;&nbsp;码：<input id="mpassword" type="password" name="mpassword"
					class="easyui-validatebox" data-options="required:true" />
				</td>		
			</tr>
			<tr>
				<td colspan="1">
					 真实姓名：<input id="mname" type="text" name="mname"
					 class="easyui-validatebox" data-options="required:true" /> 
				</td>
			</tr>
			<tr>
				<td>
					用户类型： <select name="mtype" id="mtype">
								<option value="0">管理员</option>
								<option value="1">客服员</option>
								<option value="2">采购员</option>
							</select>
				</td>
			</tr>
			<tr>
			    <td colspan="1">
					物品管理权限：<input id="goodpower" type="text" name="goodpower"
					 class="easyui-validatebox" data-options="required:true" /> 
				</td>
			</tr>
			<tr>
			    <td colspan="1">
					订单管理权限：<input id="orderpower" type="text" name="orderpower"
					 class="easyui-validatebox" data-options="required:true" /> 
				</td>
			</tr>
			<tr>
			    <td colspan="1">
					顾客管理权限：<input id="customerpower" type="text" name="customerpower"
					 class="easyui-validatebox" data-options="required:true" /> 
				</td>
			</tr>
			<tr>
			    <td colspan="1">
					工作人员管理权限：<input id="workerpower" type="text" name="workerpower"
					 class="easyui-validatebox" data-options="required:true" /> 
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" id="btn"  value="添加新的管理员" />
					<input type="reset" id="reset"  value="重置" />
				</td>
			</tr>
		</table>
	
     </form>
  </div>
  </body>
</html>
