<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../tool/sidebar.html" />
<%@ page import="java.time.LocalDate"%>
<%@page import="bean.ClassNum, java.util.*" %>
<%@page import="java.util.List" %>

<style>
	#search-header {
		padding: 1em;
		margin: 2em 0;
		border: solid 1px #ccc;
		border-radius: 5px;
	}

	#search-header label {
		display: inline-block;
		width: 80px;
		margin-right: 10px;
	}

	#search-header select,
	#search-header input[type="text"] {
		padding: 8px;
		margin-right: 15px;
		border: 1px solid #ccc;
		border-radius: 3px;
	}

	#text {
		color: #00bfff;
	}

	.student-info {
		margin-top: 20px;
		padding: 1em;
		border: solid 1px #ccc;
		border-radius: 5px;
	}

	.student-info label {
		display: inline-block;
		width: 100px;
		margin-right: 10px;
	}

	.student-info input[type="text"] {
		padding: 8px;
		border: 1px solid #ccc;
		border-radius: 3px;
		margin-right: 15px;
	}

	.button-container button {
		padding: 8px 15px;
		border: none;
		outline: none;
		appearance: none;
		background-color: #28a745;
		color: #fff;
		border-radius: 5px;
		cursor: pointer;
		font-weight: bold;
		margin-right: 10px;
	}

	.button-container button:hover {
		background-color: #1e7e34;
	}

	button{
  		padding: 0;
  		border: none;
  		outline: none;
  		appearance: none;
		background: none;
  		cursor: pointer;
  		color: #FFFFFF;
  		background: #808080;
 	 	display: inline-block;
  		padding:10px;
  		border-radius:5px;
  		text-align: center;
  		font-weight: bold;
  		text-decoration: none;

	}

    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
        border: 1px solid #ccc;
        border-radius: 5px;
        overflow: hidden;
        background-color: #fff;
        font-family: sans-serif;
    }

    th, td {
        padding: 12px 15px;
        text-align: center;
        border-bottom: 1px solid #ddd;
    }

    .table-wrapper {
        margin: 20px 0;
        overflow-x: auto;
    }

    	form {
  display: flex;
  align-items: center;
  gap: 10px;      /* 要素間のスペース */
  flex-wrap: nowrap; /* 改行させない */
}

form button {
  white-space: nowrap; /* ボタン内での折り返し防止 */
}
</style>


<h2>成績一覧(科目)</h2>

<div id="search-header">
	<form action="TestListSubjectExecute.action" method="post">
	<jsp:include page="/common/test_list_subject_header.jsp" />
	<button type="submit">検索</button>
	</form>
</div>

<div class="student-info">
	<form action="TestListStudentExecute.action" method="post">
	<jsp:include page="/common/test_list_student_header.jsp" />
	<button type="submit">検索</button>
	</form>
</div>

<c:choose>
	<c:when test="${tests.size() > 0 }">
		<label>科目:${selectedSubject.name}</label>


		<div class="table-wrapper">
<table>
    <tr>
        <th>入学年度</th>
        <th>クラス</th>
        <th>学生番号</th>
        <th>氏名</th>
        <th>1回</th>
        <th>2回</th>
    </tr>
    <c:forEach var="test" items="${tests}">
        <tr>
            <td>${test.student.entYear}</td>
            <td>${test.student.classNum}</td>
            <td>${test.student.no}</td>
            <td>${test.student.name}</td>
            <td>
                <c:choose>
                    <c:when test="${test.point1 != null}">
                        ${test.point1}
                    </c:when>
                    <c:otherwise>-</c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${test.point2 != null}">
                        ${test.point2}
                    </c:when>
                    <c:otherwise>-</c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
</table>
</div>

	</c:when>
<c:otherwise>
    <p>学生データが存在しませんでした</p>
</c:otherwise>
</c:choose>

<jsp:include page="../tool/footer.html" />