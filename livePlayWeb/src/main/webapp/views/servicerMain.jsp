<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${context}/js/unit.js"></script>
<script type="text/javascript" src="${context}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${context}/js/jquery.bootstrap.min.js"></script>
<script type="text/javascript" src="${context}/js/bootstrap/js/bootstrap.js"></script>
<link href="${context}/js/bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${context}/js/easydialog-v2.0/easydialog.min.js"></script>
<link href="${context}/js/easydialog-v2.0/easydialog.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${context}/js/socket.io-1.3.4.js"></script>
<script type="text/javascript" src="${context}/js/jquery.dataTables.min.js"></script>
<link href="${context}/css/jquery.dataTables.min.css" type="text/css" rel="stylesheet">
<title>西北大宗直播室客服</title>
 
</head>
<body>
	<!--[if lte IE 7 ]>    
	
	<div class="container-fluid">
		&nbsp;IE浏览器版本太低，请升级IE到版本8，或者使用火狐浏览器！下载地址：<a href="http://www.firefox.com.cn/" target="blank">http://www.firefox.com.cn/</a>
	</div>
	
	
 <![endif]-->
 <!--[if (gt IE 7)|!(IE)]><!-->
	<div class="container-fluid">
	<div class="row" >
			<div class="panel panel-primary" style="margin-bottom:0px;height: 75px;text-align: right;background-image: url('${context}/images/topBg.jpg')">
						<div class="btn-group" role="group" aria-label="Justified button group" >
							<a href="#" onclick="editUserInfo()" class="btn btn-default" role="button"><img src="${context}/images/user.png"><br>${servicer.name}</a>
							<a href="#" onclick="logout()" class="btn btn-default" role="button"><img src="${context}/images/logout.png"><br>退出</a>
						</div>
						
			</div>
			
	</div>
		<div class="row">
			
			<div class="col-xs-4" style="padding-right:2px;padding-left:2px;">
				<div class="panel panel-primary">
					<div class="panel-body" id="chatContent" style="height: 600px;overflow: auto;background-image: url('${context}/images/chatBg.png')"> 
						
					</div>
					
				</div>
				<div class="panel panel-primary" style="margin-bottom: 5px;">
						<div class="panel-heading" style="text-align: center;">
							在线用户&nbsp;&nbsp;<span class="badge" id="userCount">0</span>
						</div>
						<div class="panel-body" style="height: 150px;overflow: auto;padding: 0px;">
							 <table class="table table-hover" id="onLineUsers">
	    						
	  						</table>	
							
						</div>
					</div>
			</div>
			
			<div class="col-xs-8" style="padding-right:2px;padding-left:2px;">
				<div class="panel panel-default" style="margin-bottom: 5px;">
					<div class="panel-heading" style="text-align: right;margin-bottom:5px; ">
						<button class="btn btn-success"  onclick="addUser()" >注册新用户</button>
					</div>
					<div class="panel-body" style="height: 800px;overflow: auto;padding: 0px;">
								
								
								<table class="table table-condensed table-hover" id="userTable">
									<thead>
										<tr >
											<th style="text-align: left;">用户名</th>
											<th style="text-align: left;">电话</th>
											<th style="text-align: left;">创建时间</th>
											<th style="text-align: left;"></th>
										</tr>
									</thead>
									<tbody>
									</tbody>
							</table>				
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="userInfo">
		<form id="loginform" role="form">
			<input type="hidden" id="userId" value="${servicer.id}">
			<div class="form-group">
				<label for="oldPassword">旧密码</label> <input type="password"
					class="form-control" id="oldPassword">
			</div>
			<div class="form-group">
				<label for="newPassword">新密码</label> <input type="password"
					class="form-control" id="newPassword">
			</div>
		</form>
	</div>
	
	
	<div id="addCommonUser">
		<form id="loginform" role="form">
			<div class="form-group">
				<label for="userName">用户名</label> <input type="text"
					class="form-control" id="userName">
			</div>
			<div class="form-group">
				<label for="phone">手机号</label> <input type="text"
					class="form-control" id="phone">
			</div>
		</form>
	</div>

	<script type="text/javascript">
	var userName="${servicer.name}";
	var sessionId="${sessionId}";
	var socket;
	$(document).ready(function(){
		
		socket = io.connect("http://${socketIoAddress}?sessionId=no",{'reconnection delay' : 200000000000});
		
		socket.on('chatMessageEvent', function(data) {
			onChatMessage(data);
	  	});	

		socket.on('allOnLineUserEvent', function(data) {
			allOnlineUserMessage(data);
		 });
		socket.on('userOffLineEvent', function(data) {
			//userOfflineMessage(data);
		 });
		socket.on('userOnLineEvent', function(data) {
			//userOnlineMessage(data);
		 });
		$("#userInfo").dialog({title:"Login", autoOpen: false});
		$("#addCommonUser").dialog({title:"Add", autoOpen: false});
		loadAllUser();
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
				var data="userId="+userId+"&oldPassword="+oldPwd+"&newPassword="+newPwd;
				 var url="${context}/servicer/control/saveServicerInfo.do";
				 
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
	function onChatMessage(messageJson){
		var tmp=messageJson.message;
   		var textLength=$(tmp).text().length*20;
   		textLength=textLength+$(tmp).find("img").size()*60;
   		var panelPx=$("#chatContent").css("width");
   		var end=panelPx.indexOf("px");
   		var panelWidth=panelPx.substr(0,end);
   		if(textLength>(panelWidth-100)){
   			textLength=panelWidth-100;
   		}
   		var keepMessage=300;
   		if($("#chatContent").children("div").size()>=keepMessage){
   			$("#chatContent").children(":lt(2)").remove();
   		}
       	if(messageJson.sender==userName){
       		$("#chatContent").append("<div style='display: flex'><img src='${context}/images/speaker.png'><div>"+messageJson.sender+":&nbsp;&nbsp;&nbsp;&nbsp;<br><div class='panel panel-default' style='width:"+textLength+"px;padding:5px;margin-bottom: auto'>"+messageJson.message+"</div></div></div><br>");	
       	}else{
       		$("#chatContent").append("<div style='display: flex'><img src='${context}/images/speaker.png'><div>"+messageJson.sender+":&nbsp;&nbsp;&nbsp;&nbsp;<br><div class='panel panel-default' style='width:"+textLength+"px;padding:5px;margin-bottom: auto'>"+messageJson.message+"</div></div></div><br>");
       	}
       	var div=document.getElementById("chatContent");
	 	div.scrollTop=div.scrollHeight;
	 }   
	 function userOnlineMessage(messageJson){
		 if($("#onLineUsers #"+messageJson.userName).length==0){
    			$("#onLineUsers").append("<tr class='info' id='"+messageJson.userName+"'><td><span class='glyphicon glyphicon-user' aria-hidden='true'></span>&nbsp;&nbsp;&nbsp;&nbsp;"+messageJson.userName+"</td></tr>");	
    		}
    		updateUserCount();
	 }
	 function userOfflineMessage(message){
		 $("#onLineUsers #"+message.userName).remove();
    		updateUserCount();
	 }
	 function allOnlineUserMessage(message){
		 var onlineUsers=message.userNames;
		$("#onLineUsers").empty();
   		for(var i=0;i<onlineUsers.length;i++){
			if(onlineUsers[i]==userName){
   				$("#onLineUsers").append("<tr class='success' id='"+onlineUsers[i]+"'><td><span class='glyphicon glyphicon-user' aria-hidden='true'></span>&nbsp;&nbsp;&nbsp;&nbsp;"+onlineUsers[i]+"</td></tr>");
   			}else{
   				$("#onLineUsers").append("<tr class='info' id='"+onlineUsers[i]+"'><td><span class='glyphicon glyphicon-user' aria-hidden='true'></span>&nbsp;&nbsp;&nbsp;&nbsp;"+onlineUsers[i]+"</td></tr>");	
   			}	
   		}
   		
   		$("#userCount").text(onlineUsers.length);
	 }
	
	   
	  function logout(){
		  socket.close();
		  var url="${context}/servicer/control/logout.do";
			$.post(url,function(result){
				if(result=="ok"){
					window.location.href="${context}/servicerLogin.html";
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
	  
	  
	  function deleteUser(userId){
			var confirmRes=confirm("Are you sure  delete the user?");
			if(!confirmRes){
				return;
			}
			var url="{{ path('/admin/deleteUser') }}";
			$.ajax
			(
				{
					type: "POST",
					url: url+"?userId="+userId,
					cache: false,
					dataType: "text",
					success: 
						function(result)
						{   
							if(result=="ok"){
								getUserContent();
							}else{
								alert(result);
							}
										
						},
					error: 
						function(jqXHR, textStatus, errorThrown )
						{
							alert(errorThrown); 
						}
				}
			);
		}
		
		
		
		function loadAllUser(){
			var url="${context}/servicer/control/getAllUser.do";
	    	$('#userTable').DataTable({
			    ajax: {
			        url: url,
			        type: 'POST'
			    },
			    "destroy": true,
				"columns": [
					{data:"name"},
					{data:"phone"},
					{data:"createTime"},
					{data:"id",render:function(data, type, row, meta){ return buildOperationgCell(data);}}
				],
				"rowCallback":function(row, data){
					$(row).dblclick(function(){
						alert("clicke");
					});
				},
				"columnDefs": [
	            {
	                "targets": [ 3 ],
	                "orderable": false,
	                "searchable": false
	            }
	        ]        		
		   	} );
		}
	function addUser(){
		$("#addCommonUser").dialog({title:"注册用户",
			buttons: [
         			{text: "关闭",
         			'class': "btn-primary",
         			click: function() {
         				$(this).dialog("close");
         			}},
         			{
         			text: "注册",
         			'class': "btn-success",
         			 click: function() {
         				 
         				var userName=$("#userName").val();
         				var phone=$("#phone").val();
         				if(stringIsEmpty(userName)||stringIsEmpty(phone)){
         					easyDialog.open({
         						  container : {
         						    header : '错误',
         						    content : '用户名或手机号不能为空'
         						  }
         						});
         					return;
         				}
         				var data="userName="+userName+"&phone="+phone;
         				var url="${context}/servicer/control/registNewCommonUser.do";
         				 
         					$.post(url,data,function(result){
         						if(result=="ok"){
         							$("#addCommonUser").dialog("close");
         							loadAllUser();
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
	
	function buildOperationgCell(userId){
		return '<a class="btn" onclick="shutup('+userId+')"><span data-toggle="tooltip" data-placement="right" title="禁言" class="glyphicon glyphicon-volume-off" ></span></a>';
	}
	function shutup(userId){
		
		var url="${context}/servicer/control/shutupUserMouth.do";
		$.ajax
		(
			{
				type: "POST",
				url: url+"?userId="+userId,
				cache: false,
				dataType: "text",
				success: 
					function(result)
					{   
						if(result=="ok"){
							alert("禁言成功！");	
						}else{
							alert(result);
						}
									
					},
				error: 
					function(jqXHR, textStatus, errorThrown )
					{
						alert(errorThrown); 
					}
			}
		);
	}
	
</script>
<!--<![endif]-->
</body>

</html>
