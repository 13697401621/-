<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>订单列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/pager/pager.css'/>" />
    <script type="text/javascript" src="<c:url value='/jsps/pager/pager.js'/>"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value='/adminjsps/admin/css/order/list.css'/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/css.css'/>" />
<script type="text/javascript">
	function canceOne(oid) {
		var sure = window.confirm("是否要取消该订单");
		if(sure) {
			window.location.href= "${pageContext.request.contextPath}/adminOrder/adminCanceOrder.action?oid="+oid;
		}
	}
</script>
  </head>
  
  <body>
<p class="pLink">
  <a href="<c:url value='/adminOrder/bkfindOrderByPageBean.action'/>?ostate=0">未付款</a>  | 
  <a href="<c:url value='/adminOrder/bkfindOrderByPageBean.action'/>?ostate=1">已付款</a>  | 
  <a href="<c:url value='/adminOrder/bkfindOrderByPageBean.action'/>?ostate=2">已发货</a>  | 
  <a href="<c:url value='/adminOrder/bkfindOrderByPageBean.action'/>?ostate=3">交易成功</a>  | 
  <a href="<c:url value='/adminOrder/bkfindOrderByPageBean.action'/>?ostate=4">已取消</a>
</p>
<div class="divMain">
	<div class="title">
		<div style="margin-top:7px;">
			<span style="margin-left: 150px;margin-right: 280px;">商品信息</span>
			<span style="margin-left: 40px;margin-right: 100px;">金额</span>
			<span style="margin-left: 50px;margin-right: 53px;">订单状态</span>
			<span style="margin-left: 100px;">操作</span>
		</div>
	</div>
	<br/>
	<table align="center" border="0" width="100%" cellpadding="0" cellspacing="0">
	
	
	
	<c:forEach items="${pageBean.list }" var="order">
		<tr class="tt">
			<td width="320px">订单号：<a  href="<c:url value='/adminjsps/admin/order/desc.jsp'/>">${order.oid }</a></td>
			<td width="200px">下单时间：${order.time }</td>
			<td width="178px">&nbsp;用户名：${order.customer.cusername }</td>
			<td width="205px">&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr style="padding-top: 10px; padding-bottom: 10px;">
			<td colspan="2">
		<c:forEach items="${order.items }" var="item">
			<img border="0" width="70" src="<c:url value='/${item.goods.gpicturepath }'/>"/>
		</c:forEach>
			</td>
			<td style="padding-left: 0">
				<span class="price_t">&yen;${order.totalprice }</span>
			</td>
			<td>
				<c:choose>
					<c:when test="${order.ostate eq '0' }">(等待付款)</c:when>
					<c:when test="${order.ostate eq '1' }">(等待发货)</c:when>
					<c:when test="${order.ostate eq '2' }">(等待确认)</c:when>
					<c:when test="${order.ostate eq '3' }">(交易成功)</c:when>
					<c:when test="${order.ostate eq '4' }">(交易失败)</c:when>
				</c:choose>
			</td>
			<td>
				<a href="<c:url value='/adminOrder/orderDesc.action'/>?oid=${order.oid}">查看</a><br/>
				<c:choose>
					<c:when test="${order.ostate eq '0' }">
						<%-- <a href="<c:url value='/adminOrder/adminCanceOrder.action'/>?oid=${order.oid}">取消</a><br/> --%>
						<a href="javascript:canceOne('${order.oid}')">取消订单</a>
					</c:when>
					<c:when test="${order.ostate eq '1' }">
						<a href="<c:url value='/adminOrder/updateOrderState.action'/>?oid=${order.oid}&ostate=2">发货</a><br/>
					</c:when>
				</c:choose>
			</td>
		</tr>
	</c:forEach>

	</table>
	<br/>
	<%@include file="/jsps/pager/orderPager.jsp" %>
</div>
  </body>
</html>
