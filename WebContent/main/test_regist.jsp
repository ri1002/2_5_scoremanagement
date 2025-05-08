<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../tool/sidebar.html" />
<%@ page import="java.time.LocalDate"%>
<%@page import="bean.ClassNum, java.util.*" %>
<%@page import="java.util.List" %>

<style>

	#sheader { padding: 0.5em 1em;
    	/*margin: 2em 0;
	    font-weight: bold;
    	border: solid 1px;線*/
	    /*border-radius: 10px;角の丸み*/

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
<div id = "sheader">
	<form action="TestRegist.action" method="post">
		<jsp:include page="/common/test_header.jsp" />
		<button type="submit">検索</button>
		<!-- エラーメッセージの表示 -->

		<c:if test="${not empty errors['errors']}">
		    <p style="color:red;">${errors['errors']}</p>
		</c:if>
	</form>
</div>
<c:choose>
	<c:when test="${tests.size() > 0 }">

	<p>検索結果:${tests.size()}件</p>

	<table border="1">
			<tr>
            	<th><label>入学年度</label></th>
            	<th><label>クラス</label></th>
         	   	<th><label>氏名</label></th>
            	<th><label>点数</label></th>
        	</tr>
        <c:forEach var="test" items="${tests}">
        	<tr>
	            <td>${test.student.entYear}</td>
    	        <td>${test.student.classNum}</td>
        	    <td>${test.student.name}</td>
            	<td>
            		<input type="text" name="point_${test.student.no}" value="${test.point}">
            	</td>
           	</tr>
        </c:forEach>
    </table>
	</c:when>
<c:otherwise>
    <p>学生データが存在しません。</p>
</c:otherwise>
</c:choose>


<jsp:include page="../tool/footer.html" />
