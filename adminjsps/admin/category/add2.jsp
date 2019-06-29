<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加分类</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript">
	$(function(){
		$.post("${pageContext.request.contextPath }/findTypeByDicname.action",{"dicname":"topcategory"},function(data){
			$(data).each(function(i,n){
				$('#code').append("<option value='"+n.dicid+"'>"+n.value+"</option>");
			});
		},"json");
	});
		function checkForm() {
			if(!$("#value").val()) {
				alert("分类名不能为空！");
				return false;
			}
			if(!$("#code").val()) {
				alert("一级分类不能为空！");
				return false;
			}

			return true;
		}
	</script>
<style type="text/css">
	body {background: rgb(254,238,189);}
</style>
  </head>
  
  <body>
    <h3>添加2级分类</h3>
    <h1></h1>
    <p style="font-weight: 900; color: red">${msg }</p>
    <form action="<c:url value='/addSecondCategory.action'/>" method="post" onsubmit="return checkForm()">
    	分类名称：<input type="text" name="value" id="value"/><br/>
    	一级分类：<select name="code" id="code">
    		<option value="">===选择1级分类===</option>
    	</select><br/>
    	<input type="submit" value="添加二级分类"/>
    	<input type="button" value="返回" onclick="history.go(-1)"/>
    </form>
  </body>
</html>
