<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="bean.Student" %>
<%@page import="java.util.List" %>
<%@page import="java.time.LocalDate" %>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../tool/sidebar.html" />

<%-- 学生一覧 --%>

<style>
    table {
        border-collapse: collapse;
        width: 100%;
    }
    th {
        border-bottom: 1px solid #999;
        padding: 8px;
        text-align: left;
    }
    td {
        border-top: 1px solid #ddd;
        border-bottom: 1px solid #ddd;
        padding: 8px;
    }
    tr:first-child td {
        border-top: none;
    }
    /* 横の線を消す */
    td, th {
        border-left: none;
        border-right: none;
    }
    #new_registration {
		text-align: right;
		margin: 10px 10px;
    }
    #filtering {
    	display: flex;
	    justify-content: space-around;
    	align-items: center;
	    flex-wrap: wrap;
    	margin: 10px;
    	padding: 10px;
    	border: solid 1px #ddd;
    	border-radius:5px;
    }
    #filtering select {
    	width: 100%;
    }
    #year_filter, #class_filter, #currently_enrolled_filter, #filter_filter{
   	 	width: 25%;
		padding: 10px;
    }
    #year_filter, #class_filter {
    	text-align: left;
    }
    #currently_enrolled_filter, #filter_filter{
    	text-align: center;
    }

	button{
  		padding: 0;
  		border: none;
  		outline: none;
  		appearance: none;
		background: none;
  		cursor: pointer;
	}
	#filter_filter button {
  		color: #FFFFFF;
  		background: #808080;
 	 	display: inline-block;
 	 	width: 50%;
 	 	min-width: 80px;        /* どんなに画面が狭くても最低100px */
    	max-width: 200px;
  		padding:10px;
  		border-radius:5px;
  		text-align: center;
  		font-weight: bold;
  		text-decoration: none;
	}
	#filter_filter button:hover {
  		color: #FFFFFF;
  		background: #d3d3d3;
	}

</style>

<%--画面タイトル --%>
<h2>学生管理</h2>

<%--新規登録リンク --%>
<div id = "new_registration"><a href = "http://localhost:8080/scoremanagement/main/StudentCreate.action">新規登録</a></div>

<form action="StudentList.action" method="post">

	<div id = "filtering">
		<div id = "year_filter">
			<%--入学年度 --%>
			<label>入学年度</label><br>


			<%--入学年度セレクトボックス --%>
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
		</div>

		<div id = "class_filter">
		<%--クラス --%>
			<label>クラス</label><br>
			<%--クラスセレクトボックス --%>
			<select name="f2">
				<option value="0">--------</option>
				<c:forEach var="num" items="${class_num_set}">
					<option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
				</c:forEach>
			</select>
		</div>

		<div id = "currently_enrolled_filter">
			<%--在学中ON/OFF --%>
			<label>
				<input type="checkbox" name="f3" value="t"<c:if test="${!empty f3}">checked</c:if>>在学中
			</label>
		</div>

		<%--絞り込みボタン --%>
		<div id = "filter_filter">
			<button type="submit">絞込み</button>
		</div>
	</div>
</form>

<c:choose>
	<c:when test="${students.size()>0 }">

	<%--検索結果件数 --%>
	<p>検索結果:${students.size()}件</p>

	<%--学生一覧テーブル --%>
	<table border="1">
		<%--ヘッダー --%>
        <tr>
            <th>入学年度</th>
            <th>学生番号</th>
            <th>氏名</th>
            <th>クラス</th>
            <th>在学中</th>
            <th>　</th>
        </tr>
        <%--学生情報 --%>
        <c:forEach var="student" items="${students }">
            <tr>
                <td>${student.entYear}</td>
                <td>${student.no}</td>
                <td>${student.name}</td>
                <td>${student.classNum}</td>
                <td>
                	<c:choose>
                    	<c:when test="${student.getAttend() }">〇</c:when>
                    	<c:otherwise>×</c:otherwise>
                	</c:choose>
                </td>
                <%--学生情報変更リンク --%>
                <td><a href="http://localhost:8080/scoremanagement/main/StudentUpdate.action?no=${student.no}">変更</a></td>
            </tr>
        </c:forEach>
    </table>
</c:when>
<%--学生情報なしメッセージ --%>
<c:otherwise>
    <div><p>学生データが存在しません。</p></div>
</c:otherwise>
</c:choose>
<jsp:include page="../tool/footer.html" />