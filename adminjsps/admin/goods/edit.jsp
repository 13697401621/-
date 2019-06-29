<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>addgoods</title>
    
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
$(function (){
	 
	 
	$("#btn").hover(
		function() {
			$("#btn").css("background","url(/sshjar/images/hong_1_btn.jpg)");
		},
		function() {
			$("#btn").css("background","url(/sshjar/images/hong_2_btn.jpg)");
		}
	); 
/* 	$.post("${pageContext.request.contextPath }/findTypeByDicname.action",{"dicname":"topcategory"},function(data){
		$(data).each(function(i,n){
			$('#gtopcategory').append("<option value='"+n.dicid+"'>"+n.value+"</option>");
		});
	},"json");
	*/
	/*  当一级菜单改变时，先遍历二级，并且删除上一次添加的option  */
	$('#gtopcategory').change(function(){
		$("#gtype option").each(function(){
			$(this).remove();
		});
		$.post("${pageContext.request.contextPath }/findTypeByCode.action",{"code":$('#gtopcategory option:selected').val()},function(data){
			$(data).each(function(i,n){
				$('#gtype').append("<option value='"+n.value+"'>"+n.value+"</option>");
			});
		},"json");
	}); 
});


</script>
  </head>
  
  <body>
  <div>
   <p style="font-weight: 900; color: red;"><s:actionerror/></p>
   <form action="${pageContext.request.contextPath }/goods/editGoods.action" enctype="multipart/form-data" method="post" id="form">
    <div>
    	<!-- 获取之前的图片路径 -->
    	<input id="gpicturepath" name="gpicturepath" type="hidden" value="${goods.gpicturepath }"/>
    	<input name="gid" type="hidden" value="${goods.gid }">
	    <ul> 
	    	<li>商品名称：　<input id="gname" type="text" name="gname" value="${goods.gname }" 
	    	class="easyui-validatebox" data-options="required:true" /></li>
	    	<li>图片：　<input id="image" type="file" name="image"/></li>
	    	<li>价格：<input id="gprice" type="text" name="gprice" value="${goods.gprice }" 
	    	class="easyui-validatebox" data-options="required:true"/></li>
	    	<li>库存：<input id="gstock" type="text" name="gstock" value="${goods.gstock }" 
	    	class="easyui-validatebox" data-options="required:true"/></li>
	    </ul>
		<hr style="margin-left: 50px; height: 1px; color: #dcdcdc"/>
		<table>
			<tr>
				<td colspan="3">
					制造厂家：　　<input id="gmanufacturer" type="text" name="gmanufacturer" value="${goods.gmanufacturer }" 
	    					class="easyui-validatebox" data-options="required:true"/>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					商品型号：　　<input id="gmodel" type="text" name="gmodel" value="${goods.gmodel }" 
	    					class="easyui-validatebox" data-options="required:true"/>
				</td>
			</tr>
			<tr>
				<td>
					商品一级分类：<select name="gtopcategory" id="gtopcategory">
								<option value="-1">-未选择-</option>
								<c:forEach items="${categoryList }" var="category">
									<c:choose>
										<c:when test="${category.topCategory.dicid eq goods.gtopcategory }">
											<option value="${category.topCategory.dicid }" selected="selected">${category.topCategory.value }</option>
										</c:when>
										<c:otherwise>
											<option value="${category.topCategory.dicid }">${category.topCategory.value }</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
					商品二级级分类：<select name="gtype" id="gtype">
								<option value="-1">-未选择-</option>
								<c:forEach items="${categoryList }" var="category">
									<c:if test="${category.topCategory.dicid eq goods.gtopcategory }">
										<c:forEach items="${category.secondCategory }" var="dictionary">
										<c:choose>
											<c:when test="${dictionary.value eq goods.gtype }">	
												<option value="${dictionary.value }" selected="selected">${dictionary.value }</option>	
											</c:when>
											<c:otherwise>
												<option value="${dictionary.value }">${dictionary.value }</option>
											</c:otherwise>
										</c:choose>
										</c:forEach>
									</c:if>
								</c:forEach>
							</select>		
				</td>
			</tr>
			<tr>
				<td colspan="3">生产日期：　　<input id="gproducedate" type="date" name="gproducedate" value="${goods.gproducedate }" 
	    					class="easyui-validatebox" data-options="required:true"/></td>
			</tr>
			<tr>
				<td colspan="3">保修期：　　　<input style="width:30px;" id="gguaranteedate" type="text" name="gguaranteedate" value="${goods.gguaranteedate }" 
	    					class="easyui-validatebox" data-options="required:true"/>年</td>
			</tr>
			<tr>
				<td colspan="3">商品状态：　　<select id="gstate" name="gstate" class="easyui-validatebox" data-options="required:true">
					<option value="1">在售</option>
					<option value="0">已下架</option>
				</select></td>
	    	</tr>
			<tr>
				<td colspan="3">商品描述：　　<textarea style="height:50px;" id="gdescription" type="text" name="gdescription"
	    					class="easyui-validatebox" data-options="required:true">${goods.gdescription }</textarea></td>
	    	</tr>
			
			<tr>
				<td>
					<input type="submit" style="background: url(/sshjar/images/hong_2_btn.jpg) no-repeat;" id="btn" class="btn" value="修改商品">
				</td>
			</tr>
		</table>
	</div> 
   </form>
  </div>
  </body>
</html>
