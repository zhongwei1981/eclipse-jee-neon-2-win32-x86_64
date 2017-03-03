<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Title: test</title>
<script>
	function displayDate() {
		document.getElementById("demo").innerHTML = Date();
	}
</script>
</head>

<body>
	<%
		out.println("#### ttt: Hello World! xxx");
	%>

	<h1>我的第一个 JavaScript 程序</h1>
	<p id="demo">这是一个段落</p>
	<button type="button" onclick="displayDate()">显示日期</button>

	<br>
	<br>
	<button type="button" onclick="myFunction()">点击这里</button>
	<p>
		<b>注释：</b>myFunction 保存在名为 "myScript.js" 的外部文件中。
	</p>
	<script src="myScript.js"></script>

	<br>
	<br>
	<p>
		点击按钮执行 <em>displayDate()</em> 函数.
	</p>
	<button id="myBtn">点这里</button>
	<script>
		document.getElementById("myBtn").onclick = function() {
			displayDate()
		};
		function displayDate() {
			document.getElementById("demo_date").innerHTML = Date();
		}
	</script>
	<p id="demo_date">Displayed here!</p>
</body>

</html>