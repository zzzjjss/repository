
<%@page import="com.uf.fanfan.common.Constant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>FanFou</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<%@ include file="jsAndCSS.jsp"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>	
<body>
<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
					<ul class="nav">
						<li><a style="font-size:30px">FanFan</a></li>
						<li  id="platformInfo" class="active"><a href="platformInfo.jsp" target="navLeft">平台简介</a></li>
						<c:if test="${sessionScop.user!=null}">
						<li  id="shopManage"><a href="admin/navLeftShopManage.jsp" target="navLeft">餐馆管理</a></li>
						</c:if>
					</ul>
					<c:if test="${sessionScop.user!=null}">
						welcome ${sessionScop.user.name}
					</c:if>
					<c:if test="${sessionScop.user==null}">
						<form class="navbar-form pull-right" onsubmit="login()" >
						<input id="name"  class="span2" type="text" placeholder="用户名"> 
						<input id="passwd" class="span2" type="password" placeholder="密码">
						<button type="submit" class="btn" id="loginBtn">登 录</button>
						</form>
					</c:if>
					
			</div>
		</div>
	</div>
	</body>
	<script type="text/javascript">
	$("li").click(function(){
		$("li.active").removeAttr("class");
		
		this.setAttribute("class","active");
		
	} );
	function login(){
		$("#loginBtn").addClass("disabled");
		$.ajax({url:"Index_login.action",async:false,
			data:{userName:$("#name").val(),password:$("#passwd").val()},
			type:"POST",
			dataType:"text",
		success:function(data){
			if(data=="success"){
				window.location.reload();
				$("#shopManage").click();
			}else if(data=="getVerrfyCode"){
				window.parent.location="index/loginPage.jsp";
			}
			$("#loginBtn").removeClass("disabled");
		}
		});
	}
	if($("#shopManage")!=null){
		$("#shopManage").click();
	}
	</script>