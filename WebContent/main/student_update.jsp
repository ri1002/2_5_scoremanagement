<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="java.time.LocalDate"%>
<jsp:include page="../tool/header.html" />
<jsp:include page="../tool/sidebar.html" />


<h2>学生情報登録</h2>

<form action="StudentUpdateExecute.action" method="post">

<label>入学年度</label>
<br>
<input type="hidden" name="ent_year" value="${student.entYear}">${student.entYear}
<br>
<label>学生番号</label>
<br>
<input type="hidden" name="no" value="${student.no}">${student.no}
<br>

<label>氏名</label>
<br>
<input type="text" name="name" value="${student.name}">
<br>

<label>クラス</label>
<br>
<select name="class_num">
	<c:forEach var="c" items="${class_num_set}">
		<option value="${c}" <c:if test="${c == student.classNum}">selected</c:if>>${c}</option>
	</c:forEach>
</select>
<br>
<input type="checkbox" name="is_attend" <c:if test="${student.isAttend}">checked</c:if>>
<br>
在学中
<br>
<button type="submit" name="end">登録して終了</button>
<br>

</form>

<a href="todo">戻る</a>

<jsp:include page="../tool/footer.html" />