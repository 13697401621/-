<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/user/login.css'/>">
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script src="<c:url value='/js/common.js'/>"></script>
<script language="JavaScript">
	// 是否是顶级页面路径，如果不是就跳转。
	if (window != top)
		top.location.href = location.href;
</script>
  </head>
  
  <body>
	<div class="main">
	  <%-- <div><img src="<c:url value='/images/logo.gif'/>" /></div> --%>
	  <div>
	    <%-- <div class="imageDiv"><img class="img" src="<c:url value='/images/zj.png'/>"/></div> --%>
        <div class="login1">
	      <div class="login2">
            <div class="loginTopDiv">
              <span class="loginTop">商城会员登录</span>
              <span>
                <a href="${pageContext.request.contextPath }/jsps/user/regist.jsp" class="registBtn"></a>
              </span>
            </div>
            <div>
           	 <h3><font style="font-weight: 900; color: red"><s:actionerror/></font></h3>
              <form target="_top" action="${pageContext.request.contextPath }/customer/login.action" method="post" id="loginForm">
                <input type="hidden" name="method" value="" />
                  <table>
                    <tr>
                      <td width="50"></td>
                      <!-- <td><label class="error" id="msg">用户名或密码错误</label></td> -->
                    </tr>
                    <tr>
                      <td width="50">用户名</td>
                      <td><input class="input" type="text" name="cusername" id="loginname" value="狗清"/></td>
                    </tr>
                    <tr>
                      <td height="20">&nbsp;</td>
                      <td><label id="loginnameError" class="error">用户名不能为空</label></td>
                    </tr>
                    <tr>
                      <td>密　码</td>
                      <td><input class="input" type="password" name="cpassword" id="loginpass" value="123456"/></td>
                    </tr>
                    <tr>
                      <td height="20">&nbsp;</td>
                      <td><label id="loginpassError" class="error">密码不能为空</label></td>
                    </tr>
                    <!-- <tr>
                      <td>验证码</td>
                      <td>
                        <input class="input yzm" type="text" name="verifyCode" id="verifyCode" value=""/>
                        <img id="vCode" src=""/>
                        <a id="verifyCode">换张图</a>
                      </td>
                    </tr>
                    <tr>
                      <td height="20px">&nbsp;</td>
                      <td><label id="verifyCodeError" class="error">验证码不能为空！</label></td>
                    </tr> -->
                    <tr>
                      <td>&nbsp;</td>
                      <td align="left">
                        <input type="image" id="submit" src="<c:url value='/images/login1.jpg'/>" class="loginBtn"/>
                      </td>
                    </tr>																				
                 </table>
              </form>
            </div>
          </div>
        </div>
      </div>
	</div>
  </body>
</html>
	