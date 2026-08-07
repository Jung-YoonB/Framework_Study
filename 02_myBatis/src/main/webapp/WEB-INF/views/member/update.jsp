<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 수정</title>
</head>
<body>
    <h1>회원 수정</h1>

    <form action="/member/update" method="post">
        <label>
			<input type="hidden" name="id" value="${ member.id }">
		</label>
        <label>
        이름 : <input type="name" name="name" value="${ member.name }" required>
        </label>
        <br>
        <label>
        이메일 : <input type="email" name="email" value="${ member.email }" required>
        </label>
        <br>
        <label>
        나이 : <input type="number" name="age" value="${ member.age }" min="0">
        </label>
        <br>
        <button>수정</button>
    </form>
</body>
</html>