<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

</style>

<h2>学生情報登録</h2>

<form action="StudentCreate.action" method="post">
	<div id = "filtering">
		<div id = "year_filter">
			<label>入学年度</label><br>
			<select name="ent_year">
				<option value="">--------</option>
					<%int currentYear = LocalDate.now().getYear(); %>
					<% for (int i = currentYear - 10; i <= currentYear + 10; i++) {%>
        				<option value="<%= i %>" ><%= i %></option>
    				<% } %>
			</select>
		</div>
		<br>

		<label>学生番号</label>
		<br>
		<input type="text" placeholder="学生番号を入力してください" name="no">
		<br>
		<label>氏名</label>
		<br>
		<input type="text" placeholder="氏名を入力してください" name="name">
		<br>

		<div id = "class_filter">
			<label>クラス</label><br>
			<select name="class_num">
				<c:forEach var="c" items="${class_num_set}">
					<option value="${c}">${c}</option>
				</c:forEach>
			</select>
		</div>
		<br>


		<div id = "create_end">
			<button type="submit" name="end">登録して終了</button>
		</div>
	</div>
	<br>
</form>

<jsp:include page="../tool/footer.html" />