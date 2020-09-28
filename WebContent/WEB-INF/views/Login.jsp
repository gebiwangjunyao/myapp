<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../frame/frame.jsp">
    <c:param name="main">


        <div id="left-container">
            <div>
<c:if test="${hasError}">
            <div id="flush_error">
                ユーザー名かパスワードが間違っています。
            </div>
        </c:if>
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
                <h2 class="pad">ログイン</h2>
                <form class="pad" method="POST" action="<c:url value='/Login' />">
                    <label for="name">ユーザー名</label><br /> <input type="text"
                        name="name" value="${name}" /> <br /> <br /> <label
                        for="password">パスワード</label><br /> <input type="password"
                        name="password" /> <br /> <br /> <input type="hidden"
                        name="_token" value="${_token}" />
                    <button type="submit">ログイン</button>
                </form>
            </div>
        </div>



        <div id="left-option">
            <ul>
                <li><a href="<c:url value='/NewUser' />">新規ユーザー</a></li>
            </ul>
        </div>
    </c:param>
</c:import>