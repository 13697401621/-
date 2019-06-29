<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

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
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/order/list.css'/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/pager/pager.css'/>" />
    <script type="text/javascript" src="<c:url value='/jsps/pager/pager.js'/>"></script>
    
<script type="text/javascript">
	function canceOne(oid) {
		var sure = window.confirm("是否要取消该订单");
		if(sure) {
			window.location.href= "${pageContext.request.contextPath }/order/canceOrder.action?oid="+oid;
		}
	}
	
	function deleteOne(oid) {
		var sure = window.confirm("是否要删除该订单");
		if(sure) {
			window.location.href= "${pageContext.request.contextPath }/order/deleteOrder.action?oid="+oid;
		}
	}
</script>
  </head>
  
  <body>
<div class="divMain">
	<div class="divTitle">
		<span style="margin-left: 150px;margin-right: 280px;">商品信息</span>
		<span style="margin-left: 40px;margin-right: 38px;">金额</span>
		<span style="margin-left: 50px;margin-right: 40px;">订单状态</span>
		<span style="margin-left: 50px;margin-right: 50px;">操作</span>
	</div>
	<br/>
	<table align="center" border="0" width="100%" cellpadding="0" cellspacing="0">


	<%-- <s:iterator value="orders"> --%>
	<c:forEach items="${pageBean.list }" var="order">
		<tr class="tt">
			<td width="320px">订单号：
				<a  href="${pageContext.request.contextPath }/order/findOrderById.action?oid=${order.oid}">${order.oid }</a></td>
			<td width="200px">下单时间：${order.time }</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr style="padding-top: 10px; padding-bottom: 10px;">
			<td colspan="2">


		<c:forEach items="${order.items }" var="item">
			<a class="link2" href="${pageContext.request.contextPath }/goods/desc.action?gid=${item.goods.gid }">
		   	 	<img border="0" width="70" src="${pageContext.request.contextPath }/${item.goods.gpicturepath}"/>
			</a>
		</c:forEach>

		</td>
		<td width="115px">
			<span class="price_t">&yen;${order.totalprice }</span>
		</td>
		<td width="142px">
		
		<c:choose>
			<c:when test="${order.ostate eq '0' }">(等待付款)</c:when>
			<c:when test="${order.ostate eq '1' }">(等待发货)</c:when>
			<c:when test="${order.ostate eq '2' }">(等待确认)</c:when>
			<c:when test="${order.ostate eq '3' }">(交易成功)</c:when>
			<c:when test="${order.ostate eq '4' }">(已取消)</c:when>
		</c:choose>
			
			</td>
			<td>
				<a href="${pageContext.request.contextPath }/order/findOrderById.action?oid=${order.oid}">查看</a><br/>
				<c:choose>
					<c:when test="${order.ostate eq '0' }">
						<a href="<c:url value='pay.action?oid=${order.oid }'/>">支&nbsp;&nbsp;&nbsp;付</a><br/>
						<%-- <a href="${pageContext.request.contextPath }/order/canceOrder.action?oid=${order.oid}">取消订单</a><br/><br/> --%>
						<a href="javascript:canceOne('${order.oid}')">取消订单</a>
					</c:when>
					<c:when test="${order.ostate eq '1' }">
						<%-- <a href="${pageContext.request.contextPath }/order/findOrderById.action?oid=${order.oid}">取消订单</a><br/> --%>
					</c:when>
					<c:when test="${order.ostate eq '2' }">
						<a href="${pageContext.request.contextPath }/order/findOrderById.action?oid=${order.oid}">确认收货</a><br/>
					</c:when>
					<c:when test="${order.ostate eq '4' }">
						<%-- <a href="${pageContext.request.contextPath }/order/deleteOrder.action?oid=${order.oid}">删除订单</a><br/><br/> --%>
						<a href="javascript:deleteOne('${order.oid}')">删除订单</a>
					</c:when>
				</c:choose>
			</td>
		</tr>
	</c:forEach>

	</table>
	<br/>
	<%@include file="/jsps/pager/orderPager2.jsp" %>
</div>
  </body>
</html>
