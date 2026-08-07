<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 목록</title>
</head>
<body>
    <%-- message 값이 있을 경우 alert로 메시지 내용 출력 --%>
    <c:if test="${ message != null }">
        <script>
            alert("${ message }");

            <c:remove var="message" />
        </script>
    </c:if>
    <h1>회원 목록</h1>
	
	<h3><a href="/member/insert">회원 가입</a></h3>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>이름</th>
                <th>이메일</th>
                <th>나이</th>
				<th colspan="2">관리</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${ empty memberList }">
                <tr>
                    <td colspan="4">조회 된 목록이 없습니다.</td>
                </tr>
            </c:if>
            <c:forEach var="ml" items="${ memberList }">
            <tr>
                <td>${ ml.id }</td>
                <td>${ ml.name }</td>
                <td>${ ml.email }</td>
                <td>${ ml.age }</td>
				<td><a href="/member/delete/${ ml.id }">삭제</a></td>
				<td><a href="/member/update/${ ml.id }">수정</a></td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>