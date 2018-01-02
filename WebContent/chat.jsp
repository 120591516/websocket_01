<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="jquery-1.4.4.min.js"></script>
<script type="text/javascript">
	var ws;
	var url = "ws://localhost:8080/websocket_01/chatSocket?username=${sessionScope.username}";
	var thisUser ="${sessionScope.username }";
	window.onload = connect;
	function connect() {
		if ('WebSocket' in window) {
			ws = new WebSocket(url);
		} else if ('MozWebSocket' in window) {
			ws = new MozWebSocket(url);
		} else {
			alert('WebSocket is not supported by this browser.');
			return;
		}
		ws.onmessage = function(event) {
			eval("var result=" + event.data);

			if (result.alert != undefined) {
				$("#content").append(result.alert + "<br/>");
			}

			if (result.names != undefined) {
				$("#userList").html("");
				$(result.names).each(
						function() {
							var str="<input type='checkbox'value='"+this+"'";
							if(thisUser ==this){
								str+="disabled='disabled'";
							}
							str+=("/>"+ this + "<br/>");
							console.info(str);$("#userList").append(str);
						});
			}

			if (result.from != undefined) {
				$("#content").append(
						result.from + " " + result.date + " 说：<br/>"
								+ result.sendMsg + "<br/>");
			}

		};
	}

	function send() {
		var msg = $("#msg").val();
		var num = $('#userList :checked').size();
		console.info($('#userList :checked').size());
		$("#msg").val("");
		var type = 1;
		var username = $('#userList :checked').val();
		console.info(username);
		if (num > 0) {
			type = 2;
			to = ''
		}
		var obj = {
			to : username,
			msg : msg,
			type : type
		//1 广播 2 私聊
		}
		var str = JSON.stringify(obj);
		console.info(str);
		ws.send(str);
	}
</script>
</head>
<body>

	<h3>欢迎 ${sessionScope.username } 使用本系统！！</h3>
	<div id="container" style="border: 1px solid black;width: 400px; height: 400px;float: left;">
	<div id="content"
		style="height: 350px"></div>

	<div style="border-top:1px solid black; width: 400px; height: 50px;" >
		<input id="msg" />
		<button onclick="send();">send</button>
	</div>
	</div>
	<div id="userList"
		style="border: 1px solid black; width: 100px; height: 400px; float: left;"></div>


</body>
</html>