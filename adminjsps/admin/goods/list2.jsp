<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>家电列表</title>
    
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

<s:iterator value="pageBean.list">
  <li>
  <div class="inner">

    <a class="pic" href="${pageContext.request.contextPath }/goods/editGoodsPre.action?gid=${gid }">

    	<img src="${pageContext.request.contextPath }/${gpicturepath }" border="0"/>
    </a>
   <%--  <p class="price">
		<span class="price_n">&yen;${gprice }</span>
		<span class="price_r">&yen;50.9</span>
		(<span class="price_s">6.9折</span>)
		<span>价格：</span>&yen;${gprice }
	</p> --%>
	<br>
	
	<p>

	<a id="bookname" title="${gname }" href="#">

			${gname }</a>
	</p>
	<p class="price">
		价格：<span class="price_n">&yen;${gprice }</span>　　
		<a href="${pageContext.request.contextPath }/goods/deleteGoods.action?gid=${gid }">删除商品</a>
	</p>
	<p>
		<span>型号：</span><a href="#" name='P_zz' title='KD-100Z9D'>${gmodel }</a>　　
		<a href="${pageContext.request.contextPath }/goods/editGoodsPre.action?gid=${gid }">修改商品</a>
	</p>
	<p >
		<span>生产厂家：</span><a href="#">${gmanufacturer }</a>
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

