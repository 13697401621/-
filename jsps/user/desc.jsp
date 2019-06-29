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
   <!--  <div class="div0">
    	<span></span>
    </div> -->

	<div class="div1">
		<!-- <form action=""></form> -->
		<table>
			<tr>
				<td><label class="error">${msg }</label></td>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td align="right">用户名:</td>
				<td>
					${sessionScope.customer.cusername }
					<%-- <input type="hidden" name="cusername" value="${sessionScope.customer.cusername }"> --%>
				</td>
			</tr>
			<tr>
				<td align="right">真实姓名:</td>
				<td>
					${sessionScope.customer.cname }
					<%-- <input type="hidden" name="cname" value="${sessionScope.customer.cname }"> --%>
				</td>
			</tr>
			
			<tr>
				<td align="right">性别:</td>
				<td>
					${sessionScope.customer.cgender }
					<%-- <input type="hidden" name="cgender" value="${sessionScope.customer.cgender }"> --%>
				</td>
			</tr>
			
			<tr>
				<td align="right">手机号:</td>
				<td>
					${sessionScope.customer.cphone }
					<%-- <input type="hidden" name="cphone" value="${sessionScope.customer.cphone }"> --%>
				</td>
			</tr>
			<tr>
				<td align="right">邮箱:</td>
				<td>
					${sessionScope.customer.cemail }
					<%-- <input type="hidden" name="cemail" value="${sessionScope.customer.cemail }"> --%>
				</td>
			</tr>
			<tr>
				<td align="right">收货地址:</td>
				<td>
					${sessionScope.customer.caddress }
					<%-- <input type="hidden" name="caddress" value="${sessionScope.customer.caddress }"> --%>
				</td>
			</tr>
			
			<tr>
				<td align="right"></td>
				<td>
					<a href="${pageContext.request.contextPath }/customer/editUI.action">
						<input id="submit" type="button" value="编       辑"/>
					</a>&nbsp;&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath }/jsps/user/pwd.jsp">
						<input id="submit" type="button" value="修改密码"/>
					</a>
				</td>
			</tr>
		</table>
	</div>
  </body>
</html>
