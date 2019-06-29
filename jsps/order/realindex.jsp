<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>支付宝电脑网站支付</title>
<style>
  table {
   margin: 0 auto;
   
   
}

td{
   text-align: center;
 
}

td.btn{
   background-color: #02aaf1;
	color: #FFFFFF;
	font-weight: bold;
	border: none;
	width: 100%;
	height: 30px;
	border-radius: 5px;
	font-size: 16px;
  
}

.note-help {
    text-align:center;
	color: #999999;
	font-size: 12px;
	line-height: 130%;
	margin-top: 35px;
	width: 100%;
	display: block;
}

.new-btn-login-sp {
	padding: 1px;
	margin-left:auto;
	margin-right:auto;
	position: relative;
}

.new-btn-login {
    display: block;
	background-color: #02aaf1;
	color: #FFFFFF;
	font-weight: bold;
	border: none;
	width: 70px;
	height: 35px;
	text-align: center;
	font-size: 16px;
	position: absolute;
	left: 100px;
	top: 20px;
}

.foot-ul {
	width: 100%;
}

#foot {
	margin-top: 10px;
	position: absolute;
	bottom: 15px;
	width: 100%;
}

.foot-ul {
	width: 100%;
}

.foot-ul li {
	width: 100%;
	text-align: center;
	color: #666;
}


</style>
</head>
<body text=#000000  bgColor="#ffffff"  leftMargin=0  topMargin=4>
	<!-- <header class="am-header"> -->
	<h1 align="center">支付宝电脑网站支付体验入口页</h1><br>
	<!-- </header> -->
	<div id="main">
		<form name=alipayment action=/sshjar/jsps/order/alipay.trade.page.pay.jsp method=post >
			<div id="body1" class="show" name="divcontent">
				<table  cellspacing="0" cellpadding="0">
					<tr>
					<td>商户订单号 ：</td>	
					 <td>
						<input id="WIDout_trade_no" name="WIDout_trade_no" value="${order.oid }"  style="width:300px;" /><br>
					</td>
				  </tr>
				  <tr>
					<td>订单名称 ：</td>
					<td>
						<input id="WIDsubject" name="WIDsubject" value="冯楚活商城" style="width:300px;" /><br>
					</td>
				  </tr>
				  <tr>	 
					<td>付款金额 ：</td>
					<td>
						<input id="WIDtotal_amount" name="WIDtotal_amount" value="${order.totalprice }"  style="width:300px;"/><br/>
					</td>
				 </tr>				
				<tr>
					  <td>				 
					    <span class="new-btn-login-sp">
							<button class="new-btn-login" type="submit" >付 款</button><br/>
						</span> 	
					</td>
				</tr>
				</table>
			</div>
		</form>
		
		<div id="note">
		  <span class="note-help">如果您点击“付款”按钮，即表示您同意该次的执行操作。</span> 
	    </div>				
		
		
		<div id="foot">
			<ul class="foot-ul">
				<li>支付宝版权所有 2015-2018 ALIPAY.COM</li>
			</ul>
		</div> 
	</div>
</body>

</html>