<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>pwd.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/css.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/user/pwd.css'/>">
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script src="<c:url value='/js/common.js'/>"></script>
  </head>
  
  <body>
    <div class="div0">
    	<span></span>
    </div>

	<div class="div1">
		<form action="${pageContext.request.contextPath }/customer/editPwd.action" method="post" target="_top">
		<table>
			<tr>
				<td><label class="error">${custError.cpasswordError }</label></td>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td align="right">原密码:</td>
				<td>
					<!-- 借用Customer对象的cstate属性，但不是修改数据库中的数据 -->
					<s:password class="input" name="cstate"></s:password>
				</td>
				<td>
					<label id="loginpassError" class="error"></label>
				</td>
			</tr>
			<tr>
				<td align="right">新密码:</td>
				<td>
					<s:password class="input" name="cpassword"></s:password>
				</td>
				<td><label id="newpassError" class="error"></label></td>
			</tr>
			<tr>
				<td align="right">确认密码:</td>
				<td>
					<!-- 借用Customer对象的cphone属性，但不是修改数据库中的数据 -->
					<s:password class="input" name="cphone"></s:password>
				</td>
				<td><label id="reloginpassError" class="error"></label></td>
			</tr>
			<tr>
				<td align="right"></td>
				<td><input id="submit" type="submit" value="修改密码"/></td>
			</tr>
		</table>
		</form>
	</div>
  </body>
</html>
