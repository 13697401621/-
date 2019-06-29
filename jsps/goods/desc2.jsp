<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>图书详细</title>
    
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
	<script src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/book/desc.css'/>">
	<script src="<c:url value='/jsps/js/book/desc.js'/>"></script>
	
<script type="text/javascript">
window.onload = function() {
	var del = document.getElementById("addTocart");
	del.onclick = function() {
		var getNum=document.getElementById("cnt");
		var getId=document.getElementById("goodsId");
		var num = getNum.value;
		var id = getId.value;
		window.location.href = "${pageContext.request.contextPath }/cart/goodsAdd.action?num=" + num + "&gid=" + id;
	}
	
	var del = document.getElementById("buynNow");
	del.onclick = function() {
		var getNum=document.getElementById("cnt");
		var getId=document.getElementById("goodsId");
		var num = getNum.value;
		var id = getId.value;
		window.location.href = "${pageContext.request.contextPath }/order/buyOneItem.action?num=" + num + "&gid=" + id;
	}
}
	
</script>
  </head>
  
  <body>
  <div class="divBookName">${gname }</div>
  <div>
    <img align="top" src="${pageContext.request.contextPath }/${gpicturepath }" class="img_image_w"/>
    <div class="divBookDesc">
	    <ul>
	   		<li><span style="font-size: 20px; color: red">${gdescription }</span></li>
	    	<li>商品编号：${gid }</li>
	    	<li>销售价：<span class="price_n">&yen;${gprice }</span></li>
	    	<!-- <li>定价：<span class="spanPrice">&yen;59.0</span>　折扣：<span style="color: #c30;">6.9</span>折</li> -->
	    </ul>
		<hr class="hr1"/>
		<table>
			
			<tr>
				<td colspan="3">
					型号：${gmodel }
				</td>
			</tr>
			<tr>
				<td colspan="3">
					厂商：${gmanufacturer }
				</td>
			</tr>
			<%-- <tr>
				<td colspan="3">出厂时间：${gproducedate }</td>
			</tr> --%>
			<tr>
				<td>库存：${gstock }</td>
				<td>出厂时间：${gproducedate }</td>
				<td>保修时长：${gguaranteedate }</td>
			</tr>
			<!-- <tr>
				<td width="180">印刷时间：2013-06-01</td>
				<td>开本：16 开</td>
				<td>纸张：胶版纸</td>
			</tr> -->
		</table>
		<div class="divForm">
			
			<input type="hidden" name="gid" value="${gid }"/>
			<br/>
				我要买：<input id="cnt" style="width: 40px;text-align: center;" type="text" name="num" value="1"/>件
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button id="addTocart">加入购物车</button>&nbsp;&nbsp;
				<%-- <a href="${pageContext.request.contextPath }/order/buyOneItem.action?gid=${gid }"><button>立 即 购 买</button></a> --%>
  				

  			<button id="buynNow">立 即 购 买</button>
  			<input type="hidden" name="aaa" id="goodsId" value="${gid }"/>
  			<!-- <a id="btn" href="javascript:$('#form1').submit();">加入购物车</a> -->
  		</div>	
	</div>
  </div>
  </body>
</html>
