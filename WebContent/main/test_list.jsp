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

<h2>成績参照</h2>

<div id="search-header">
	<form action="TestListSubjectExecute.action" method="post">
	<input type="hidden" name="f" value="sj" />
	<jsp:include page="/common/test_list_subject_header.jsp" />
	<button type="submit">検索</button>
	</form>

	<c:if test="${not empty errors}">
    		<p style="color:orange">${errors}</p>
		</c:if>
</div>

<div class="student-info">
	<form action="TestListStudentExecute.action" method="post">
	<input type="hidden" name="f" value="st" />
	<jsp:include page="/common/test_list_student_header.jsp" />
	<button type="submit">検索</button>
	</form>
</div>

<div id="text">
<p>科目情報を選択または学生情報を入力して検索ボタンをクリックしてください。</p>
</div>

<jsp:include page="../tool/footer.html" />