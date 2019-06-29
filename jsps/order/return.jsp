<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>电脑网站支付return_url</title>
</head>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.alipay.config.*"%>
<%@ page import="com.alipay.api.*"%>
<%@ page import="com.alipay.api.internal.util.*"%>
<script type="text/javascript">
window.onload = function(){
	
	var timer = document.getElementById("timer");
	setTimeout(function(){ timer.innerHTML = "2秒后跳回主页面"; }, 1000);
	setTimeout(function(){ timer.innerHTML = "1秒后跳回主页面"; }, 2000);
	setTimeout(function(){ timer.innerHTML = "0秒后跳回主页面"; 
	/* var url = "${pageContext.request.contextPath}/customer/login.action?"
	+"cusername=${customer.cusername}&cpassword=${customer.cpassword }";
	window.location.href=url; */
	var fm = document.getElementById("fm");
	fm.submit();
	}, 3000);

}


</script>
<body>
<p><font size="50">${msg }</font><font size="50" id="timer">3秒后跳回主页面</font></p>
<form id="fm" action="${pageContext.request.contextPath}/customer/login.action" method="post">
	<input type="hidden" name="cusername" value="${customer.cusername }">
	<input type="hidden" name="cpassword" value="${customer.cpassword }">
</form>
</body>
</html>