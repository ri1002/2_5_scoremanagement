<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@page import="bean.Student" %>
<%@page import="java.util.List" %>
<jsp:include page="../tool/header.html" />
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


<h2>学生管理</h2>

<div id = "new_registration"><a href = "http://localhost:8080/scoremanagement/main/student_create.jsp">新規登録</a></div>

<form action="StudentList.action" method="post">

	<div id = "filtering">
		<div id = "year_filter">
			<label>入学年度</label><br>
			<select name="f1">
				<option value="">--------</option>
				<c:forEach  var="year" items="${YearList}">
        			<option value="${year}" <c:if test="${param.f1 == year }">selected</c:if>>${year}</option>
    			</c:forEach>
			</select>
		</div>
		<div id = "class_filter">
			<label>クラス</label><br>
			<select name="f2">
				<option value="">--------</option>
				<c:forEach var="s2" items="${studentList2}">
					<option value="${s2.class_num}" <c:if test="${param.f2 == s2.class_num }">selected</c:if>>${s2.class_num}</option>
				</c:forEach>
			</select>
		</div>

		<div id = "currently_enrolled_filter">
			<label>
				<input type="checkbox" name="f3" value="t"<c:if test="${param.f3 != null}">checked</c:if>>在学中
			</label>
		</div>

		<div id = "filter_filter">
			<button type="submit">絞込み</button>
		</div>
	</div>
</form>

<c:choose>
	<c:when test="${not empty filterList}">

	<p>検索結果:${filterList.size()}件</p>

	<table border="1">
        <tr>
            <th>入学年度</th>
            <th>学生番号</th>
            <th>氏名</th>
            <th>クラス</th>
            <th>在学中</th>
        </tr>
        <c:forEach var="filterlist" items="${filterList}">
            <tr>
                <td>${filterlist.entYear}</td>
                <td>${filterlist.no}</td>
                <td>${filterlist.name}</td>
                <td>${filterlist.classNum}</td>
                <td>
                	<c:choose>
                    	<c:when test="${filterlist.isAttend == true}">〇</c:when>
                    	<c:otherwise>×</c:otherwise>
                </c:choose>
               </td>
            </tr>
        </c:forEach>
    </table>
</c:when>
<c:when test="${not empty studentList1}">

	<p>検索結果:${studentList1.size()}件</p>

	<table border="1">
        <tr>
            <th>入学年度</th>
            <th>学生番号</th>
            <th>氏名</th>
            <th>クラス</th>
            <th>在学中</th>
        </tr>
        <c:forEach var="s1" items="${studentList1}">
            <tr>
                <td>${s1.entYear}</td>
                <td>${s1.no}</td>
                <td>${s1.name}</td>
                <td>${s1.classNum}</td>
                <td><c:choose>
                    <c:when test="${s1.isAttend}">〇</c:when>
                    <c:otherwise>×</c:otherwise>
                </c:choose></td>
            </tr>
        </c:forEach>
    </table>
</c:when>

<c:otherwise>
    <p>学生データが存在しません。</p>
</c:otherwise>
</c:choose>
<jsp:include page="../tool/footer.html" />