<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.time.LocalDate" %>
<jsp:include page="../tool/header.html" />
<jsp:include page="../tool/sidebar.html" />

<style>
    /* --- フォームレイアウト系 --- */
    #filtering {
        margin: 10px;
    }

    .input-container {
        margin-top: 10px;
        position: relative;  /*エラーメッセージを配置するために親要素に相対位置を指定 */
    }

    label {
        display: block;
        margin-bottom: 5px;
    }

    .name_class {
        position: relative;
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

    .student-value {
        margin-top: 5px;
        margin-left: 30px;
    }

    /* --- ボタン系 --- */
    button {
        padding: 0;
        border: none;
        background: none;
        cursor: pointer;
        appearance: none;
    }

    #filtering button {
        width: 60px;
        height: 35px;
        margin: 10px;
        color: #fff;
        background: #0066FF;
        border-radius: 5px;
        text-align: center;
    }

	/*ボタンにカーソルを合わせた際の挙動*/
    #filtering button:hover {
    background: #3399FF; /* ← 薄めの青 */
	}


    .overlap {
    position: relative;
    }

	/* エラーメッセージラッパー */
	.error-wrapper {
    	position: relative;
	    top: 18px; /* フィールドの中央に配置 */
    	left: 10px; /* 左側に少し寄せる */
	    transform: translateY(-50%); /* 垂直方向の中央に配置 */
    	display: flex;
    	justify-content: center; /* 中央に配置 */
    	align-items: center;
    	z-index: 1; /* 他の要素より前面に表示 */
	}

	/* エラーメッセージのスタイル */
	.error-message {
    	position: relative;
	    text-align: center;
    	background: #fff;
    	border: 2px solid #ddd;
    	border-radius: 8px;
    	padding: 5px;
    	font-size: 14px;
    	box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
    	max-width: 300px;
    	z-index: 2;
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
    	z-index: 1;
	}

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
    	z-index: 2;
	    position: relative;
	}

</style>

<h2>学生情報登録</h2>

<%-- StudentUpdateExecuteActionに送信 --%>
<form action="StudentUpdateExecute.action" method="post">
    <div id="filtering">
        <!-- 入学年度 -->
        <div class="input-container">
            <label>入学年度</label>
            <input type="hidden" name="ent_year" value="${student.entYear}">
            <%--student.entYearで学生の入学年度を表示 --%>
            <div class="student-value">${student.entYear}</div>
        </div>

        <!-- 学生番号 -->
        <div class="input-container">
            <label>学生番号</label>
            <input type="hidden" name="no" value="${student.no}">
            <%--student.entYearで学生の学生番号を表示 --%>
            <div class="student-value">${student.no}</div>
        </div>

        <!-- 氏名 -->
        <div class="input-container">
            <div class="overlap">
            	<label>氏名</label>
            </div>
            <div class="name_class">
            	<%--もし student_name が空（つまりバリデーションエラーがない）なら student.name を入力欄に表示、
					そうじゃないなら空欄にしておく（エラー時にフォームの再入力を促すため） --%>
                <input type="text" name="name" placeholder="氏名を入力してください" value="${empty student_name ? student.name : ''}">
            </div>

            <!-- 氏名エラー -->
            <c:if test="${not empty student_name}">
                <div class="error-wrapper">
                    <div class="error-message">
                        <div class="error-arrow"></div>
                        <span class="error-icon">!</span>
                        ${student_name}<%--エラーメッセージ（このフィールドを入力してください） --%>
                    </div>
                </div>
            </c:if>
        </div>

	<!-- クラス -->
	<div class="input-container">
    	<div class="overlap">
    		<label>クラス</label>
	    </div>
    	<div class="name_class">
        	<select name="class_num">
            	<c:forEach var="c" items="${class_num_set}">
            		<%--エラーが起きた際選択したクラスの保持をする --%>
                	<option value="${c}" <c:if test="${c == student.classNum}">selected</c:if>>${c}</option>
	            </c:forEach>
    	    </select>
    	</div>



        <!-- 在学中 -->
        <div class="input-container">
            <label>在学中
                <input type="checkbox" name="is_attend" <c:if test="${student.attend}">checked</c:if> value="true">
            </label>
        </div>

        <!-- 変更ボタン -->
        <div class="input-container">
            <button type="submit" name="login">変更</button>
        </div>

        <!-- 戻るリンク -->
        <div class="input-container">
            <a href="http://localhost:8080/scoremanagement/main/StudentList.action">戻る</a>
        </div>
    </div>

    </div>

</form>


<jsp:include page="../tool/footer.html" />
