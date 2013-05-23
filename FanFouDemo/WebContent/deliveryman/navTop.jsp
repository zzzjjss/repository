
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
					<span class="icon-bar"></span> <span class="icon-bar"></span>
					 <span class="icon-bar"></span>
				</button>
				
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li><a style="font-size:30px">FanFan</a></li>
					</ul>
					<form class="navbar-form pull-right">
						<input class="span2" type="text" placeholder="用户名"> <input
							class="span2" type="password" placeholder="密码">
						<button type="submit" class="btn">登 录</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	