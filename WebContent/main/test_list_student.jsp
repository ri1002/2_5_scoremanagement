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

/* エラーメッセージ自体 */
	.error-message {
    	position: relative;
	    text-align: center;
    	font-size: 14px;
	    box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
	    max-width: 300px;
	    bottom: 5px;
	    float: right;
	    margin: 0 auto;
    	padding: 5px;
    	background: #fff;
    	border: 2px solid #ddd;
    	border-radius: 8px;
	}

	/* エラーメッセージアイコン */
	.error-icon {
    	background: #FFC800;
	    color: #fff;
    	font-weight: bold;
	    width: 20px;
    	height: 20px;
	    display: inline-flex;
	    align-items: center;
	    justify-content: center;
	    margin-left: 5px;
	    font-size: 14px;
	    position: relative;
	}

	/* エラーメッセージの矢印 */
	.error-arrow {
    	position: absolute;
	    top: -6px;
	    left: 10px;
	    width: 12px;
	    height: 12px;
	    background: #fff;
	    border-top: 2px solid #ddd;
	    border-left: 2px solid #ddd;
	    transform: translateX(-50%) rotate(45deg);
	}
</style>

<h2>成績一覧(学生)</h2>

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

<c:if test="${not empty selectedStudent}">
    <p>氏名: ${selectedStudent.name} (${selectedStudent.no})</p>
</c:if>

<c:choose>
    <c:when test="${tests.size() > 0}">
        <div class="table-wrapper">
            <table>
                <tr>
                    <th>科目名</th>
                    <th>科目コード</th>
                    <th>回数</th>
                    <th>点数</th>
                </tr>
                <c:forEach var="test" items="${tests}">
                    <tr>
                        <td>${test.subjectName}</td>
                        <td>${test.subjectCd}</td>
                        <td>${test.num}</td>
                        <td>${test.point}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:when>
    <c:otherwise>
        <p>成績情報が存在しませんでした</p>
    </c:otherwise>
</c:choose>

<jsp:include page="../tool/footer.html" />