<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@page contentType="text/html; charset=UTF-8" %>
<jsp:include page="../tool/header.html" />
<jsp:include page="../tool/sidebar.html" />
<%@ page import="java.time.LocalDate"%>


<%-- 学生登録 --%>
<style>
	#filtering {
		padding: 10px;
		height: 12%;
	}
	#filtering select{
		width: 100%;
	}
	#filtering input {
		width: 100%;
	}

	input[type="text"], select {
        width: 100%;
        height: 30px;
        margin-top: 5px;
        padding: 0 5px;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-sizing: border-box;
    }

    button{
  		padding: 0;
  		border: none;
  		outline: none;
  		appearance: none;
		background: none;
  		cursor: pointer;
	}
	#filtering button {
  		color: #FFFFFF;
  		background: #808080;
 	 	display: inline-block;
 	 	width: 100px;
 	 	height: 30px;
 	 	min-width: 80px;        /* どんなに画面が狭くても最低100px */
    	max-width: 200px;
  		border-radius:5px;
  		text-align: center;
  		text-decoration: none;
	}
	#filtering button:hover {
  		color: #FFFFFF;
  		background: #d3d3d3;
	}
	.filtering_margin {
		margin-top: 10px;
	}

	.filtering_margin label {
		height: 30px;
	}

/* エラーメッセージのラッパー */
.error-wrapper {
    display: flex;
    justify-content: center;        /* メッセージを中央に配置 */
    align-items: center;
    margin-top: 5px;
}

/* エラーメッセージ自体 */
.error-message {
    position: relative;
    text-align: center;
    font-size: 14px;
    box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
    max-width: 300px;
    bottom: 5px;
    float: right;
    margin: 0 auto;
    padding: 5px;
    background: #fff;
    border: 2px solid #ddd;
    border-radius: 8px;
}

/* エラーメッセージアイコン */
.error-icon {
    background: #FFC800;
    color: #fff;
    font-weight: bold;
    width: 20px;
    height: 20px;
    display: inline-flex;
    align-items: center;
    justify-content: center;
    margin-left: 5px;
    font-size: 14px;
    position: relative;
}

/* エラーメッセージの矢印 */
.error-arrow {
    position: absolute;
    top: -6px;
    left: 10px;
    width: 12px;
    height: 12px;
    background: #fff;
    border-top: 2px solid #ddd;
    border-left: 2px solid #ddd;
    transform: translateX(-50%) rotate(45deg);
}

</style>

<h2>学生情報登録</h2>

<form action="StudentCreateExecute.action" method="post">
<<<<<<< HEAD
=======

<form action="student_create" method="post">
>>>>>>> branch 'master' of https://github.com/ri1002/2_5_scoremanagement.git
	<div id = "filtering">
		<div class="filtering_margin">
			<div id = "year_filter">
				<label>入学年度</label><br>
				<select name="ent_year">
					<option value="0">--------</option>
						<%int currentYear = LocalDate.now().getYear();
						String entYearParam = request.getParameter("ent_year");%>
						<% for (int i = currentYear - 10; i <= currentYear + 10; i++) {%>
        					<option value="<%= i %>"
        						<%= (String.valueOf(i).equals(entYearParam)) ? "selected" : "" %>><%= i %></option>
	    				<% } %>
				</select>
		</div>
<<<<<<< HEAD
=======

>>>>>>> branch 'master' of https://github.com/ri1002/2_5_scoremanagement.git
		<c:if test="${not empty student_ent_year}">
    		<p style="color:#FFCC33">${student_ent_year}</p>
		</c:if>
<<<<<<< HEAD
=======

		<br>

		<label>学生番号</label>
		<br>
		<input type="text" placeholder="学生番号を入力してください" name="no">
		<br>
		<label>氏名</label>
		<br>
		<input type="text" placeholder="氏名を入力してください" name="name">
		<br>

		<div id = "class_filter">
			<label>クラス</label><br>
			<select name="class_num">
				<c:forEach var="c" items="${studentClassList}">
					<option value="${c.class_num}">${c.class_num}</option>
				</c:forEach>
			</select>

>>>>>>> branch 'master' of https://github.com/ri1002/2_5_scoremanagement.git
		</div>

		<div class="filtering_margin">
			<label>学生番号</label>
			<input type="text" placeholder="学生番号を入力してください" name="no" value="${param.no}">
		</div>

	    <!-- 学生番号エラー -->
        <c:if test="${not empty student_number}">
	         <div class="error-wrapper">
                <div class="error-message">
                    <div class="error-arrow"></div>
                    <span class="error-icon">!</span>
                    ${student_number}
                </div>
            </div>
        </c:if>
		<c:if test="${not empty student_duplication}">
    		<p style="color:#FFCC33">${student_duplication}</p>
		</c:if>

		<div class="filtering_margin">
			<label>氏名</label>
			<input type="text" placeholder="氏名を入力してください" name="name" value="${param.name}">
		</div>
            <!-- 氏名エラー -->
            <c:if test="${not empty student_name}">
                <div class="error-wrapper">
                    <div class="error-message">
                        <div class="error-arrow"></div>
                        <span class="error-icon">!</span>
                        ${student_name}
                    </div>
                </div>
            </c:if>


		<div class="filtering_margin">
			<div id = "class_filter">
				<label>クラス</label><br>
				<select name="class_num">
					<c:forEach var="c" items="${class_num_set}">
						<option value="${c}"<c:if test="${param.class_num == c}">selected</c:if>>${c}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="filtering_margin">
			<div id = "create_end">
				<button type="submit" name="end">登録して終了</button>
			</div>
		</div>
		<div class="filtering_margin">
			<a href="http://localhost:8080/scoremanagement/main/StudentList.action">戻る</a>
		</div>
	</div>
</form>

<jsp:include page="../tool/footer.html" />