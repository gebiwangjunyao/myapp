<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <c:if test="${flush != null}">
        <div id="flush_success">
            <c:out value="${flush}"></c:out>
        </div>
    </c:if>
    <form method="POST" action="<c:url value='/CreateUser' />">
        <label for="name">ユーザー名</label><br />
        <input type="text" name="name"><br />

         <label for="password">パスワード</label><br />
         <input type="text" name="password" /> <br />

    <input type="hidden" name="_token" value="${_token}" />

        <button type="submit">投稿</button>
    </form>
</body>
</html>