<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>top</title>
    <base target="body">
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {font-size: 10pt;}
	a {color: #aaa;}
</style>
  </head>
  
  <body style="background: rgb(78,78,78);color: #fff;">
<h1 style="text-align: center; line-height: 30px;">网上家电商城系统后台管理</h1>
<div style="line-height: 10px;">
	<span>
	<c:choose>
		<c:when test="${manager.mtype eq '0' }">
  			     管理员
 	    </c:when>
		<c:when test="${manager.mtype eq '1' }">
     		  客服人员
   	    </c:when>
		<c:otherwise>
      	     采购人员
        </c:otherwise>
	</c:choose>：${manager.mname }</span>
	<a target="_top" href="<c:url value='/adminjsps/login.jsp'/>">退出</a>
	<span style="padding-left:50px;">
		<a href="<c:url value='/findAllCategory.action'/>">分类管理</a>
		<a href="<c:url value='/adminjsps/admin/goods/main.jsp'/>">商品管理</a>
		<a href="<c:url value='/adminOrder/bkfindOrderByPageBean.action'/>">订单管理</a>
		<a href="<c:url value='/adminjsps/admin/staff/main.jsp' />">员工管理</a>
	</span>
</div>
  </body>
</html>
