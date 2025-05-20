<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@page import="bean.Subject" %>
<%@page import="java.util.List" %>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../tool/sidebar.html" />
 
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
 
<h2>科目一覧</h2>
<div id="new_registration">
<a href="http://localhost:8080/scoremanagement/main/SubjectCreate.action">新規登録</a>
</div>
 
<c:choose>
<c:when test="${not empty subjectList}">
 
        <table border="1">
<tr>
<th>科目コード</th>
<th>科目名</th>
<th> </th>
<th> </th>
</tr>
<c:forEach var="subj" items="${subjectList}">
<tr>
<td>${subj.cd}</td>
<td>${subj.name}</td>
<td><a href="http://localhost:8080/scoremanagement/main/SubjectUpdate.action?code=${subj.cd}">変更</a></td>
<td><a href="http://localhost:8080/scoremanagement/main/SubjectDelete.action?code=${subj.cd}">削除</a></td>
</tr>
</c:forEach>
</table>
</c:when>
<c:otherwise>
<p>科目データが存在しません!</p>
</c:otherwise>
</c:choose>
 
<jsp:include page="../tool/footer.html" />