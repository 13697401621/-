<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.min.js'/>"></script>
<script type="text/javascript">
<%
String cusername = (String)request.getParameter("cusername");
%>
$(function(){
	/* 发送信息  */
	$('#btn').click(function(){
		var mydate = new Date();
		var t=mydate.toLocaleString();
		var sendMsg = "客服  "+t+"\n";
		sendMsg += $('#sendmsg').val();
		$('#sendmsg').val("");
		$.post("${pageContext.request.contextPath }/sendMsg.action?cusername=<%=cusername%>",{"sendMsg":sendMsg}
		,function(data){},"json");
	});
	
});
</script>
<body>
<div align="center">
	<textarea cols="52" rows="10" id="sendmsg" name="sendMsg"></textarea>
	<button id="btn">发送</button>
</div>
</body>
</html>