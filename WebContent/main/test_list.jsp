<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<jsp:include page="../tool/header.html" />
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
		background-color: #f9f9f9;
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
		background-color: #f9f9f9;
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

	.error-message {
		color: red;
		margin-top: 10px;
	}
</style>

<h2>成績参照</h2>

<div id="search-header">
	<p>科目情報</p>
	<form action="TestList.action" method="post">
		<label for="admissionYear">入学年度</label>
		<select id="admissionYear" name="f1">
			<option value="">--------</option>
			<% int currentYear = LocalDate.now().getYear(); %>
			<% for (int i = currentYear; i >= currentYear - 10; i--) { %>
				<option value="<%= i %>" <%= (request.getParameter("admissionYear") != null && request.getParameter("admissionYear").equals(String.valueOf(i))) ? "selected" : "" %>>
					<%= i %>
				</option>
			<% } %>
		</select>

		<label for="class">クラス</label>
		<select id="class" name="f2">
				<option value="0">--------</option>
				<c:forEach var="num" items="${class_num_set}">
					<option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
				</c:forEach>
			</select>

		<label for="subject">科目</label>
		<select id="subject" name="f3">
			<option value="">--------</option>
			<c:forEach var="subject" items="${subjects}">
				<option value="${subject.cd}">${subject.name}</option>
			</c:forEach>
		</select>

		<button type="submit">検索</button>
	</form>
</div>

<div class="student-info">
	<p>学生情報</p>
	<form action="TestList.action" method="post">
		<label for="studentId">学生番号</label>
		<input type="text" id="studentId" name="f4" value="${param.studentId}" placeholder="学生番号を入力してください">
		<button type="submit">検索</button>
		<p class="error-message">${errorMessage}</p>
	</form>
</div>

<div id="text">
<p>※学生番号または学生氏名を入力して検索ボタンをクリックしてください。</p>
</div>

<jsp:include page="../tool/footer.html" />