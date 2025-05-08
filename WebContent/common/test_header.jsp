<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.time.LocalDate"%>
<%@page import="bean.ClassNum, java.util.*" %>
<%@page import="java.util.List" %>


	<label>入学年度</label>
	<label>クラス</label>
	<label>科目</label>
	<label>回数</label><br>

	<select name="f1">
				<option value="0">--------</option>
				<%int currentYear = LocalDate.now().getYear();%>
				<%
					for (int i = currentYear; i >= currentYear - 10; i--) {
						String selected = (String.valueOf(i).equals(request.getParameter("f1"))) ? "selected" : "";
				%>
       				<option value="<%= i %>"  <%= selected %>><%= i %></option>
   				<% } %>
			</select>

			<select name="f2">
				<option value="0">--------</option>
				<c:forEach var="num" items="${class_num_set}">
					<option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
				</c:forEach>
			</select>

	<select name="f3">
		<option value="0">--------</option>
			<c:forEach var="subject" items="${subjects}">
    <option value="${subject.cd}">${subject.name}</option>
  </c:forEach>
	</select>

	<select name="f4">

		<option value="">--------</option>
  		<%for(int i=1; i <= 10; i++) {%>
  			<option value="<%= i%>"><%= i%></option>
  		<%} %>
	</select>


