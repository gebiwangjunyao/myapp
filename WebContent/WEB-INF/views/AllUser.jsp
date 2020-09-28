<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <c:if test="${errors != null}">
        <div id="flush_error">
            入力内容にエラーがあります。<br />
            <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" />
                <br />
            </c:forEach>

        </div>
    </c:if>
    <c:if test="${flush != null}">
        <div id="flush_success">
            <c:out value="${flush}"></c:out>
        </div>
    </c:if>
    <form method="POST" action="<c:url value='/AdminCreateUser' />">
        <label for="name">ユーザー名</label><br /> <input type="text" name="name"><br />

        <label for="password">パスワード</label><br /> <input type="password"
            name="password" /> <br /> <label for="admin_flag">権限</label><br />
        <select name="admin_flag">
            <option value="0"
                <c:if test="${user.admin_flag == 0}"> selected</c:if>>一般</option>
            <option value="1"
                <c:if test="${user.admin_flag == 1}"> selected</c:if>>管理者</option>
        </select> <input type="hidden" name="_token" value="${_token}" />
        <button type="submit">投稿</button>
    </form>
    <h2>一覧</h2>
    <table id="users_list">
        <tbody>
            <tr>
                <th>ユーザー名</th>
                <th>新規時間</th>
                <th>更新時間</th>
                <th>権限</th>
                <th>操作</th>
            </tr>
            <c:forEach var="user" items="${users}" varStatus="status">
                <tr>
                    <td><c:out value="${user.name}" /></td>
                    <td><c:out value="${user.created_at}" /></td>
                    <td><c:out value="${user.updated_at}" /></td>
                    <td><c:choose>
                            <c:when test="${user.admin_flag == 1}">
                                    管理者
                                </c:when>
                            <c:otherwise>
                                一般
                            </c:otherwise>
                        </c:choose></td>
                    <td><c:choose>
                            <c:when test="${user.delete_flag == 1}">
                                    （削除済み）
                                </c:when>
                            <c:otherwise>
                                <a href="#" onclick="confirmDestroy('${user.id}');">削除</a>
                                <form id="${user.id}" method="POST" action="/myapp/AdminiDestroyUser">
                                    <input type="hidden" name="id" value="${user.id}" />
                                    <input type="hidden" name="_token" value="${_token}" />
                                </form>
                                <script>
                        function confirmDestroy(id) {
                            if(confirm("削除しますよ？")) {
                              document.getElementById(id).submit();
                            }
                    }
                </script>
                            </c:otherwise>
                        </c:choose></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="">indexへ</a>
</body>
</html>