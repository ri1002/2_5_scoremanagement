<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<jsp:include page="../tool/header.html" />
<jsp:include page="../tool/sidebar.html" />

<h2>科目管理</h2>

<!-- ここにフォームを追加 -->
<form action="${pageContext.request.contextPath}/main/subject_list" method="post">
    <button type="submit">科目一覧を表示</button>
</form>

<!-- 新規登録ボタン -->
<div id="new_registration">
    <a href="subject_create.jsp">新規登録</a>
</div>

<!-- JSTLを使った科目一覧の表示部分（すでにある内容） -->
<c:choose>
    <c:when test="${not empty subjectList}">
        <p>検索結果：${subjectList.size()}件</p>
        <table>
            <tr>
                <th>科目コード</th>
                <th>科目名</th>
                <th>学校名</th>
            </tr>
            <c:forEach var="subject" items="${subjectList}">
                <tr>
                    <td>${subject.cd}</td>
                    <td>${subject.name}</td>
                    <td>${subject.school.name}</td>
                </tr>
            </c:forEach>
        </table>
    </c:when>

    <c:otherwise>
        <p>科目データが存在しません。</p>
    </c:otherwise>
</c:choose>

<jsp:include page="../tool/footer.html" />
