<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@page import="bean.Subject" %>
<%@page import="java.util.List" %>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../tool/sidebar.html" />

<h2>科目一覧</h2>
<div id="new_registration">
<a href="http://localhost:8080/scoremanagement/main/SubjectCreate.action">新規登録</a>
</div>

<c:choose>
<c:when test="${not empty subjectList}">
<p>登録件数: ${subjectList.size()} 件</p>

        <table border="1">
<tr>
<th>科目コード</th>
<th>科目名</th>
<th>操作</th>
</tr>
<c:forEach var="subj" items="${subjectList}">
<tr>
<td>${subj.cd}</td>
<td>${subj.name}</td>
<td>
<a href="http://localhost:8080/scoremanagement/main/SubjectUpdate.action?code=${subj.cd}">変更</a> |
<a href="http://localhost:8080/scoremanagement/main/SubjectDelete.action?code=${subj.cd}" onclick="return confirm('本当に削除しますか？');">削除</a>
</td>
</tr>
</c:forEach>
</table>
</c:when>
<c:otherwise>
<p>科目データが存在しません!</p>
</c:otherwise>
</c:choose>

<jsp:include page="../tool/footer.html" />