
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

</head>	
<body>
<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
					<ul class="nav">
						<li><a style="font-size:30px">FanFan</a></li>
						<li  id="platformManage" class="active"><a href="navLeftPlatformManage.jsp" target="navLeft">平台管理</a></li>
						<li  id="shopManage"><a href="navLeftShopManage.jsp" target="navLeft">餐馆管理</a></li>
					</ul>
					<form class="navbar-form pull-right">
						<input class="span2" type="text" placeholder="用户名"> <input
							class="span2" type="password" placeholder="密码">
						<button type="submit" class="btn">登 录</button>
					</form>
			</div>
		</div>
	</div>
	</body>
	<script type="text/javascript">
	$("li").click(function(){
		$("li.active").removeAttr("class");
		
		this.setAttribute("class","active");
		
	} );
	
	
	</script>