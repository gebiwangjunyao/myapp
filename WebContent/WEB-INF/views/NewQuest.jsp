<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../frame/frame.jsp">
    <c:param name="main">



        <div id="left-container">
            <div id="quest-left">
                <h2 class="pad">${rutine}　一覧</h2>
                <div id="quest-select">
                    <p>日を選択</p>
                    <form method="POST" action="<c:url value='/NewQuest' />">
                        <select name="rutineId">
                            <c:forEach var="rutine" items="${rutines}" varStatus="status">
                                <option value="${rutine.id}"
                                    <c:if test="${rutine.id == rutineId}"> selected</c:if>>${rutine.rutine}</option>
                            </c:forEach>
                        </select> <input type="hidden" name="_token" value="${_token}" />
                        <button type="submit">選択</button>
                    </form>
                </div>
                <div id="quest-list" style="overflow-y: auto;">
                    <table>
                        <tbody>
                            <tr>
                                <th>開始時間</th>
                                <th>終了時間</th>
                                <th>クエスト</th>
                                <th>詳細</th>
                                <th>操作</th>
                            </tr>
                            <c:forEach var="quest" items="${quests}" varStatus="status">
                                <tr>
                                    <td><c:out value="${quest.startTime}" /></td>
                                    <td><c:out value="${quest.endTime}" /></td>
                                    <td><c:out value="${quest.quest}" /></td>
                                    <td><c:out value="${quest.content}" /></td>
                                    <td><a
                                        href="<c:url value='/DeleteQuest?questId=${quest.id}&_token=${_token}'/>">削除</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div id="right-container">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
            <form class="pad" method="POST"
                action="<c:url value='/CreateQuest' />">
                <p>開始時間</p>
                <input name="startTime" type="time" />
                <p>終了時間</p>
                <input name="endTime" type="time" />
                <p>クエスト</p>
                <input type="text" name="quest" />
                <p>内容詳細</p>
                <textarea id="content-input"  name="content"></textarea>
                <p>操作</p>
                <button type="submit">追加</button>
                <input type="hidden" name="_token" value="${_token}" />

            </form>
            <div id="quest-right">
            </div>
        </div>

<div id="left-option">
<ul>
            <li>日を選んでからクエスト登録してください</li>
            <li>時間かぶらないように登録いてください</li>
            <li>(例：クエスト①　1:00~2:00,　クエスト②　2:01~3:00)</li>
        <li>登録時個人情報を控えてください</li>
</ul>
        </div>
        <div id="right-option">
            <a href="<c:url value='/Index' />">タイトルへ戻る</a>

        </div>

    </c:param>
</c:import>