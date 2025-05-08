<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:include page="../tool/header.html" />
<jsp:include page="../tool/sidebar.html" />

<h2>科目一覧</h2>

<table border="1" cellpadding="8" cellspacing="0" style="width: 60%; margin-top: 20px;">
    <thead>
        <tr style="background-color: #f0f0f0;">
            <th>科目コード</th>
            <th>科目名</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="subject" items="${subjectList}">
            <tr>
                <td>${subject.code}</td>
                <td>${subject.name}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<jsp:include page="../tool/footer.html" />

