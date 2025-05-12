<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.time.LocalDate"%>
<%@page import="bean.ClassNum, java.util.*" %>
<%@page import="java.util.List" %>

	<p>科目情報</p>
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