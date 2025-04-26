<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<jsp:include page="../tool/header.html" />
<jsp:include page="../tool/sidebar.html" />
<%@ page import="java.time.LocalDate"%>

<h2>学生情報登録</h2>


<label>入学年度</label>
<%int currentYear = LocalDate.now().getYear(); %>
<% for (int i = currentYear - 10; i <= currentYear + 10; i++) {%>
<select name="ent_year">
	<option></option>
</select>
<% } %>

<label>学生番号</label>
<input type="text" name="no" value="${no}"></input>

<label>氏名</label>
<input type="text" name="name" value="${name}"></input>

<select name="class_num">
	<option></option>
</select>

<button type="submit" name="end">登録して終了</button>

<a href="todo">戻る</a>

<jsp:include page="../tool/footer.html" />