<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/unit.js"></script>
<script type='text/javascript' src='jwplayer.js'></script>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.bootstrap.min.js"></script>
<script type="text/javascript" src="../js/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="swfobject.js"></script>
<script type="text/javascript" src="web_socket.js"></script>
<script type="text/javascript" charset="utf-8" src="../ueditor1_4_3/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="../ueditor1_4_3/ueditor.all.js"> </script>
<link href="../js/bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="../js/easydialog-v2.0/easydialog.min.js"></script>
<link href="../js/easydialog-v2.0/easydialog.css" type="text/css" rel="stylesheet">
<title>西北大宗直播室</title>
</head>
<body>

</body>
<script type="text/javascript">
WEB_SOCKET_SWF_LOCATION = "WebSocketMain.swf";
$(document).ready(function(){
	websocket = new WebSocket("ws://127.0.0.1:8080/chat"); 
});

</script>
</html>