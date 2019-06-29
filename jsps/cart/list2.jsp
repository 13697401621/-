<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>cartlist.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/jquery/jquery.datepick.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/jquery/jquery.datepick-zh-CN.js'/>"></script>
	
	<script src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script src="<c:url value='/js/round.js'/>"></script>
	
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/cart/list.css'/>">
<script type="text/javascript">
window.onload = function() {
	
	/* 批量删除点击事件 */
	var del = document.getElementById("deleteList");
	del.onclick = function() {
		question = confirm("确定要删除吗？");
		if(question) {
			var list = document.getElementsByName("checkboxBtn");
			var str = "";
			for(var i = 0; i < list.length; i++) {
				if(list[i].checked) {
					str += list[i].value;
					if(i < list.length - 1) {
						str += ",";
					}
				}
			}
			window.location.href = "${pageContext.request.contextPath }/cart/deleteList.action?gidList=" + str;
		}
	}
	
	/* 购买点击事件 */
	var buy = document.getElementById("jiesuan");
	buy.onclick = function() {
		var list = document.getElementsByName("checkboxBtn");
		var str = "";
		for(var i = 0; i < list.length; i++) {
			if(list[i].checked) {
				str += list[i].value;
				if(i < list.length - 1) {
					str += ",";
				}
			}
		}
		window.location.href = "${pageContext.request.contextPath }/cart/readyToOrder.action?gidList=" + str;
	}
}

$(function() {
	showTotal();//显示合计
	// 给全选按钮添加点击事件
	$("#selectAll").click(function() {
		var flag = $(this).attr("checked");//获取全选的状态
		setAll(flag);//让所有条目复选框与全选同步
		setJieSuanStyle(flag);//让结算按钮与全选同步
	});
	
	// 给条目复选框添加事件
	$(":checkbox[name=checkboxBtn]").click(function() {
		var selectedCount = $(":checkbox[name=checkboxBtn][checked=true]").length;//被勾选复选框个数
		var allCount = $(":checkbox[name=checkboxBtn]").length;//所有条目复选框个数
		if(selectedCount == allCount) {//全选了
			$("#selectAll").attr("checked", true);//勾选全选复选框
			setJieSuanStyle(true);//使结算按钮可用
		} else if(selectedCount == 0) {//全撤消了
			$("#selectAll").attr("checked", false);//撤消全选复选框
			setJieSuanStyle(false);//使结算按钮不可用			
		} else {//未全选
			$("#selectAll").attr("checked", false);//撤消全选复选框
			setJieSuanStyle(true);//使结算按钮可用
		}
		showTotal();//重新计算合计
	});
	
	// 给jia、jian添加事件
	/* $(".jian").click(function() {
		var cartItemId = $(this).attr("id").substring(0, 5);
		var quantity = Number($("#" + cartItemId + "Quantity").val());
		if(quantity == 1) {
			if(confirm("您是否真要删除该条目？")) {
				alert("删除成功！");		
			}
		} else {
			var num = quantity-1
			sendUpdate(cartItemId, num);
		}
	});
	$(".jia").click(function() {
		var cartItemId = $(this).attr("id").substring(0, 5);
		var quantity = Number($("#" + cartItemId + "Quantity").val());
		sendUpdate(cartItemId, quantity+1);
	}); */
});

// 异步请求，修改数量
/* function sendUpdate(cartItemId, quantity) {
	
	// 1. 通过cartItemId找到输入框元素
	// 2. 通过cartItemId找到小计元素
	
	var input = $("#" + cartItemId + "Quantity");
	var subtotal = $("#" + cartItemId + "Subtotal");
	var currPrice = $("#" + cartItemId + "CurrPrice");

	input.val(quantity);
	subtotal.text(round(currPrice.text() * quantity, 2));
	showTotal();
} */

// 设置所有条目复选框
function setAll(flag) {
	$(":checkbox[name=checkboxBtn]").attr("checked", flag);//让所有条目的复选框与参数flag同步
	showTotal();//重新设置合计
}

// 设置结算按钮的样式
function setJieSuanStyle(flag) {
	if(flag) {// 有效状态
		$("#jiesuan").removeClass("kill").addClass("jiesuan");//切换样式
		$("#jiesuan").unbind("click");//撤消“点击无效”
	} else {// 无效状态
		$("#jiesuan").removeClass("jiesuan").addClass("kill");//切换样式
		$("#jiesuan").click(function() {//使其“点击无效”
			return false;
		});
	}
}

