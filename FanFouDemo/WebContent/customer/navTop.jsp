
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>FanFou</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="../bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<link href="../bootstrap/css/docs.css" rel="stylesheet">
<link href="../bootstrap/css/prettify.css" rel="stylesheet">
<script src="../js/jquery.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
<style>
body {
	padding-top: 60px;
	padding-bottom: 40px;
	/* 60px to make the container go all the way to the bottom of the topbar */
}
</style>
</head>	
<body>
<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<button type="button" class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li><a style="font-size:30px">FanFan</a></li>
						<li  id="index"><a href="index.jsp">本期菜谱</a></li>
						<li id="companyInfo"><a href="companyInfo.jsp">公司简介</a></li>
						<li id="myHome"><a href="myHome.jsp" >我的饭饭</a></li>
						
					</ul>
					<form class="navbar-form pull-right">
						<input class="span2" type="text" placeholder="用户名"> <input
							class="span2" type="password" placeholder="密码">
						<button type="submit" class="btn">登 录</button>
					</form>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
	