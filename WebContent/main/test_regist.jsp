<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html; charset=UTF-8" %>
<jsp:include page="../tool/header.html" />
<jsp:include page="../tool/sidebar.html" />
<%@ page import="java.time.LocalDate"%>
<%@page import="bean.ClassNum, java.util.*" %>
<%@page import="java.util.List" %>

<style>

	#sheader { padding: 0.5em 1em;
    	margin: 2em 0;
	    font-weight: bold;
    	border: solid 1px;/*線*/
	    border-radius: 10px;/*角の丸み*/
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

</style>


<h2>成績管理</h2>

<div id = "sheader">
<form action="StudentList.action" method="post">

	<label>入学年度</label>
	<label>クラス</label>
	<label>科目</label>
	<label>回数</label><br>

	<select name="f1">
		<option value="">--------</option>
			<%int currentYear = LocalDate.now().getYear(); %>
			<% for (int i = currentYear - 10; i <= currentYear + 10; i++) {%>
       			<option value="<%= i %>" ><%= i %></option>
   			<% } %>
	</select>

	<select name="f2">
		<option value="">--------</option>
			<c:forEach var="c" items="${cList}">
    <option value="${c.getClass_num() }">${ c.getClass_num() }</option>
	</c:forEach>
	</select>

	<select name="f3">
		<option value="">--------</option>
			<c:forEach var="c" items="${studentSubjectList}">
			<option value="${c.class_num}">${c.class_num}</option>
			</c:forEach>
	</select>


	<select name="f4">

		<option value="">--------</option>
  		<%for(int i=1; i <= 10; i++) {%>
  			<option value="<%= i%>"><%= i%></option>
  		<%} %>
	</select>

	<button type="submit">検索</button>
</form>
</div>

<jsp:include page="../tool/footer.html" />