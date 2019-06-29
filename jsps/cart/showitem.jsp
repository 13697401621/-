<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>showitem.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/cart/showitem.css'/>">
	<script src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script src="<c:url value='/js/round.js'/>"></script>
<style type="text/css">
#addr{width: 500px; height: 32px;border: 1px solid #7f9db9; padding-left: 10px; line-height: 32px;}
#consignee{width: 260px; height: 32px;border: 1px solid #7f9db9; padding-left: 10px; line-height: 32px;}
</style>

<script type="text/javascript">
	//计算合计
	$(function() {
		var total = 0;
		$(".subtotal").each(function() {
			total += Number($(this).text());
		});
		$("#total").text(round(total, 2));
	});
</script>
  </head>
  
  <body>
<form id="form1" action="${pageContext.request.contextPath }/order/createOrder.action" method="post">
	<div><font style="font-weight: 900; color: red"><s:actionerror/></font></div>
	<table width="95%" align="center" cellpadding="0" cellspacing="0">
		<tr bgcolor="#efeae5">
			<td width="400px" colspan="5"><span style="font-weight: 900;">生成订单</span></td>
		</tr>
		<tr align="center">
			<td width="10%">&nbsp;</td>
			<td width="50%">商品名称</td>
			<td>单价</td>
			<td>数量</td>
			<td>小计</td>
		</tr>
	
	
	<c:forEach items="${tempCart.order.items }" var="item">
		<tr align="center">
			<td align="right">
				<a class="linkImage" href="${pageContext.request.contextPath }/goods/desc.action?gid=${item.goods.gid }">
					<img border="0" width="54" align="top" src="${pageContext.request.contextPath }/${item.goods.gpicturepath}"/></a>
			</td>
			<td align="left">
				<a href="<${pageContext.request.contextPath }/goods/desc.action?gid=${item.goods.gid }">
					<span>${item.goods.gname }</span>
				</a>
			</td>
			<td>&yen;${item.goods.gprice }</td>
			<td>${item.number }</td>
			<td>
				<span class="price_n">&yen;<span class="subtotal">${item.priceInCart }</span></span>
			</td>
		</tr>
	</c:forEach>
	
		<tr>
			<td colspan="6" align="right">
				<span>总计：</span>
				<span class="price_t">
					<%-- &yen;<span id="total"></span> --%>
					${tempCart.order.totalprice }
				</span>
			</td>
		</tr>
		<tr>
			<td colspan="5" bgcolor="#efeae5"><span style="font-weight: 900">收货人信息</span></td>
		</tr>
		<tr>
			<td colspan="2">
				<span style="font-size: 14px; font-family: 微软雅黑;">收件人:&nbsp;</span>
				<input id="consignee" type="text" name="consigneeName" value="${tempCart.customer.cname }"/>
			</td>
			<td colspan="4">
				<span style="font-size: 14px; font-family: 微软雅黑;">收件人电话:&nbsp;</span>
				<input id="consignee" type="text" name="consigneePhone" value="${tempCart.customer.cphone }"/>
			</td>
		</tr>
		<tr>
			<td colspan="6">
				<span style="font-size: 14px; font-family: 微软雅黑;">收货地址:&nbsp;</span>
				<input id="addr" type="text" name="address" value="${tempCart.customer.caddress }"/>
			</td>
		</tr>
		<tr>
			<td colspan="6">
				<span style="font-size: 14px; font-family: 微软雅黑;">买家留言:&nbsp;</span>
				<input id="addr" type="text" name="message"/>(不多于200个字符)
			</td>
		</tr>
		<tr>
			<td style="border-top-width: 4px;" colspan="5" align="right">
				<a id="linkSubmit" href="javascript:$('#form1').submit();">提交订单</a>
			</td>
		</tr>
	</table>
</form>
  </body>
</html>
