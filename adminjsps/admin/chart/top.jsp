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
	response.setIntHeader("refresh", 2);
%>
<script type="text/javascript">
<%
	String cusername = (String)request.getParameter("cusername");
%>
$(function(){
	/* 定时接收信息  */
	var lookMsg = $('#lookmsg').val();
	$.post("${pageContext.request.contextPath }/getMsg.action?cusername=<%=cusername%>"
			,{"lookMsg":lookMsg},function(data){
			 	$(data).each(function(i,n){
			 		$('#lookmsg').val(n.lookMsg);
			 	});
	},"json");
});
</script>
<body>
<c:forEach items="${msgMap }" var="cus">
	<a href="<c:url value='/changeCustomer.action?cusername=${cus.key }'/>" target="_top">${cus.key }</a>
</c:forEach>
<div align="center">
	<%-- <input value="<%=cusername%>" id="username"/> --%>
	<textarea readonly="readonly" cols="60" rows="30" id="lookmsg" name="lookMsg"></textarea><br>
</div>
</body>
</html>