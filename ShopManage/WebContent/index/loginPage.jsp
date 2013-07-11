<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form class="navbar-form pull-right" onsubmit="login()" >
						<input id="name" class="span2" type="text" placeholder="用户名"> 
						<input id="passwd" class="span2" type="password" placeholder="密码">
						<input id="verifyCode" class="span2" type="text" placeholder="验证码">
						<img  src="Index_generateVerifyCode">
						<button type="submit" class="btn" id="loginBtn">登 录</button>
					</form>
					
					
</body>
<script type="text/javascript">


</script>
</html>