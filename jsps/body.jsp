<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>家电前台主页列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/book/list.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/pager/pager.css'/>" />
    <script type="text/javascript" src="<c:url value='/jsps/pager/pager.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/jsps/js/book/list.js'/>"></script>
  </head>
  
<body>
  
<ul>
<s:iterator value="goodsList">
  <li>
  <div class="inner">
    <a class="pic" href="${pageContext.request.contextPath }/goods/desc.action?gid=${gid }">
    	<img src="${pageContext.request.contextPath }/${gpicturepath }" border="0"/>
    </a>
	<br>
	<p>

	<a id="bookname" title="${gname }" href="${pageContext.request.contextPath }/goods/desc.action?gid=${gid }">
			${gname }</a>
	</p>
	<p class="price">
		价格：<span class="price_n">&yen;${gprice }</span>
	</p>
	<p>
		<span>型号：</span><a href="${pageContext.request.contextPath }/goods/desc.action?gid=${gid }" name='P_zz' title='KD-100Z9D'>${gmodel }</a>
		
	</p>
	<p >
		<span>生产厂家：</span><a href="${pageContext.request.contextPath }/goods/desc.action?gid=${gid }">${gmanufacturer }</a>
	</p>
	<p ><span>生产时间：</span>${gproducedate }</p>
  </div>
  </li>
</s:iterator>
</ul>

<div style="float:left; width: 100%; text-align: center;">
<br>
	<hr/>
	<br/>
	<%@include file="/jsps/pager/pager.jsp" %>
</div>

  </body>
 
</html>

