<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../frame/frame.jsp">
    <c:param name="main">



        <div id="left-container">
            <div id="index-left">
                <h2 class="pad">${LoginUser.id}</h2>
                <h1 id="nowQuest">クエストなし!</h1>
                <p id="content">なんかしよう!</p>
                <p id="startTime">開始時間</p>
                <h2 id="time"></h2>
                <p id="endTime">終了時間</p>
                <progress id="progress" value="0" max="100"></progress>
                <p id="totalTime">トータル</p>
                <h2 id="limtTime">残り</h2>
                <p id="Timer">経過</p>
            </div>
        </div>









        <div id="right-container">
            <div id="index-right">
                <h2 class="pad">${thisRutine.rutine}</h2>
                <div id="index-select">
                    <p>日を選択</p>
                    <form method="POST" action="<c:url value='/Index' />">
                        <select name="rutineId">
                            <c:forEach var="rutine" items="${rutines}" varStatus="status">
                                <option value="${rutine.id}"
                                    <c:if test="${rutine.id == rutineId}"> selected</c:if>>${rutine.rutine}</option>
                            </c:forEach>
                        </select> <input type="hidden" name="_token" value="${_token}" />
                        <button type="submit">選択</button>
                    </form>
                </div>




                <div id="index-list" >
                <table style="overflow-y:auto;">
                    <tbody>
                        <tr>
                            <th>開始時間</th>
                            <th>終了時間</th>
                            <th>クエスト</th>
                            <th>詳細</th>
                        </tr>
                        <c:forEach var="quest" items="${quests}" varStatus="status">
                            <tr>
                                <td id="startTime${status.index}"><c:out
                                        value="${quest.startTime}" /></td>
                                <td id="endTime${status.index}"><c:out
                                        value="${quest.endTime}" /></td>
                                <td id="quest${status.index}"><c:out value="${quest.quest}" /></td>
                                <td id="content${status.index}" style="overflow-y:auto;">
                                    <div >
                                        <c:out value="${quest.content}" />
                                </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        </div>

        <div id="left-option">
            <ul>
                <li>日を選択されると表示されます</li>
            </ul>
        </div>
        <div id="right-option">
            <ul>
                <li><a href="<c:url value='/NewRutine' />">日を編集</a></li>
                <li><a href="<c:url value='/NewQuest' />">クエスト編集</a></li>
            </ul>
        </div>


        <script>
    var count = ${count};
</script>
        <script src="js/Index.js"></script>
    </c:param>
</c:import>