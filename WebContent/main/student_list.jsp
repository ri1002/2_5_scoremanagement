<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<<<<<<< HEAD
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="bean.Student" %>
<%@page import="java.util.List" %>
<%@page import="java.time.LocalDate" %>
<jsp:include page="../common/header.jsp" />
=======
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<jsp:include page="../tool/header.html" />
>>>>>>> branch 'master' of https://github.com/ri1002/2_5_scoremanagement.git
<jsp:include page="../tool/sidebar.html" />

<h2>科目管理</h2>

<<<<<<< HEAD
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


<h2>学生管理</h2>

<div id = "new_registration"><a href = "http://localhost:8080/scoremanagement/main/StudentCreate.action">新規登録</a></div>

<form action="StudentList.action" method="post">

	<div id = "filtering">
		<div id = "year_filter">
			<label>入学年度</label><br>


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
			<label>クラス</label><br>
			<select name="f2">
				<option value="0">--------</option>
				<c:forEach var="num" items="${class_num_set}">
					<option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
				</c:forEach>
			</select>
		</div>

		<div id = "currently_enrolled_filter">
			<label>
				<input type="checkbox" name="f3" value="t"<c:if test="${!empty f3}">checked</c:if>>在学中
			</label>
		</div>

		<div id = "filter_filter">
			<button type="submit">絞込み</button>
		</div>
	</div>
=======
<!-- ここにフォームを追加 -->
<form action="${pageContext.request.contextPath}/main/subject_list" method="post">
    <button type="submit">科目一覧を表示</button>
>>>>>>> branch 'master' of https://github.com/ri1002/2_5_scoremanagement.git
</form>

<!-- 新規登録ボタン -->
<div id="new_registration">
    <a href="subject_create.jsp">新規登録</a>
</div>

<!-- JSTLを使った科目一覧の表示部分（すでにある内容） -->
<c:choose>
    <c:when test="${not empty subjectList}">
        <p>検索結果：${subjectList.size()}件</p>
        <table>
            <tr>
<<<<<<< HEAD
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
                <td><a href="http://localhost:8080/scoremanagement/main/StudentUpdate.action?no=${student.no}">変更</a></td>
=======
                <th>科目コード</th>
                <th>科目名</th>
                <th>学校名</th>
>>>>>>> branch 'master' of https://github.com/ri1002/2_5_scoremanagement.git
            </tr>
            <c:forEach var="subject" items="${subjectList}">
                <tr>
                    <td>${subject.cd}</td>
                    <td>${subject.name}</td>
                    <td>${subject.school.name}</td>
                </tr>
            </c:forEach>
        </table>
    </c:when>

    <c:otherwise>
        <p>科目データが存在しません。</p>
    </c:otherwise>
</c:choose>

<jsp:include page="../tool/footer.html" />
