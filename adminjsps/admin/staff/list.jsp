<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
   String path = request.getContextPath();
   String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工信息列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="<c:url value='/adminjsps/admin/css/book/add.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/jquery/jquery.datepick.css'/>">
<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jquery/jquery.datepick.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jquery/jquery.datepick-zh-CN.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value='/themes/default/easyui.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/themes/icon.css'/>" >
<!-- 引入文件顺序不能调乱 -->
<script type="text/javascript" src="<c:url value='/js/jquery.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.easyui.min.js'/>"></script>
<script type="text/javascript">
   
    
    //删除, 使用 ajax 的方式
    $(function(){
        $(".delete").click(function(){
          
        });
    })
</script>

<style type="text/css">
	.wrap {
		width: 1000px; //设置需要固定的宽度
		white-space: nowrap; //不换行
	  }
</style>

</head>
<body>
    <h3>ManagerList Page</h3>
    
    根据姓名查询，若不输入，则查询全部
     <form action="<c:url value='/queryManager.action'/>" method="post">
          Musername： <input type="text" name="queryText" value="${searchText }" />
        <input type="submit" value="查询" />    
     </form>
      
        <table border="1px" cellpadding="0" cellspacing="0" align="center"  style="width: 800px" >
         <thead>
            <tr bgcolor="#ff0">      
                <th>用户名：</th>
                <th>密&nbsp;&nbsp;码：</th>
                <th>真实姓名：</th>
                <th>用户类型：</th>
                <th>物品管理权限：</th>
                <th>订单管理权限：</th>
                <th>顾客管理权限：</th>
                <th>工作人员管理权限：</th>
                <th>操作：</th> 
            </tr>
            </thead>
            <tbody>
             <c:forEach var="manager" items="${managers }">
                 
                <tr>
                    <td>${manager.musername}</td>
                    <td>${manager.mpassword}</td>
                    <td>${manager.mname}</td>
                    <td>${manager.mtype}</td>
                    <td>${manager.goodpower}</td>
                    <td>${manager.orderpower}</td>
                    <td>${manager.customerpower }</td>
                    <td>${manager.workerpower }</td>
                           
                    <td>
                        <a href="prepupManager.action?musername=${manager.musername }" >修改</a>
                        <a href="deleteManager.action?musername=${manager.musername }">删除</a>
                    </td>
                </tr>
                </c:forEach>
            </tbody>
            
        </table>
        
</body>
</html>