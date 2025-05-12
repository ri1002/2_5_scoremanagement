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

<h2>成績一覧(科目)</h2>

<div id="search-header">
	<jsp:include page="/common/test_list_subject_header.jsp" />
	<form action="TestList.action" method="post">
	<button type="submit">検索</button>
	</form>
</div>

<c:choose>
	<c:when test="${tests.size() > 0 }">

	<%-- 検索結果件数の表示 --%>
	<p>検索結果:${tests.size()}件</p>


		<table border="1">
			<tr>
            	<th><label>入学年度</label></th>
            	<th><label>クラス</label></th>
            	<th><label>学生番号</label></th>
         	   	<th><label>氏名</label></th>
            	<th><label>1回</label></th>
            	<th><label>2回</label></th>
        	</tr>
        	<%-- 検索結果の生徒を表示 --%>
        	<c:forEach var="test" items="${tests}">
        		<tr>
	            	<td>${test.student.entYear}</td>
	    	        <td>${test.student.classNum}</td>
	    	       	<%--学生番号をサーブレットに送信 --%>
	    	        <td>${test.student.no}</td>

    	    	    <td>${test.student.name}</td>
           		</tr>
           		<% String error = request.getParameter("error"); %>
           		<c:if test="${not empty error}">
           			<tr>
           				<td> </td>
           				<td> </td>
           				<td> </td>
           				<td> </td>
           		    	<td>${error}</td>
           			</tr>
           		</c:if>
        	</c:forEach>
    	</table>

	</c:when>
<c:otherwise>
    <p>学生データが存在しません。</p>
</c:otherwise>
</c:choose>

<div class="student-info">
	<jsp:include page="/common/test_list_student_header.jsp" />
	<form action="TestList.action" method="post">
	<button type="submit">検索</button>
		<p class="error-message">${errorMessage}</p>
	</form>
</div>

<div id="text">
<p>科目情報を選択または学生情報を入力して検索ボタンをクリックしてください。</p>
</div>

<jsp:include page="../tool/footer.html" />