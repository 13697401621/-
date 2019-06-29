<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>订单详细</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/order/desc.css'/>">
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
	<div class="divOrder">
		<span>订单号：${order.oid }
		<c:choose>
			<c:when test="${order.ostate eq '0' }">(等待付款)</c:when>
			<c:when test="${order.ostate eq '1' }">(等待发货)</c:when>
			<c:when test="${order.ostate eq '2' }">(等待确认)</c:when>
			<c:when test="${order.ostate eq '3' }">(交易成功)</c:when>
			<c:when test="${order.ostate eq '4' }">(已取消)</c:when>
		</c:choose>

		下单时间：${order.time }</span>
	</div>
	<div class="divContent">
		<div class="div2">
			<dl>
				<dt>收货人信息</dt>
				<dd>地址:${order.address } &nbsp;&nbsp;|&nbsp;&nbsp;
					姓名:${order.consigneeName }&nbsp;&nbsp;|&nbsp;&nbsp;
					电话:${order.consigneePhone }</dd>
				<%-- <dd>姓名：${order.consigneeName }</dd>
				<dd>电话：${order.consigneePhone }</dd> --%>
			</dl>
		</div>
		<div class="div2">
			<dl>
				<dt>商品清单</dt>
				<dd>
					<table cellpadding="0" cellspacing="0">
						<tr>
							<th class="tt">商品名称</th>
							<th class="tt" align="left">单价</th>
							<th class="tt" align="left">数量</th>
							<th class="tt" align="left">小计</th>
						</tr>

						<c:forEach items="${order.items }" var="item">
						<tr style="padding-top: 20px; padding-bottom: 20px;">
							<td class="td" width="400px">
								<div class="bookname">
								  <img align="middle" width="70" src="${pageContext.request.contextPath }/${item.goods.gpicturepath}"/>
								  <a href="${pageContext.request.contextPath }/goods/desc.action?gid=${item.goods.gid }">${item.goods.gname }</a>
								</div>
							</td>
							<td class="td" >
								<span>&yen;${item.uprice }</span>
							</td>
							<td class="td">
								<span>${item.number }</span>
							</td>
							<td class="td">
								<span>&yen;${item.price }</span>
							</td>			
						</tr>
						</c:forEach>

					</table>
				</dd>
			</dl>
		</div>
		<div style="margin: 10px 10px 10px 550px;">
			<span style="font-weight: 900; font-size: 15px;">合计金额：</span>
			<span class="price_t">&yen;${order.totalprice }</span><br/>

			<c:choose>
				<c:when test="${order.ostate eq '0' }">
					<a href="<c:url value='pay.action?oid=${order.oid }'/>" class="pay"></a><br/>
					<%-- <a id="cancel" href="${pageContext.request.contextPath }/order/canceOrder.action?oid=${order.oid}">取消订单</a><br/> --%>
					<a href="javascript:canceOne('${order.oid}')">取消订单</a>
				</c:when>
				<c:when test="${order.ostate eq '1' }">
					<!-- <a id="cancel" href="javascript:alert('订单已取消！');">取消订单</a><br/> -->
				</c:when>
				<c:when test="${order.ostate eq '2' }">
					<!-- <a id="confirm" href="javascript:alert('交易成功！');">确认收货</a><br/> -->
<a id="confirm" href="${pageContext.request.contextPath }/order/changeOrderState.action?oid=${order.oid}&cusername=${order.customer.cusername }&ostate=3">确认收货</a><br/>
				</c:when>
				<c:when test="${order.ostate eq '4' }">
					<a id="confirm" href="javascript:deleteOne('${order.oid}')">删除订单</a>
				</c:when>
			</c:choose>
			
		</div>
	</div>
</body>
</html>

