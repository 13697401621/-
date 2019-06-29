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
<%
	response.setIntHeader("refresh", 3);
%>
<script type="text/javascript">
$(function(){
	/* 定时接收信息  */
	var lookMsg = $('#lookmsg').val();
	$.post("${pageContext.request.contextPath }/getMsg.action?cusername=${customer.cusername}"
			,{"lookMsg":lookMsg},function(data){
			 	$(data).each(function(i,n){
			 		$('#lookmsg').val(n.lookMsg);
			 	});
	},"json");
});
</script>
<body>
<div align="center">
	<textarea readonly="readonly" cols="60" rows="30" id="lookmsg" name="lookMsg"></textarea><br>
</div>
</body>
</html>