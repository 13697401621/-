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
	<link rel="stylesheet" type="text/css" href="<c:url value='/themes/default/easyui.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/themes/icon.css'/>" >
	<!-- 引入文件顺序不能调乱 -->
	<script type="text/javascript" src="<c:url value='/js/jquery.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/jquery.easyui.min.js'/>"></script>
  </head>
  
  <body>
   <!--  <div class="div0">
    	<span></span>
    </div> -->
<s:debug></s:debug>
	<div class="div1">
		<form action="${pageContext.request.contextPath }/customer/edit.action" target="_top">
			<table>
				<tr>
					<td><label class="error">${msg }</label></td>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td align="right">用户名:</td>
					<td>
						&nbsp;&nbsp;&nbsp;&nbsp;${sessionScope.customer.cusername }
						<%-- <s:hidden name="cusername"></s:hidden> --%>
					</td>
				</tr>
				<tr>
					<td align="right">真实姓名:</td>
					<td>
						&nbsp;&nbsp;&nbsp;&nbsp;${sessionScope.customer.cname }
						<%-- <s:hidden name="cname"></s:hidden> --%>
					</td>
				</tr>
				
				<tr>
					<td align="right">性别:</td>
					<td>
						&nbsp;&nbsp;&nbsp;&nbsp;<s:radio list="{'男','女'}" name="cgender"></s:radio>
					</td>
				</tr>
				
				<tr>
					<td align="right">手机号:</td>
					<td>
						<s:textfield name="cphone" class="input" />
					</td>
				</tr>
				<tr>
					<td align="right">邮箱:</td>
					<td>
						<!-- <input name="cemail" class="easyui-validatebox" data-options="required:true,validType:'email'"/> -->
						&nbsp;&nbsp;&nbsp;&nbsp;<s:textfield name="cemail" class="easyui-validatebox" data-options="required:true,validType:'email'" />
					</td>
				</tr>
				<tr>
					<td align="right">收货地址:</td>
					<td>
						<s:textfield name="caddress" class="input" />
					</td>
				</tr>
				
				<tr>
					<td align="right"></td>
					<td><input id="submit" type="submit" value="确认修改"/></td>
				</tr>
			</table>
		</form>
	</div>
  </body>
</html>
