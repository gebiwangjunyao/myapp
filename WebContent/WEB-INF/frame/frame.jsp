<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>生活リズム</title>
<link rel="stylesheet" href="<c:url value='/css/reset.css' />">
<link rel="stylesheet" href="<c:url value='/css/style.css' />">
</head>
<body>
    <div id="wrapper">
        <div id="background-color"></div>
        <div id="header">
            <h1 id="title">生活リズム</h1>
            <a id="logout" class="whiteColor"href="<c:url value='/Logout' />">ログアウト</a>
        </div>
        <div id="main">${param.main}</div>
        <div id="footer">
            <p id="sign" >by　王　俊尭</p>
        </div>
    </div>
</body>
</html>