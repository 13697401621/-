<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>聊天页面</title>
</head>
<frameset rows="60%,*">
	<frame frameborder="0" src="<c:url value='/adminjsps/admin/chart/top.jsp?cusername=${cusername }'/>" name="top">
	<frame frameborder="0" src="<c:url value='/adminjsps/admin/chart/under.jsp?cusername=${cusername }'/>" name="under">
</frameset>
</html>