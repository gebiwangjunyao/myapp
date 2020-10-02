<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../frame/frame.jsp">
    <c:param name="main">


        <div id="left-container">
        <div id="rutine-left">
    <h2 class="pad">日の一覧</h2>
    <div id="rutines-list"  style="overflow-y:auto;">
    <table >
        <tbody>
            <tr>
                <th>日の名前</th>
                <th>シェア</th>
                <th>操作</th>
            </tr>
            <c:forEach var="myRutine" items="${myRutines}" varStatus="status">
                    <tr>

                        <td><c:out value="${myRutine.rutine}" /></td>

                        <td>        <c:choose>
            <c:when test="${myRutine.shareCheck != 0}">
                はい
                </c:when>
            <c:otherwise>
                いいえ
            </c:otherwise>
        </c:choose></td>
                        <td>
                            <a href="<c:url value='/DeleteRutine?rutineId=${myRutine.id}&_token=${_token}' />" onclick="confirmDestroy('${myRutine.id}')">削除</a>
                            <a href="<c:url value='/UpdateRutine?rutineId=${myRutine.id}&_token=${_token}' />"><c:choose>
            <c:when test="${myRutine.shareCheck != 0}">
                　　シェアしない
                </c:when>
            <c:otherwise>
                　　シェアする
            </c:otherwise>
        </c:choose></a>
                        </td>
                    </tr>
                </c:forEach>
        </tbody>
    </table>
    </div>
        </div>
</div>





        <div id="right-container">
    <form class="pad"method="POST" action="<c:url value='/CreateRutine' />">

         <label for="rutine">日の名前</label><br /> <input
            type="text" name="rutine" /> <br />

            <select name="share">
            <option value="0"
                <c:if test="${rutine.shareCheck == 0}"> selected</c:if>>シェアしない</option>
            <option value="1"
                <c:if test="${rutine.shareCheck == 1}"> selected</c:if>>シェアする</option>
        </select>
        <input type="hidden" name="id" value="${id}" />
        <input type="hidden" name="_token" value="${_token}" />
        <button type="submit">投稿</button>
    </form>
    <p>シェアされた日々</p>
    <div id="rutines-right"  style="overflow-y:auto;">
    <table >
        <tbody>
            <tr>
                <th>日の名前</th>
                <th>操作</th>
            </tr>
            <c:forEach var="shareRutine" items="${shareRutines}" varStatus="status">
                    <tr>

                        <td><c:out value="${shareRutine.rutine}" /></td>
                        <td>
                            <a href="<c:url value='/ShowRutine?rutineId=${shareRutine.id}&_token=${_token}' />">詳細</a></td>
                    </tr>
                </c:forEach>
        </tbody>
    </table>
    </div>
        </div>



<div id="left-option">
        <ul>
        <li>シェアすると他のユーザーは”シェアされた日々”で見ることができます</li>
        <li>登録時個人情報を控えてください</li>
        </ul>

</div>
<div id="right-option">
<ul>
                <li><a href="<c:url value='/NewQuest' />">クエスト編集</a></li>
        <li><a href="<c:url value='/Index' />">タイトルへ戻る</a></li>
        </ul>

</div>






<script>
                        function confirmDestroy(id) {
                            if(confirm("削除しますよ？")) {
                              document.getElementById(id).submit();
                            }
                    }
                </script>
    </c:param>
</c:import>