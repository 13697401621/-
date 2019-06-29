<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
window.onload = function(){
	var gtype = "${goods.gtype}"; 
	var gname = "${goods.gname}"; 
	var gmanufacturer = "${goods.gmanufacturer}"; 
	var gmodel = "${goods.gmodel}"; 
	var gdescription = "${goods.gdescription}"; 
	var as = document.getElementsByName("a");
	if(gtype!=""){
		for (var i = 0; i < as.length; i++) {
			var href = as[i].getAttribute("href");
			as[i].setAttribute("href",href+"&gtype="+gtype);
		}
	}
	if(gname!=""){
		for (var i = 0; i < as.length; i++) {
			var href = as[i].getAttribute("href");
			as[i].setAttribute("href",href+"&gname="+gname);
		}
	}
	if(gmanufacturer!=""){
		for (var i = 0; i < as.length; i++) {
			var href = as[i].getAttribute("href");
			as[i].setAttribute("href",href+"&gmodel="+gmodel);
		}
	}
	if(gmodel!=""){
		for (var i = 0; i < as.length; i++) {
			var href = as[i].getAttribute("href");
			as[i].setAttribute("href",href+"&gdescription="+gdescription);
		}
	}
	if(gdescription!=""){
		for (var i = 0; i < as.length; i++) {
			var href = as[i].getAttribute("href");
			as[i].setAttribute("href",href+"&gdescription="+gdescription);
		}
	}
}
	function _go() {
		var pc = $("#pageCode").val();//获取文本框中的当前页码
		if(!/^[1-9]\d*$/.test(pc)) {//对当前页码进行整数校验
			alert('请输入正确的页码！');
			return;
		}
		if(pc > 10) {//判断当前页码是否大于最大页
			alert('请输入正确的页码！');
			return;
		}
		var gtype = "${goods.gtype}";
		if(gtype!=""){
			location = "${pageContext.request.contextPath}/goods/bkfindGoodsByPageBean.action?currPage="+pc+"&gtype="+gtype;
		}else{
			location = "${pageContext.request.contextPath}/goods/bkfindGoodsByPageBean.action?currPage="+pc;
		}
	}
</script>


<div class="divBody">
  <div class="divContent">
    <%--上一页 --%>

       <c:choose>
   			<c:when test="${pageBean.currPage eq 1 }">
   				<span class="spanBtnDisabled">上一页</span>
   			</c:when>
   			<c:otherwise>
   				 <a name="a" href="<c:url value='/goods/bkfindGoodsByPageBean.action?currPage=${pageBean.currPage-1 }'/>"
   				  class="aBtn bold">上一页</a> 
   			</c:otherwise>
   		</c:choose>
 
    <%-- 计算begin和end --%>
      <%-- 如果总页数<=6，那么显示所有页码，即begin=1 end=${pb.tp} --%>
        <%-- 设置begin=当前页码-2，end=当前页码+3 --%>
          <%-- 如果begin<1，那么让begin=1 end=6 --%>
          <%-- 如果end>最大页，那么begin=最大页-5 end=最大页 --%>

    
    <%-- 显示页码列表 --%>
   <c:forEach begin="1" var="page" end="${pageBean.totalPage }" step="1">
   		<c:choose>
   			<c:when test="${page eq pageBean.currPage}">
   				<span class="spanBtnSelect">${page }</span>
   			</c:when>
   			<c:otherwise>
   				 <a name="a" href="<c:url value='/goods/bkfindGoodsByPageBean.action?currPage=${page }'/>" class="aBtn">${page }</a>
   			</c:otherwise>
   		</c:choose>
   </c:forEach>
    
    <%-- 显示点点点 --%>
      <span class="spanApostrophe">...</span> 

    
     <%--下一页 --%>
        <c:choose>
   			<c:when test="${pageBean.currPage eq pageBean.totalPage }">
   				<span class="spanBtnDisabled">下一页</span>
   			</c:when>
   			<c:otherwise>
   				 <a name="a" href="<c:url value='/goods/bkfindGoodsByPageBean.action?currPage=${pageBean.currPage+1 }'/>"
   				  class="aBtn bold">下一页</a> 
   			</c:otherwise>
   		</c:choose>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    
    <%-- 共N页 到M页 --%>
    <span>共${pageBean.totalPage }页</span>
    <span>到</span>
    <input type="text" class="inputPageCode" id="pageCode" value="1"/>
    <span>页</span>
    <a href="javascript:_go();" class="aSubmit">确定</a>
  </div>
</div>