// 显示合计
function showTotal() {
	var total = 0;//创建total，准备累加
	/*
	1. 获取所有被勾选的复选框，遍历之
	*/
	$(":checkbox[name=checkboxBtn][checked=true]").each(function() {
		/*
		2. 通过复选框找到小计
		*/
		var subtotal = Number($("#" + $(this).val() + "Subtotal").text());
		total += subtotal;
	});
	/*
	3. 设置合计
	*/
	$("#total").text(round(total, 2));
}


</script>
  </head>
  <body>
<br/><br/>
<c:choose>
	<c:when test="${empty sessionScope.cart.order }">
		<table width="95%" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td align="right">
					<img align="top" src="<c:url value='/images/icon_empty.png'/>"/>
				</td>
				<td>
					<span class="spanEmpty">您的购物车中暂时没有商品</span>
				</td>
			</tr>
		</table> 
	<br/>
	<br/> 
	</c:when>


	<c:otherwise>
		<div><font style="font-weight: 900; color: red"><s:actionerror/></font></div>
		<table width="95%" align="center" cellpadding="0" cellspacing="0">
			<tr align="center" bgcolor="#efeae5">
				<td align="left" width="50px">
					<!-- <input type="checkbox" id="selectAll" checked="checked" name="gids" /><label for="selectAll">全选</label> -->
					<input type="checkbox" id="selectAll" checked="checked"/><label for="selectAll">全选</label>
				</td>
				<td colspan="2">商品名称</td>
				<td>单价</td>
				<td>数量</td>
				<td>小计</td>
				<td>操作</td>
			</tr>
		
			<c:forEach items="${cart.order.items }" var="item">
				<tr align="center">
					<td align="left">
						<input value="${item.goods.gid}" type="checkbox" name="checkboxBtn" checked="checked"/>
					</td>
					
					<td align="left" width="70px">
						<a class="linkImage" href="<c:url value='/jsps/goods/desc.jsp'/>">
							<img border="0" width="54" align="top" src="${pageContext.request.contextPath }/${item.goods.gpicturepath}"/></a>
					</td>
					<td align="left" width="400px">
					    <a href="<c:url value='/jsps/goods/desc.jsp'/>">
					    	<span>${item.goods.gname}</span></a>
					</td>
					<td><span>&yen;<span class="currPrice" id="12345CurrPrice">${item.goods.gprice}</span></span></td>
					<td>
					
						<c:choose>
							<c:when test="${item.number eq 1 }"></c:when>
							<c:otherwise>
								<!-- 数量减1 -->
								<a class="jian" id="12345Jian"
									href="${pageContext.request.contextPath }/cart/goodsCountChangeOne.action?gid=${item.goods.gid}&num=-1">
								</a>
							</c:otherwise>
						</c:choose>
						<input class="quantity" readonly="readonly" id="12345Quantity" type="text" value="${item.number}"/>
						<c:choose>
							<c:when test="${item.number eq item.goods.gstock }"></c:when>
							<c:otherwise>
								<!-- 数量加1 -->
								<a class="jia" id="12345Jia"
									href="${pageContext.request.contextPath }/cart/goodsCountChangeOne.action?gid=${item.goods.gid}&num=1"></a>
							</c:otherwise>
						</c:choose>
						
					</td>
					<td width="100px">
						<span class="price_n">&yen;<span class="subTotal" id="12345Subtotal">${item.priceInCart}</span></span>
					</td>
					<td>
						<a href="${pageContext.request.contextPath }/cart/deleteItem.action?gid=${item.goods.gid}"><button>删除</button></a>
					</td>
				</tr>
			</c:forEach>
		
			<tr>
				<td colspan="4" class="tdBatchDelete">
					<button id="deleteList">批量删除</button>
				</td>
				<%-- <td colspan="3" align="right" class="tdTotal">
					<span>总计：</span>
					<span class="price_t">
						&yen;<span id="total"></span>
						${cart.order.totalprice }
					</span>
				</td> --%>
				<%-- <td colspan="3" align="right" class="tdTotal">
					<span>总计：</span><span class="price_t">&yen;<span id="total"></span></span>
				</td> --%>
			</tr>
			
			<tr>
				<td colspan="7" align="right">
					<%-- <a href="<c:url value='/jsps/cart/showitem.jsp'/>" id="jiesuan" class="jiesuan"></a> --%>
					<button id=jiesuan class="jiesuan"></button>
				</td>
			</tr>
		</table>
		
		<form id="form1" action="<c:url value='/jsps/cart/showitem.jsp'/>" method="post">
			<input type="hidden" name="cartItemIds" id="cartItemIds"/>
			<input type="hidden" name="method" value="loadCartItems"/>
		</form>
	</c:otherwise>
</c:choose>

  </body>
</html>
