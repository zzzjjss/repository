<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${context}/js/unit.js"></script>
<script type='text/javascript' src='jwplayer.js'></script>
<script type="text/javascript" src="${context}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${context}/js/jquery.bootstrap.min.js"></script>
<script type="text/javascript" src="${context}/js/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="${context}/views/assets/swfobject.js"></script>
<script type="text/javascript" charset="utf-8" src="${context}/ueditor1_4_3/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${context}/ueditor1_4_3/ueditor.all.js"> </script>
<link href="${context}/js/bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${context}/js/easydialog-v2.0/easydialog.min.js"></script>
<script type="text/javascript" src="${context}/js/socket.io-1.3.4.js"></script>
<script type="text/javascript" src="${context}/js/html5.js"></script>

<link href="${context}/js/easydialog-v2.0/easydialog.css" type="text/css" rel="stylesheet">
<title>西北大宗直播室</title>
<style type="text/css">
.qqLink {margin-top:30px;}

</style>
</head>
<body onUnload="myClose()">
<!--[if lte IE 8 ]>    
	
	<div class="container-fluid">
		&nbsp;IE浏览器版本太低，请升级IE到版本9，或者使用火狐浏览器！下载地址：<a href="http://www.firefox.com.cn/" target="blank">http://www.firefox.com.cn/</a>
	</div>
	
	
 <![endif]-->
 <!--[if (gt IE 8)|!(IE)]><!-->
	<div class="container-fluid">
	<div class="row" style="background-image: url('${context}/images/topBg.jpg');margin-bottom:0px;height: 75px;">
				<div class="col-xs-6" style="text-align: left;height: 70px;">
						<a class="btn btn-default  active qqLink" target="blank" href="http://wpa.qq.com/msgrd?v=1&amp;uin=2851710057&amp;site=tjdco.com&amp;menu=yes"><img alt="" src="${context}/images/qq.jpg">&nbsp;&nbsp;客服1</a>
						<a class="btn btn-default  active qqLink" target="blank" href="http://wpa.qq.com/msgrd?v=1&amp;uin=285171005&amp;site=tjdco.com&amp;menu=yes"><img alt="" src="${context}/images/qq.jpg">&nbsp;&nbsp;客服2</a>
						<a class="btn btn-default  active qqLink" target="blank" href="http://wpa.qq.com/msgrd?v=1&amp;uin=2851710059&amp;site=tjdco.com&amp;menu=yes"><img alt="" src="${context}/images/qq.jpg">&nbsp;&nbsp;客服3</a>
						<a class="btn btn-default  active qqLink" target="blank" href="http://wpa.qq.com/msgrd?v=1&amp;uin=2851710060&amp;site=tjdco.com&amp;menu=yes"><img alt="" src="${context}/images/qq.jpg">&nbsp;&nbsp;客服4</a>
						<a class="btn btn-default  active qqLink" target="blank" href="http://wpa.qq.com/msgrd?v=1&amp;uin=2851710068&amp;site=tjdco.com&amp;menu=yes"><img alt="" src="${context}/images/qq.jpg">&nbsp;&nbsp;客服5</a>
						<a class="btn btn-default  active qqLink" target="blank" href="http://wpa.qq.com/msgrd?v=1&amp;uin=2851710069&amp;site=tjdco.com&amp;menu=yes"><img alt="" src="${context}/images/qq.jpg">&nbsp;&nbsp;客服6</a>
						<a class="btn btn-default  active qqLink" target="blank" href="http://wpa.qq.com/msgrd?v=1&amp;uin=2851710070&amp;site=tjdco.com&amp;menu=yes"><img alt="" src="${context}/images/qq.jpg">&nbsp;&nbsp;客服7</a>
						<a class="btn btn-default  active qqLink" target="blank" href="http://wpa.qq.com/msgrd?v=1&amp;uin=2851710075&amp;site=tjdco.com&amp;menu=yes"><img alt="" src="${context}/images/qq.jpg">&nbsp;&nbsp;客服8</a>
						<a class="btn btn-default  active qqLink" target="blank" href="http://wpa.qq.com/msgrd?v=1&amp;uin=2851710076&amp;site=tjdco.com&amp;menu=yes"><img alt="" src="${context}/images/qq.jpg">&nbsp;&nbsp;客服9</a>
						<a class="btn btn-default  active qqLink" target="blank" href="http://wpa.qq.com/msgrd?v=1&amp;uin=2851710077&amp;site=tjdco.com&amp;menu=yes"><img alt="" src="${context}/images/qq.jpg">&nbsp;&nbsp;客服0</a>
					</div>
					
						<div class="col-xs-6" role="group" aria-label="Justified button group" style="text-align: right;">
							<a href="http://www.k1600.com/etf/silver.html" target="_blank"  class="btn btn-default" role="button" ><img src="${context}/images/a.png" title="白银ETF"><br>白银ETF</a>
							<a href="http://www.k1600.com/etf/gold.html" target="_blank" class="btn btn-default" role="button" ><img src="${context}/images/b.png" title="黄金ETF"><br>黄金ETF</a>
							<a href="http://www.k1600.com/rili" target="_blank"  class="btn btn-default" role="button"><img src="${context}/images/c.png" title="财经日历"><br>财经日历</a>
							<a href="#" onclick="editUserInfo()" class="btn btn-default" role="button"><img src="${context}/images/user.png"><br>${user.name}</a>
							<c:if test="${ user.role =='unknow'  }">
							<a href="${context}/login.html"  class="btn btn-default" role="button"><img src="${context}/images/logout.png"><br>登录</a>
							</c:if>
							<c:if test="${ user.role !='unknow'  }">
							<a href="#" onclick="logout()" class="btn btn-default" role="button"><img src="${context}/images/logout.png"><br>退出</a>
							</c:if>
						</div>
						
			
	</div>
		<div class="row">
			<div class="col-xs-5" style="padding-right:2px;padding-left:2px;" id="leftPart">
						<div class="panel panel-primary" style="margin-bottom: 1px;height: 300px;">
							<div class="panel-heading" style="background-image: url(${context}/images/logo.jpg);height: 70px;"></div>
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
											<p style="font-size: 12px;">&nbsp;&nbsp;更多操作建议，请联系直播室QQ客服！</p>
										</div>
										<div class="tab-pane" id="tab3" style="padding: 15px">
											<p style="font-size: 12px;">&nbsp;&nbsp;本直播室所有内容，包括文字、图像、音频、视频只供本公司或授权者使用，访问者可将本网站提供的内容或服务用于个人学习或欣赏，以及其他非商业性或非盈利性用途；没有本公司的书面授权，不得因任何目的，以任何方式如电子的、转载或其它方式，包括影印和记录，复制和传播本直播室的任何部分。</p>
										</div>
									</div>
								</div>
							</div>
					</div>

					<div class="panel panel-primary" style="margin-bottom: 1px;">
						<div class="panel-body" style="padding: 0px;" id="liveVideo">
							
						</div>
					</div>
					
					
				
				
			</div>
			
			
			<div class="col-xs-5" style="padding-right:2px;padding-left:2px;">
				<div class="panel panel-primary">
					<div class="panel-body" id="chatContent" style="height: 550px;overflow: auto;background-image: url('${context}/images/chatBg.png')"> 
						
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
						游客&nbsp;&nbsp;<span class="badge" id="unknowCount">0</span>
					</div>
					<div class="panel-body" style="height: 550px;overflow: auto;padding: 0px;">
						 <table class="table table-hover" id="onLineUsers">
    						
  						</table>	
						
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-body" style="height: 220px;overflow: auto;padding: 0px;">
						<div class="btn-group btn-group-justified" role="group" aria-label="Justified button group" >
							<a href="#" onclick="vote('up')" class="btn btn-default" role="button" style="background-image:url('${context}/images/up.png'); width: 65px;color: #ff0;text-align: right;">看涨<br><span id="upValue"></span></a>
							<a href="#" onclick="vote('equal')" class="btn btn-default" role="button" style="background-image:url('${context}/images/eq.png'); width: 65px;color: #ff0;text-align: right;">盘整<br><span id="equalValue"></span></a>
							<a href="#" onclick="vote('down')" class="btn btn-default" role="button" style="background-image:url('${context}/images/down.png'); width: 65px;color: #ff0;text-align: right;">看空<br><span id="downValue"></span></a>
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
		</form>
	</div>

           
	<script type="text/javascript">
	var userName="${user.name}";
	var sessionId="${sessionId}";
	var userRole="${user.role}";
	var listenMinute="{listenMinute}";
	var ue;
	var socket;
	var isShutup=false;;
	$(document).ready(function(){
		 if (navigator.userAgent.match(/iP(od|hone|ad)/i) || navigator.userAgent.match(/Android/i)) {
      	   $("#liveVideo").append("<video id='i7b70ijc' width='100%' height='500' poster='http://live.polyv.net/images/cover_image.jpg' src='http://rlive.videocc.net/record//i7b70ijc/playlist.m3u8'  preload controls></video>");
         } else {
      	   $("#liveVideo").append("<object classid='clsid:d27cdb6e-ae6d-11cf-96b8-444553540000' width='100%' height='500' id='006ef93f-8d4e-4ad9-8f12-39f573f13f7a'><param name='movie' value='http://liveplayer.polyv.net/player/i7b6vfpw/i7b70ijc.swf'/><param name='allowscriptaccess' value='always' /><param name='allowfullscreen' value='true' /><embed src='http://liveplayer.polyv.net/player/i7b6vfpw/i7b70ijc.swf' width='100%' height='500'  type='application/x-shockwave-flash' allowscriptaccess='always' name='006ef93f-8d4e-4ad9-8f12-39f573f13f7a' allowfullscreen='true' /></embed></object>");
         }
		ue=UE.getEditor('editor',{toolbars:[['snapscreen', 'wordimage','simpleupload','emotion']],elementPathEnabled:false,
			  enableAutoSave: false,maximumWords:20,enableAutoSave:false,saveInterval:5000000,enableContextMenu: false
			});
		if(userRole=="unknow"){
			sessionId="unknow";
		}
		socket = io.connect("http://${socketIoAddress}?sessionId="+sessionId,{'reconnection delay' : 200000000000});
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
		socket.on('shutupEvent', function(data) {
			onShutupMessage(data);
	  	});
		socket.on('allUnknowCountEvent', function(data) {
			allUnknowCount(data);
	  	});
		$("#userInfo").dialog({title:"Login", autoOpen: false});
		getVoteStatistic();
		if(userRole=="unknow"){
			window.setInterval(function(){
				var url="${context}/controller/isIpCanListen.do";
				$.post(url,function(result){
					if(result=="false"){
						window.location.href="${context}/views/popupQqRegine.do";
					}
				});
			},60*1000);
		}
	});
	
	
	function updateUserCount(){
		 $("#userCount").text($("#onLineUsers tr").length);
	}
	function vote(str){
		if(userRole=="unknow"){
			easyDialog.open({
				  container : {
				    header : '失败',
				    content : '游客不允许投票！'
				  }
				});
			return ;
		}
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
		$.post(url, function(voteResult){
				var messageJson = JSON.parse(voteResult)
			   $("#upValue").text(messageJson.up);
			   $("#downValue").text(messageJson.down);
			   $("#equalValue").text(messageJson.equal);
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
		 if(isShutup){
			 easyDialog.open({
				  container : {
				    header : '错误',
				    content : "你已经被禁言！"
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
		 var hello ={"sender":userName,"message":contents};
		 socket.emit("chatMessageEvent",hello);
          
         ue.execCommand('cleardoc');
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
   		var keepMessage=200;
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
	 
	 function onShutupMessage(messageJson){
		 isShutup=true;
	 }
	 function allUnknowCount(dataJson){
		 $("#unknowCount").text(dataJson.count); 
	 }
	
	 
	  function logout(){
		  socket.close();
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
	  function myClose(){
		  socket.close();
	  }
</script>
<!--<![endif]-->
</body>

</html>
