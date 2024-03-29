<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/css.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/user/regist.css'/>">
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/jsps/js/user/regist.js'/>"></script>
  </head>
  
  <body>
<div class="divBody">
  <div class="divTitle">
    <span class="spanTitle">新用户注册</span>
  </div>
  <div class="divCenter">
    <form action="${pageContext.request.contextPath }/customer/regist.action" method="post">
    <table>
      <tr>
        <td class="tdLabel">用户名：</td>
        <td class="tdInput">
          <!-- <input type="text" name="cusername" id="loginname" class="input" value=""/> -->
          <s:textfield name="cusername" id="loginname" class="input" />
        </td>
        <td class="tdError">
          <label class="labelError" id="loginnameError">*${custError.cusernameError }</label>
        </td>
      </tr>
      <tr>
        <td class="tdLabel">真实姓名：</td>
        <td class="tdInput">
          <!-- <input type="text" name="cname" id="cname" class="input" value=""/> -->
          <s:textfield name="cname" id="cname" class="input" />
        </td>
        <td class="tdError">
          <label class="labelError" id="cnameError">*${custError.cnameError }</label>
        </td>
      </tr>
      <tr>
        <td class="tdLabel">登录密码：</td>
        <td class="tdInput">
          <!-- <input type="password" name="cpassword" id="loginpass" class="input" value=""/> -->
          <s:password name="cpassword" id="loginpass" class="input"></s:password>
        </td>
        <td class="tdError">
          <label class="labelError" id="loginnameError">*${custError.cpasswordError }</label>
        </td>
      </tr>
      <tr>
        <td class="tdLabel">确认密码：</td>
        <td class="tdInput">
          <!-- <input type="password" name="cstate" id="reloginpass" class="input" value=""/> -->
          <s:password name="cstate" id="reloginpass" class="input"></s:password>
        </td>
        <td class="tdError">
          <label class="labelError" id="reloginpassError">*${custError.repasswordError }</label>
        </td>
      </tr>
      <tr>
        <td class="tdLabel">身份证号：</td>
        <td class="tdInput">
          <!-- <input type="text" name="cidnumber" id="cidnumber" class="input" value=""/> -->
          <s:textfield name="cidnumber" id="cidnumber" class="input" />
        </td>
        <td class="tdError">
          <label class="labelError" id="loginnameError">*${custError.cidnumberError }</label>
        </td>
      </tr>
      <tr>
        <td class="tdLabel">手机：</td>
        <td class="tdInput">
          <!-- <input type="text" name="cphone" id="phone" class="input" value=""/> -->
          <s:textfield name="cphone" id="phone" class="input" />
        </td>
        <td class="tdError">
          <!-- <label class="labelError" id="cphoneError"></label> -->
        </td>
      </tr>
      <!-- <tr>
        <td class="tdLabel">Email：</td>
        <td class="tdInput">
          <input type="text" name="cemail" id="email" class="input" value=""/>
        </td>
        <td class="tdError">
          <label class="labelError" id="emailError"></label>
        </td>
      </tr> -->
      <!-- <tr>
        <td class="tdLabel">图形验证码：</td>
        <td class="tdInput">
          <input type="text" name="verifyCode" id="verifyCode" class="input" value=""/>
        </td>
        <td class="tdError">
          <label class="labelError" id="verifyCodeError"></label>
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>
          <span class="verifyCodeImg"><img id="vCode" width="100" src="" /></span>
        </td>
        <td><a href="javascript: _change()">换一张</a></td>
      </tr> -->
      <tr>
        <td>&nbsp;</td>
        <td>
          <input type="image" src="<c:url value='/images/regist1.jpg'/>" id="submit"/><br>
           <a href="${pageContext.request.contextPath }/index.jsp">&nbsp;返&nbsp;&nbsp;回</a>
        </td>
        <td>&nbsp;</td>
      </tr>
    </table>
    </form>
  </div>
</div>
  </body>
</html>
	