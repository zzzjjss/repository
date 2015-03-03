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
			<div class="panel panel-primary" style="margin-bottom:0px;height: 75px;text-align: right;background-image: url('../images/topBg.jpg')">
						<div class="btn-group" role="group" aria-label="Justified button group" >
							<a href="http://www.k1600.com/etf/silver.html" target="_blank"  class="btn btn-default" role="button" ><img src="../images/a.png" title="白银ETF"><br>白银ETF</a>
							<a href="http://www.k1600.com/etf/gold.html" target="_blank" class="btn btn-default" role="button" ><img src="../images/b.png" title="黄金ETF"><br>黄金ETF</a>
							<a href="http://www.k1600.com/rili" target="_blank"  class="btn btn-default" role="button"><img src="../images/c.png" title="财经日历"><br>财经日历</a>
							<a href="#" onclick="editUserInfo()" class="btn btn-default" role="button"><img src="../images/user.png"><br>${user.name}</a>
							<c:if test="${ user.role =='unknow'  }">
							<a href="../login.html"  class="btn btn-default" role="button"><img src="../images/logout.png"><br>登录</a>
							</c:if>
							<c:if test="${ user.role !='unknow'  }">
							<a href="#" onclick="logout()" class="btn btn-default" role="button"><img src="../images/logout.png"><br>退出</a>
							</c:if>
						</div>
						
			</div>
			
	</div>
		<div class="row">
			<div class="col-xs-3" style="padding-right:2px;padding-left:2px;" id="leftPart">
				
				<c:if test="${user.role=='commonUser' || user.role=='unknow'  }">
					<div class="panel panel-primary" style="margin-bottom: 1px;height: 300px;">
							<div class="panel-heading" style="background-image: url(../images/logo.jpg);height: 70px;"></div>
							<div class="panel-body" style="padding: 0px;">
								<div class="tabbable" style="padding-top: 2px;">
									<ul class="nav nav-tabs">
										<li class="active"><a href="#tab1" data-toggle="tab" style="font-weight: 900;">公告</a></li>
										<li><a href="#tab2" data-toggle="tab" style="font-weight: 900;">操作建议</a></li>
										<li><a href="#tab3" data-toggle="tab" style="font-weight: 900;">版权声明</a></li>
									</ul>
									<div class="tab-content">
										<div class="tab-pane active" id="tab1" style="padding: 15px">
											<p style="font-size: 12px;">
												投资有风险，入市需谨慎，选择正规平台，远离小平台：<br>
												1、国内平台较多，具备优资质的并不多，请确认是否具备政府批文，选择合法平台。<br>
												2、国内现货白银报价以国际价格为基础，综合中国人民银行人民币兑美元基准汇率，连续报出现货白银人民币买入价及卖出价。西北大宗直播室作为交流、学习平台，老师对行情分析及建议均用西北大宗行情软件报价，请您根据个人投资情况，理性分析，充分考虑。<br>
												3、请您理性分析，切记带好止损止盈，不骄不躁，把控风险。
											</p>
										</div>
										<div class="tab-pane" id="tab2" style="padding: 15px">
											<p style="font-size: 12px;">&nbsp;&nbsp;话费呢</p>
										</div>
										<div class="tab-pane" id="tab3" style="padding: 15px">
											<p style="font-size: 12px;">&nbsp;&nbsp;本直播室所有内容，包括文字、图像、音频、视频只供本公司或授权者使用，访问者可将本网站提供的内容或服务用于个人学习或欣赏，以及其他非商业性或非盈利性用途；没有本公司的书面授权，不得因任何目的，以任何方式如电子的、转载或其它方式，包括影印和记录，复制和传播本直播室的任何部分。</p>
										</div>
									</div>
								</div>
							</div>
					</div>

					<div class="panel panel-primary" style="margin-bottom: 1px;">
						<div class="panel-body" style="padding: 0px;">
							<div id='mediaspace'>This text will be replaced</div>
							<script type='text/javascript'>
								jwplayer('mediaspace').setup({
									'flashplayer' : 'player.swf',
									'file':"liveChannel",
									'streamer' : 'rtmp://${rtmpAddress}/livePlay?userName=jason&password=123456',
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
								"100%", "600px", "9.0.0",
								"assets/expressInstall.swf", flashvars, params,
								attributes);
						function getRtmpUrl() {
							return "rtmp://${rtmpAddress}/livePlay?userName=jason&password=123456";
						}
					</script>
				</c:if>
			</div>
			
			
			<div class="col-xs-7" style="padding-right:2px;padding-left:2px;">
				<div class="panel panel-primary">
					<div class="panel-body" id="chatContent" style="height: 600px;overflow: auto;background-image: url('../images/chatBg.png')"> 
						
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
				<div class="panel panel-primary" style="margin-bottom: 5px;">
					<div class="panel-heading" style="text-align: center;">
						在线用户&nbsp;&nbsp;<span class="badge" id="userCount">0</span>
					</div>
					<div class="panel-body" style="height: 550px;overflow: auto;padding: 0px;">
						 <table class="table table-hover" id="onLineUsers">
    						
  						</table>	
						
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-body" style="height: 250px;overflow: auto;padding: 0px;">
						<div class="btn-group btn-group-justified" role="group" aria-label="Justified button group" >
							<a href="#" onclick="vote('up')" class="btn btn-default" role="button" style="background-image:url('../images/up.png'); width: 65px;color: #ff0;text-align: right;">看涨<br><span id="upValue"></span></a>
							<a href="#" onclick="vote('equal')" class="btn btn-default" role="button" style="background-image:url('../images/eq.png'); width: 65px;color: #ff0;text-align: right;">盘整<br><span id="equalValue"></span></a>
							<a href="#" onclick="vote('down')" class="btn btn-default" role="button" style="background-image:url('../images/down.png'); width: 65px;color: #ff0;text-align: right;">看空<br><span id="downValue"></span></a>
						</div>
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
	var userRole="${user.role}";
	var ue;
	var websocket;
	$(document).ready(function(){
		ue=UE.getEditor('editor',{toolbars:[['snapscreen', 'wordimage','simpleupload','emotion']],elementPathEnabled:false,
			  enableAutoSave: false,maximumWords:20,enableAutoSave:false,saveInterval:5000000,enableContextMenu: false
			});
		if(!window.WebSocket){
			alert('您浏览器的版本太低，聊天室无法使用，请使用最新版本的浏览器！（推荐 -》http://www.firefox.com.cn/）');
		}
		websocket = new WebSocket("ws://${wsAddress}${context}/chat/"+sessionId); 
		websocket.onmessage=onMessageReceived;
		websocket.onclose=onWebSocketClosed;
		websocket.onopen=onWebSocketOpend;
		$("#userInfo").dialog({title:"Login", autoOpen: false});
		getVoteStatistic();
	});
	function updateUserCount(){
		 $("#userCount").text($("#onLineUsers tr").length);
	}
	function vote(str){
		var url="${context}/vote.do?vote="+str;
		$.post(url,function(result){
			if(result=="ok"){
				easyDialog.open({
					  container : {
					    header : '成功',
					    content : '投票成功'
					  }
					});
				getVoteStatistic();
			}
		});
	}
	
	function getVoteStatistic(){
		var url="${context}/getVoteResult.do";
		$.getJSON(url, function(json){
			   $("#upValue").text(json.up);
			   $("#downValue").text(json.down);
			   $("#equalValue").text(json.equal);
		});
		
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
				 var url="${context}/controller/saveUserInfo.do";
				 
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
		 if(userRole=="unknow"){
			 easyDialog.open({
				  container : {
				    header : '错误',
				    content : "不允许游客发言"
				  }
				});
			 return;
		 }
		 if(ue.getContentTxt().length>20){
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
       		var tmp=$.parseHTML(messageJson.message);
       		var textLength=$(tmp).text().length*20;
       		textLength=textLength+$(tmp).find("img").size()*60;
       		var panelPx=$("#chatContent").css("width");
       		var end=panelPx.indexOf("px");
       		var panelWidth=panelPx.substr(0,end);
       		if(textLength>(panelWidth-100)){
       			textLength=panelWidth-100;
       		}
           	if(messageJson.sender==userName){
           		$("#chatContent").append("<div style='display: flex'><img src='../images/speaker.png'><div>"+messageJson.sender+":&nbsp;&nbsp;&nbsp;&nbsp;<br><div class='panel panel-default' style='width:"+textLength+"px;padding:5px;margin-bottom: auto'>"+messageJson.message+"</div></div></div><br>");	
           	}else{
           		$("#chatContent").append("<div style='display: flex'><img src='../images/speaker.png'><div>"+messageJson.sender+":&nbsp;&nbsp;&nbsp;&nbsp;<br><div class='panel panel-default' style='width:"+textLength+"px;padding:5px;margin-bottom: auto'>"+messageJson.message+"</div></div></div><br>");
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
		  var url="${context}/controller/logout.do";
			$.post(url,function(result){
				if(result=="ok"){
					window.location.href="${context}/login.html";
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
