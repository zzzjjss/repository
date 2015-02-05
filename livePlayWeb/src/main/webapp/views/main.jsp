<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<script type='text/javascript' src='jwplayer.js'></script>
<script type="text/javascript" src="../js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="../js/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" charset="utf-8" src="../ueditor1_4_3/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="../ueditor1_4_3/ueditor.all.min.js"> </script>
<link href="../js/bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet">
<title>在线直播</title>
 
</head>
<body>
	
	<div class="container-fluid">
	<div class="row" >
			<div class="panel panel-primary" style="margin-bottom:0px;background-color: #47BD7E;">
					<div class="panel-body" style="height: 50px;text-align: right;">
						欢迎 ${user.name} &nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-primary btn-xs" onclick="logout()"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> 退出</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
			</div>
			
	</div>
		<div class="row">
			<div class="col-xs-3" style="padding-right:2px;padding-left:2px;">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">公告</h3>
					</div>
					<div class="panel-body" style="height: 200px;">
						公告内容
					</div>
				</div>
				
				<div class="panel panel-primary">
					<div class="panel-body" style="padding: 0px;">
						<div id='mediaspace'>This text will be replaced</div>
						<script type='text/javascript'>
							jwplayer('mediaspace').setup({
								'flashplayer' : 'player.swf',
								'file':"liveChannel",
								'streamer' : 'rtmp://localhost/livePlay?userName=jason&password=123456',
								'controlbar' : 'bottom',
								'width':'100%'
							});
						</script>
					</div>
				</div>
				
				<div class="panel panel-primary">
					<div class="panel-heading" style="background-image: url(../images/kefuzhongxin.png);height: 80px;">
					</div>
					<div class="panel-body">
						<ul>
						
							<li><a target="blank" href="http://wpa.qq.com/msgrd?v=1&amp;uin=56167649&amp;site=tjdco.com&amp;menu=yes">葡萄</a></li>						
						</ul>
					</div>
				</div>
				
				
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
					<div class="panel-heading">
						用户列表
					</div>
					<div class="panel-body" style="height: 550px;">
						<ul id="onLineUsers">
								
						</ul>
					</div>
				</div>
				
			</div>
		</div>
	</div>

<script type="text/javascript">
	var userName="${user.name}";
	var sessionId="${sessionId}";
	var ue;
	var websocket;
	$(document).ready(function(){
		ue=UE.getEditor('editor',{toolbars:[['snapscreen', 'wordimage','simpleupload','emotion']],elementPathEnabled:false,
			  enableAutoSave: false,maximumWords:100
			});
		websocket = new WebSocket("ws://localhost:8080/livePlayWeb/chat/"+sessionId); 
		websocket.onmessage=onMessageReceived;
		websocket.onclose=onWebSocketClosed;
		websocket.onopen=onWebSocketOpend;
	});
  
  function keyDown(e) {
	    var currKey=0,e=e||event;
	    currKey=e.keyCode||e.which||e.charCode;
	    //ctrl+enter提交
	    if((currKey==13)&&(e.ctrlKey)){
	      sendMsg();
	    }
	    //与火狐浏览器快捷键冲突
	    if((currKey==83)&&(e.altKey)){
	      sendMsg();
	      }
	}
	//监听键盘按下事件
	document.onkeydown = keyDown;  
	
	 function sendMsg(){
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
       			$("#onLineUsers").append("<li id='"+messageJson.userName+"'>"+messageJson.userName+"</li>");	
       		}
       	}else if(messageJson.messageType=="offline"){
       		$("#onLineUsers #"+messageJson.userName).remove();
       	}else if(messageJson.messageType=="onlineUsers"){
       		var onlineUsers=messageJson.userNames;
       		for(var i=0;i<onlineUsers.length;i++){
       			$("#onLineUsers").append("<li id='"+onlineUsers[i]+"'>"+onlineUsers[i]+"</li>");
       		}
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
