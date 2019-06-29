<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>管理员登录页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript">
		function checkForm() {
			if(!$("#adminname").val()) {
				alert("管理员名称不能为空！");
				return false;
			}
			if(!$("#adminpwd").val()) {
				alert("管理员密码不能为空！");
				return false;
			}
			return true;
		}
	</script>
  </head>
<body>
	<div align="center">
		<h1>管理员登录页面</h1>
		<hr />
		<h1><font style="font-weight: 900; color: red"><s:actionerror/></font></h1>
		<form action="<c:url value='/loginManager.action'/>"
			method="post" onsubmit="return checkForm()">
			<table>
				<tr>
					<td>管理员账户：</td>
					<td><input type="text" name="musername" value="" id="adminname" /></td>
				</tr>
				<tr>
					<td>密 码：</td>
					<td> <input	type="password" name="mpassword" id="adminpwd" /></td>
				</tr>
				<tr>
					<td>类型：</td>
					<td><input type="radio" name="mtype" value="0" checked />管理员 <input
						type="radio" name="mtype" value="1" />客服 <input type="radio"
						name="mtype" value="2" />采购员
					</td>
				</tr>
			</table>	
			 <input	type="submit" value="进入后台" />
		</form>
	</div>
</body>
</html>
