<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>员工管理左栏</title>
<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/adminjsps/admin/css/book/left.css'/>">
	<script type="text/javascript" src="<c:url value='/menu/mymenu.js'/>"></script>
	<link rel="stylesheet" href="<c:url value='/menu/mymenu.css'/>" type="text/css" media="all">
	
  <style type="text/css">
	body {font-size: 10pt;}
	a {color: #aaa;}
  </style>
</head>
<body style="background: rgb(78,78,78);color: #fff;">

   <a class="pic" href="<c:url value='/adminjsps/admin/staff/add.jsp' />" target="bodystaff">添加员工信息</a><br><br>
   <a class="pic" href="<c:url value='/queryManager.action'/>" target="bodystaff">查询员工信息</a><br><br>
   	


</body>
</html>