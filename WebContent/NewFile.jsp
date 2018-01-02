<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<script type="text/javascript">
	var ws = null;//一个socket对象就是一个通信管道
	var target = "ws://localhost:8080/websocket_01/echo";
	function connect() {
		var userId =1;
		target=target +"?userId="+userId;
		if ('WebSocket' in window) {
			ws = new WebSocket(target);
		} else if ('MozWebSocket' in window) {
			ws = new MozWebSocket(target);
		} else {
			alert('WebSocket is not supported by this browser.');
			return;
		}
		var sendDiv = document.getElementById('sendDiv');
		sendDiv.removeAttribute('style', 'display: none');
		/* ws.onopen = function () {
		    setConnected(true);
		    log('Info: WebSocket connection opened.');
		};
		ws.onmessage = function (event) {
		    log('Received: ' + event.data);
		};
		ws.onclose = function (event) {
		    setConnected(false);
		    log('Info: WebSocket connection closed, Code: ' + event.code + (event.reason == "" ? "" : ", Reason: " + event.reason));
		}; */

	}
	function subSend() {
		var msg = document.getElementById('msg').value;
		ws.send(msg);
		document.getElementById('msg').value = "";
	}
</script>
</head>
<body>

	<button id="connect" onclick="connect();">open</button>
	<div style="display: none;" id="sendDiv">
		<input id="msg">
		<button type="submit" onclick="subSend()">send</button>
	</div>
</body>
</html>