<!DOCTYPE HTML>
<html>
<head>
<title>首页</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">
<!-- 引入 JQuery  -->
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<!-- 引入 sockJS  -->
<script src="https://cdn.bootcss.com/sockjs-client/1.1.4/sockjs.js"></script>
<!-- 自定义JS文件 -->
<script type="text/javascript">
	$(function() {
		var websocket;
		// 首先判断是否 支持 WebSocket
		if ('WebSocket' in window) {
			websocket = new WebSocket("ws://localhost:8080/SECIMS/websocket");
		} else if ('MozWebSocket' in window) {
			websocket = new MozWebSocket("ws://localhost:8080/SECIMS/websocket");
		} else {
			websocket = new SockJS(
					"http://localhost:8080/SECIMS/sockjs/websocket");
		}
		// 打开时
		websocket.onopen = function(evnt) {
			console.log("  websocket.onopen  ");
		};
		// 处理消息时
		websocket.onmessage = function(evnt) {
			$("#msg").append(
					"<p>(<font color='red'>" + evnt.data + "</font>)</p>");
			console.log("  websocket.onmessage   ");
		};
		websocket.onerror = function(evnt) {
			console.log("  websocket.onerror  ");
		};
		websocket.onclose = function(evnt) {
			console.log("  websocket.onclose  ");
		};
		// 点击了发送消息按钮的响应事件
		$("#TXBTN").click(function() {
			// 获取消息内容
			var text = $("#tx").val();
			// 判断
			if (text == null || text == "") {
				alert(" content  can not empty!!");
				return false;
			}
			var msg = {
				msgContent : text,
				postsId : 1
			};
			// 发送消息
			websocket.send(JSON.stringify(msg));
		});
	});
</script>
</head>
<body>
	<!-- 最外边框 -->
	<div
		style="margin: 20px auto; border: 1px solid blue; width: 300px; height: 500px;">
		<!-- 消息展示框 -->
		<div id="msg"
			style="width: 100%; height: 70%; border: 1px solid yellow; overflow: auto;"></div>
		<!-- 消息编辑框 -->
		<textarea id="tx" style="width: 100%; height: 20%;"></textarea>
		<!-- 消息发送按钮 -->
		<button id="TXBTN" style="width: 100%; height: 8%;">发送数据</button>
	</div>
</body>
</html>