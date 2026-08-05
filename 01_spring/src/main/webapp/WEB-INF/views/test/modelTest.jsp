<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>modelTest 파일</title>
	</head>
	<body>
		<h1>결과 화면</h1>
		<p><%= request.getAttribute("message") %></p>
		<p>EL 문법으로 message 불러오기 : ${ message }</p>
	</body>
</html>