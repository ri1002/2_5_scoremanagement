<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../tool/sidebar.html" />
<%@ page import="java.time.LocalDate"%>
<%@page import="bean.ClassNum, java.util.*" %>
<%@page import="java.util.List" %>

<style>
	#sheader {
    	padding: 0.5em 1em;
	    display: flex;
	    justify-content: space-around;
    	align-items: center;
	    flex-wrap: wrap;
    	margin: 10px;
    	padding: 10px;
    	border: solid 1px #ddd;
    	border-radius:5px;
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
  	button:hover {
  		color: #FFFFFF;
  		background: #d3d3d3;
	}
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
</style>

<h2>成績管理</h2>

<div id="sheader">
	<form action="TestRegist.action" method="post">
		<jsp:include page="/common/test_header.jsp" />
		<button type="submit" name="search">検索</button>

		<!-- エラーメッセージの表示 -->
		<c:if test="${param.search != null and not empty errors}">
    		<p style="color:red;">${errors}</p>
		</c:if>
	</form>
</div>

<c:choose>
	<c:when test="${tests.size() > 0}">
		<label>科目: ${selectedSubject.name}(${f4}回)</label>

		<!-- TestRegistExecute.actionに入学年度、クラス、氏名、点数を送信 -->
		<form action="TestRegistExecute.action" method="post">
			<table border="1">
				<tr>
	            	<th><label>入学年度</label></th>
	            	<th><label>クラス</label></th>
	            	<th><label>学生番号</label></th>
	         	   	<th><label>氏名</label></th>
	            	<th><label>点数</label></th>
	        	</tr>

	        	<!-- 検索結果の生徒を表示 -->
	        	<c:forEach var="test" items="${tests}">
    <tr>
        <td>${test.student.entYear}</td>
        <td>${test.student.classNum}</td>
        <td>${test.student.no}</td>
        <td>${test.student.name}</td>
        <td>
            <input type="text" name="point" value="${test.point != null ? test.point : ''}" size="4" maxlength="3" />
        </td>
        <td>
            <input type="hidden" name="regist" value="${test.student.no}" />
            <input type="hidden" name="count" value="${test.no}" />
            <input type="hidden" name="subject" value="${test.subject.cd}" />
        </td>
    </tr>
</c:forEach>
		<c:if test="${not empty error}">
    		<p style="color:orange">${error}</p>
		</c:if>
	    	</table>
	    	<button>登録して終了</button>
	    </form>
	</c:when>
	<c:otherwise>
	    <p>学生データが存在しません。</p>
	</c:otherwise>
</c:choose>

<jsp:include page="../tool/footer.html" />
