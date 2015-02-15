<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="../js/unit.js"></script>
<script type='text/javascript' src='jwplayer.js'></script>
<script type="text/javascript" src="../js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="../js/jquery.bootstrap.min.js"></script>
<script type="text/javascript" src="../js/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" charset="utf-8" src="../ueditor1_4_3/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="../ueditor1_4_3/ueditor.all.js"> </script>
<link href="../js/bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="../js/easydialog-v2.0/easydialog.min.js"></script>
<link href="../js/easydialog-v2.0/easydialog.css" type="text/css" rel="stylesheet">
<title>在线直播</title>
 <script type="text/javascript" src="assets/swfobject.js"></script>
</head>
<body>
	
	<div class="container-fluid">
	<div class="row" >
			<div class="panel panel-primary" style="margin-bottom:0px;background-color: #47BD7E;">
					<div class="panel-body" style="height: 50px;text-align: right;">
						欢迎 &nbsp;&nbsp;<a href="#" onclick="editUserInfo()">${user.name}</a>  &nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-primary btn-xs" onclick="logout()"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> 退出</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
			</div>
			
	</div>
		<div class="row">
			<div class="col-xs-3" style="padding-right:2px;padding-left:2px;" id="leftPart">
				
				<c:if test="${user.role=='commonUser'}">
					<div class="panel panel-primary" style="margin-bottom: 1px;">
						<div class="panel-heading">
							<h3 class="panel-title">公告</h3>
						</div>
						<div class="panel-body" style="height: 200px;">
							公告内容
						</div>
					</div>
					
					<div class="panel panel-primary" style="margin-bottom: 1px;">
						<div class="panel-body" style="padding: 0px;">
							<div id='mediaspace'>This text will be replaced</div>
							<script type='text/javascript'>
								jwplayer('mediaspace').setup({
									'flashplayer' : 'player.swf',
									'file':"liveChannel",
									'streamer' : 'rtmp://${ip}/livePlay?userName=jason&password=123456',
									'controlbar' : 'bottom',
									'width':'100%'
								});
							</script>
						</div>
					</div>
					
					<div class="panel panel-primary">
						<div class="panel-heading" style="background-image: url(../images/kefuzhongxin.png);height: 80px;">
						</div>
						<div class="panel-body" style="height: 100px;">
							<a class="btn btn-default  active" target="blank" href="http://wpa.qq.com/msgrd?v=1&amp;uin=2768117247&amp;site=tjdco.com&amp;menu=yes"><img alt="" src="../images/qq.jpg">&nbsp;&nbsp;小倩</a>
							<a class="btn btn-default  active" target="blank" href="http://wpa.qq.com/msgrd?v=1&amp;uin=2768117247&amp;site=tjdco.com&amp;menu=yes"><img alt="" src="../images/qq.jpg">&nbsp;&nbsp;小红</a>
							<a class="btn btn-default  active" target="blank" href="http://wpa.qq.com/msgrd?v=1&amp;uin=2768117247&amp;site=tjdco.com&amp;menu=yes"><img alt="" src="../images/qq.jpg">&nbsp;&nbsp;小明</a>
						</div>
					</div>
				</c:if>
				<c:if test="${user.role=='publisher'}">
					<div id="publishPart" >

					</div>
					<script type="text/javascript">
						var flashvars = {};
						var params = {
							allowfullscreen : "true"
						};
						var attributes = {};
						swfobject.embedSWF("publisher.swf", "publishPart",
								"450px", "600px", "9.0.0",
								"assets/expressInstall.swf", flashvars, params,
								attributes);
						function getRtmpUrl() {
							return "rtmp://${ip}/livePlay?userName=jason&password=123456";
						}
						$("#leftPart").css("z-index","10");
					</script>
				</c:if>
			</div>
			
			
			<div class="col-xs-7" style="padding-right:2px;padding-left:2px;">
				<div class="panel panel-primary">
					<div class="panel-body" id="chatContent" style="height: 600px;overflow: auto;"> 
						
					</div>
					<div class="panel-footer" id="chatInput" > 
						<script id="editor" type="text/plain" style="width:100%;height:150px;"></script>
						<button type="button" class="btn btn-default btn-lg" onclick="sendMsg()">
  								<span class="glyphicon glyphicon-send" aria-hidden="true"></span> 发送
						</button>
					</div>
				</div>
			</div>
			
			
			<div class="col-xs-2" style="padding-right:2px;padding-left:2px;">
				<div class="panel panel-primary">
					<div class="panel-heading" style="text-align: center;">
						用户列表&nbsp;&nbsp;<span class="badge" id="userCount">0</span>
					</div>
					<div class="panel-body" style="height: 550px;overflow: auto;padding: 0px;">
						 <table class="table table-hover" id="onLineUsers">
    						
  						</table>	
						
					</div>
				</div>
				
			</div>
		</div>
	</div>

	<div id="userInfo">
		<form id="loginform" role="form">
			<input type="hidden" id="userId" value="${user.id}">
			<div class="form-group">
				<label for="oldPassword">旧密码</label> <input type="password"
					class="form-control" id="oldPassword">
			</div>
			<div class="form-group">
				<label for="newPassword">新密码</label> <input type="password"
					class="form-control" id="newPassword">
			</div>
			<div class="form-group">
				<label for="phone">电话号码</label> <input type="text"
					class="form-control" id="phone" value="${user.phone}">
			</div>
		</form>
	</div>



	<script type="text/javascript">
	var userName="${user.name}";
	var sessionId="${sessionId}";
	var ue;
	var websocket;
	$(document).ready(function(){
		ue=UE.getEditor('editor',{toolbars:[['snapscreen', 'wordimage','simpleupload','emotion']],elementPathEnabled:false,
			  enableAutoSave: false,maximumWords:100,enableAutoSave:false,saveInterval:5000000,enableContextMenu: false
			});
		if(!window.WebSocket){
			alert('您浏览器的版本太低，聊天室无法使用，请使用最新版本的浏览器！（推荐 -》http://www.firefox.com.cn/）');
		}
		websocket = new WebSocket("ws://${ip}:8080/livePlayWeb/chat/"+sessionId); 
		websocket.onmessage=onMessageReceived;
		websocket.onclose=onWebSocketClosed;
		websocket.onopen=onWebSocketOpend;
		$("#userInfo").dialog({title:"Login", autoOpen: false});
	});
	function updateUserCount(){
		 $("#userCount").text($("#onLineUsers tr").length);
	}
	function editUserInfo(){
		$("#userInfo").dialog({title:"用户信息",buttons: [
			{text: "关闭",
			'class': "btn-primary",
			click: function() {
				$(this).dialog("close");
			}},
			{
			text: "保存",
			'class': "btn-success",
			 click: function() {
				 
				var oldPwd=$("#oldPassword").val();
				var newPwd=$("#newPassword").val();
				var phone=$("#phone").val();
				var userId=$("#userId").val();
				if(stringIsEmpty(oldPwd)||stringIsEmpty(newPwd)){
					easyDialog.open({
						  container : {
						    header : '错误',
						    content : '密码不能为空'
						  }
						});
					return;
				}
				var data="userId="+userId+"&oldPassword="+oldPwd+"&newPassword="+newPwd+"&phone="+phone;
				 var url="/livePlayWeb/controller/saveUserInfo.do";
				 
					$.post(url,data,function(result){
						if(result=="ok"){
							$("#userInfo").dialog("close");
						}else{
							easyDialog.open({
								  container : {
								    header : '错误',
								    content : result
								  }
								});
						}
					});
				
			}}
			]});
	}  

	
		  
	 function sendMsg(){
		 if(ue.getContentTxt().length>100){
			 easyDialog.open({
				  container : {
				    header : '错误',
				    content : "内容太多，无法发送！"
				  }
				});
			 return;
		 }
		 var contents=ue.getContent();
		 var reg1=new RegExp("\"","g");
		 contents=contents.replace(reg1,"'");
		 var reg2=new RegExp("<p>","g");
		 contents=contents.replace(reg2,"<p style='display: initial;'>");
		 var hello ='{"sender":"'+userName+'","messageType":"chat","message":"'+contents+'"}';
         websocket.send(hello); 
         ue.execCommand('cleardoc');
	 }
	     
	   //消息接收  
      function onMessageReceived(message) { 
       	var messageJson = JSON.parse(message.data);
       	if(messageJson.messageType=="chat"){
       		//if the sender  is  user self.
           	if(messageJson.sender==userName){
           		$("#chatContent").append("<div><span class='label label-primary'>"+messageJson.sender+":</span>&nbsp;&nbsp;&nbsp;&nbsp;"+messageJson.message+"</div></br>");	
           	}else{
           		$("#chatContent").append("<div><span class='label label-success'>"+messageJson.sender+":</span>&nbsp;&nbsp;&nbsp;&nbsp;"+messageJson.message+"</div></br>");
           	}
           	var div=document.getElementById("chatContent");
   		 	div.scrollTop=div.scrollHeight;
   		 	
       	}else if(messageJson.messageType=="online"){
       		if($("#onLineUsers #"+messageJson.userName).length==0){
       			$("#onLineUsers").append("<tr class='info' id='"+messageJson.userName+"'><td><span class='glyphicon glyphicon-user' aria-hidden='true'></span>&nbsp;&nbsp;&nbsp;&nbsp;"+messageJson.userName+"</td></tr>");	
       		}
       		updateUserCount();
       	}else if(messageJson.messageType=="offline"){
       		$("#onLineUsers #"+messageJson.userName).remove();
       		updateUserCount();
       	}else if(messageJson.messageType=="onlineUsers"){
       		var onlineUsers=messageJson.userNames;
       		for(var i=0;i<onlineUsers.length;i++){
       			if(onlineUsers[i]==userName){
       				$("#onLineUsers").append("<tr class='success' id='"+onlineUsers[i]+"'><td><span class='glyphicon glyphicon-user' aria-hidden='true'></span>&nbsp;&nbsp;&nbsp;&nbsp;"+onlineUsers[i]+"</td></tr>");
       			}else{
       				$("#onLineUsers").append("<tr class='info' id='"+onlineUsers[i]+"'><td><span class='glyphicon glyphicon-user' aria-hidden='true'></span>&nbsp;&nbsp;&nbsp;&nbsp;"+onlineUsers[i]+"</td></tr>");	
       			}
       			
       		}
       		updateUserCount();
       	}
		  
     }  
	 function onWebSocketOpend(evt){
		
	 }  
	   
	   
	 function onWebSocketClosed(message){
		 console.log('Client notified socket has closed',message); 
	 }
	   
	  function logout(){
		  websocket.close();
		  var url="/livePlayWeb/controller/logout.do";
			$.post(url,function(result){
				if(result=="ok"){
					window.location.href="login.html";
				}else{
					easyDialog.open({
						  container : {
						    header : '错误',
						    content : result
						  }
						});
				}
			});
	  }
</script>
</body>

</html>
