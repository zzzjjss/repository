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
						<img  src="Index_generateVerifyCode.action" onclick="getVerifyCode()" id="verifyCodeImg">
						<button type="submit" class="btn" id="loginBtn">登 录</button>
					</form>
					
					
</body>
<script type="text/javascript">
function getVerifyCode(){
	$("#verifyCodeImg").attr("src","Index_generateVerifyCode.action");
}
function login(){
	$("#loginBtn").addClass("disabled");
	$.ajax({url:"Index_login.action",async:false,
		data:{userName:$("#name").val(),password:$("#passwd").val()},
		type:"POST",
		dataType:"text",
	success:function(data){
		if(data=="success"){
			window.location.href="index.jsp";
		}else {
			$("#verifyCodeImg").attr("src","Index_generateVerifyCode.action");
		}
		$("#loginBtn").removeClass("disabled");
	}
	});
}
</script>
</html>