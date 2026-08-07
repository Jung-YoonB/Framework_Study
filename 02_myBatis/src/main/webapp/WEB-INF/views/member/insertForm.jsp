<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 가입</title>
</head>
<body>
    <h1>회원 가입</h1>

    <form action="/member/insert" method="post">
        <!-- 이름, 이메일, 나이를 입력받아 서버로 요청 -->
        <label>
        이름 : <input type="name" name="name" placeholder="insert name" required>
        </label>
        <br>
        <label>
        이메일 : <input type="email" name="email" placeholder="insert email" required>
        </label>
        <br>
        <label>
        나이 : <input type="number" name="age" value="20" min="0">
        </label>
        <br>
        <button>가입</button>
    </form>
</body>
</html>