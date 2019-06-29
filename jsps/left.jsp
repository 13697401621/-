<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>left</title>
    <base target="body"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/menu/mymenu.js'/>"></script>
	<link rel="stylesheet" href="<c:url value='/menu/mymenu.css'/>" type="text/css" media="all">
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/left.css'/>">
<script language="javascript">
var bar = new Q6MenuBar("bar", "");
var topcategory = new Array();
var dicid = new Array();

$(function() {
	var top ;
	bar.colorStyle = 4;
	bar.config.imgDir = "<c:url value='/menu/img/'/>";
	bar.config.radioButton=true;
	$.ajaxSetup({ 
	    async : false 
	}); 
	$.post("${pageContext.request.contextPath }/findTypeByDicname.action",{"dicname":"topcategory"},function(data){
		$(data).each(function(i,n){
			topcategory[i]=n.value;
			dicid[i]=n.dicid;	
			/* $.post("${pageContext.request.contextPath }/findTypeByCode.action",{"code":n.dicid},function(data){
				$(data).each(function(i,n){
					alert(topcategory+","+n.value);
					bar.add(topcategory,n.value,"${pageContext.request.contextPath }/goods/findAllGoods?gtype="+n.value,"body");
				});
			},"json"); */
		});
	},"json");
	for (var i = 0; i < topcategory.length; i++) {
		top=topcategory[i];
		$.post("${pageContext.request.contextPath }/findTypeByCode.action",{"code":dicid[i]},function(data){
		$(data).each(function(i,n){
			bar.add(top,n.value,"${pageContext.request.contextPath }/goods/findGoodsByPageBean?gtype="+n.value,"body");
		});
	},"json");
	}
	/* bar.add("大型家用电器", "冰箱", "${pageContext.request.contextPath }/goods/findAllGoods?gtype=冰箱", "body");
	bar.add("大型家用电器", "空调", "${pageContext.request.contextPath }/goods/findAllGoods?gtype=空调", "body");
	bar.add("大型家用电器", "洗衣机", "${pageContext.request.contextPath }/goods/findAllGoods?gtype=洗衣机", "body");
	bar.add("大型家用电器", "干衣机", "${pageContext.request.contextPath }/goods/findAllGoods?gtype=干衣机", "body");
	 */
	$("#menu").html(bar.toString());
});

</script>
</head>
  
<body>  
  <div id="menu"></div>
</body>
</html>
