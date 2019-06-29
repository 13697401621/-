<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

    <title>修改员工信息</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="SSH">

  </head>

  <body>
     
    <form action="<c:url value='/updateManager.action'/>" method="post">
         <table>
    		  <tr>
    		  	  <td>用户名：</td>
    		  	  <td>
    		  	    <input type="text" name="manager.musername" value="${manager2.musername }" readonly="readonly" />
    		  	  </td>	
    		  <tr>
    		  	  <td>密&nbsp;&nbsp;码:</td>
    		  	  <td>
    		  	     <input type="password" name="manager.mpassword" value="${manager2.mpassword }" />
    		  	  </td>	 
    		  </tr>
    		  <tr>
    		  	   <td>真实姓名：</td>	
    		  	   <td>
    		  	   	   <input type="text" name="manager.mname" value="${manager2.mname }" />
    		  	   </td>
    		  </tr>
    		   <tr>
    		  	   <td>用户类型：</td>	
    		  	   <td>
    		  	   	   <input type="text" name="manager.mtype" value="${manager2.mtype }" />
    		  	   </td>
    		  </tr>
    		   <tr>
    		  	   <td>物品管理权限：</td>	
    		  	   <td>
    		  	   	   <input type="text" name="manager.goodpower" value="${manager2.goodpower }" />
    		  	   </td>
    		  </tr>
    		  <tr>
    		  	   <td>订单管理权限：</td>	
    		  	   <td>
    		  	   	   <input type="text" name="manager.orderpower" value="${manager2.orderpower }" />
    		  	   </td>
    		  </tr>
    		  <tr>
    		  	   <td>顾客管理权限：</td>	
    		  	   <td>
    		  	   	   <input type="text" name="manager.customerpower" value="${manager2.customerpower }" />
    		  	   </td>
    		  </tr>
    		    <tr>
    		  	   <td>工作管理人员权限：</td>	
    		  	   <td>
    		  	   	   <input type="text" name="manager.workerpower" value="${manager2.workerpower }" />
    		  	   </td>
    		  </tr>
    		   
    		  <tr>
    		  	  <td>
    		  	  	 
   					  <input type="submit" name="submit" value="提交">
      		          <input type="reset" name="reset" value="重置">
    		  	  </td>
    		  </tr>
    
    	</table>	
    </form>
    
  </body>
</html>