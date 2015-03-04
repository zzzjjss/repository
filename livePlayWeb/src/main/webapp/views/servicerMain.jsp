<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${context}/js/unit.js"></script>
<script type="text/javascript" src="${context}/js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="${context}/js/jquery.bootstrap.min.js"></script>
<script type="text/javascript" src="${context}/js/bootstrap/js/bootstrap.js"></script>
<link href="${context}/js/bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${context}/js/easydialog-v2.0/easydialog.min.js"></script>
<link href="${context}/js/easydialog-v2.0/easydialog.css" type="text/css" rel="stylesheet">

<script type="text/javascript" src="${context}/js/jquery.dataTables.min.js"></script>
<link href="${context}/css/jquery.dataTables.min.css" type="text/css" rel="stylesheet">
<title>西北大宗直播室客服</title>
 
</head>
<body>
	
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
				<div class="panel panel-primary" style="margin-bottom: 5px;">
					<div class="panel-heading" style="text-align: center;">
						客户
					</div>
					<div class="panel-body" style="height: 550px;overflow: auto;padding: 0px;">
								<table class="table table-condensed table-hover" id="userTable">
									<thead>
										<tr >
											<th style="text-align: center;">User ID</th>
											<th style="text-align: center;">User Name</th>
											<th style="text-align: center;">Phone</th>
											<th style="text-align: center;"></th>
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

	<script type="text/javascript">
	var userName="${servicer.name}";
	var sessionId="${sessionId}";
	var websocket;
	$(document).ready(function(){
		if(!window.WebSocket){
			alert('您浏览器的版本太低，聊天室无法使用，请使用最新版本的浏览器！（推荐 -》http://www.firefox.com.cn/）');
		}
		websocket = new WebSocket("ws://${wsAddress}${context}/chat/no"); 
		websocket.onmessage=onMessageReceived;
		websocket.onclose=onWebSocketClosed;
		websocket.onopen=onWebSocketOpend;
		$("#userInfo").dialog({title:"Login", autoOpen: false});
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

	
		  
	
	     
	   //消息接收  
      function onMessageReceived(message) { 
       	var messageJson = JSON.parse(message.data);
       	if(messageJson.messageType=="chat"){
       		var tmp=$.parseHTML(messageJson.message);
       		var textLength=$(tmp).text().length*20;
       		textLength=textLength+$(tmp).find("img").size()*60;
       		var panelPx=$("#chatContent").css("width");
       		var end=panelPx.indexOf("px");
       		var panelWidth=panelPx.substr(0,end);
       		if(textLength>(panelWidth-100)){
       			textLength=panelWidth-100;
       		}
           
           	$("#chatContent").append("<div style='display: flex'><img src='${context}/images/speaker.png'><div>"+messageJson.sender+":&nbsp;&nbsp;&nbsp;&nbsp;<br><div class='panel panel-default' style='width:"+textLength+"px;padding:5px;margin-bottom: auto'>"+messageJson.message+"</div></div></div><br>");
           	
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
       	}else  if(messageJson.messageType=="onlineUsers"){
       		var onlineUsers=messageJson.userNames;
       		for(var i=0;i<onlineUsers.length;i++){
       			$("#onLineUsers").append("<tr class='info' id='"+onlineUsers[i]+"'><td><span class='glyphicon glyphicon-user' aria-hidden='true'></span>&nbsp;&nbsp;&nbsp;&nbsp;"+onlineUsers[i]+"</td></tr>");	
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
					{data:"id",render:function(data, type, row, meta){ return buildOperationgCell(data);}}
				],
				"rowCallback":function(row, data){
					$(row).dblclick(function(){
						alert("clicke");
					});
				},
				"columnDefs": [
	            {
	                "targets": [ 2 ],
	                "orderable": false,
	                "searchable": false
	            }
	        ]        		
		   	} );
		}
	function addUser(){
			$("#userEditContent").load("{{path('/admin/editUser')}}");
			$('#userEditModel').modal();
	}
	function buildOperationgCell(userId){
		return '<a class="btn" onclick="deleteUser('+userId+')"><span data-toggle="tooltip" data-placement="right" title="Delete" class="glyphicon glyphicon-remove pfTip" ></span></a>';
	}
	  
	  
</script>
</body>

</html>
