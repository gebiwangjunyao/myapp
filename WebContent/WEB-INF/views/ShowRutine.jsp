<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../frame/frame.jsp">
    <c:param name="main">
 <div id="left-container">
 <div id="show-left">
<h2 class="pad">${thisRutine.rutine}</h2>
<h2 class="pad">一覧</h2>
        <div id="show-list"style="overflow-y:auto;">
    <table>
        <tbody>
            <tr>
                <th>開始時間</th>
                <th>終了時間</th>
                <th>クエスト</th>
                <th>詳細</th>
            </tr>
            <c:forEach var="quest" items="${quests}" varStatus="status">
                    <tr>
                        <td><c:out value="${quest.startTime}" /></td>
                        <td><c:out value="${quest.endTime}" /></td>
                        <td><c:out value="${quest.quest}" /></td>
                        <td><c:out value="${quest.content}" /></td>
                    </tr>
                </c:forEach>
        </tbody>
    </table>
    </div>
    </div>
    </div>
    <div id="right-option">
        <a href="<c:url value='/Index' />">タイトルへ戻る</a>

</div>
    </c:param>
</c:import>