<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@page contentType="text/html; charset=UTF-8" %>
<jsp:include page="../tool/header.html" />
<jsp:include page="../tool/sidebar.html" />
<%@ page import="java.time.LocalDate"%>


<%-- 学生登録 --%>
<style>
	#filtering {
		padding: 10px;
		height: 12%;
	}
	#filtering select{
		width: 100%;
	}
	#filtering input {
		width: 100%;
	}

	#student_name, #student_number {
		margin: 10px;
		text-align: center;
		border: solid 2px #ddd;
	}

</style>

<h2>学生情報登録</h2>

<form action="StudentCreateExecute.action" method="post">
	<div id = "filtering">
		<div id = "year_filter">
			<label>入学年度</label><br>
			<select name="ent_year">
				<option value="0">--------</option>
					<%int currentYear = LocalDate.now().getYear(); %>
					<% for (int i = currentYear - 10; i <= currentYear + 10; i++) {%>
        				<option value="<%= i %>"
        					<c:if test="${param.ent_year == i}" >selected</c:if>><%= i %></option>
    				<% } %>
			</select>
		<c:if test="${not empty student_ent_year}">
    		<p style="color:#FFCC33">${student_ent_year}</p>
		</c:if>
		</div>
		<br>

		<label>学生番号</label>
		<br>

		<input type="text" placeholder="学生番号を入力してください" name="no" value="${param.no}">

		<c:if test="${not empty student_number}">
    		<div id="student_number"><p>${student_number}</p></div>
		</c:if>
		<c:if test="${not empty student_duplication}">
    		<p style="color:#FFCC33">${student_duplication}</p>
		</c:if>

		<br>
		<label>氏名</label>
		<br>
		<input type="text" placeholder="氏名を入力してください" name="name" value="${param.name}">
		<br>

		<c:if test="${not empty student_name}">
    		<div id="student_name"><p>${student_name}</p></div>
		</c:if>




		<div id = "class_filter">
			<label>クラス</label><br>
			<select name="class_num">
				<c:forEach var="c" items="${class_num_set}">
					<option value="${c}"<c:if test="${param.class_num == c}">selected</c:if>>${c}</option>
				</c:forEach>
			</select>
		</div>
		<br>


		<div id = "create_end">
			<button type="submit" name="end">登録して終了</button>
		</div>
		<a href="http://localhost:8080/scoremanagement/main/StudentList.action">戻る</a>
	</div>
	<br>
</form>

<jsp:include page="../tool/footer.html" />