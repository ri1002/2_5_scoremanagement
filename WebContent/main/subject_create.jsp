<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html; charset=UTF-8" %>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../tool/sidebar.html" />
<%@ page import="java.time.LocalDate"%>

<%-- 科目登録フォーム --%>
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
  		background: #78AFF7;
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
  		background: #75A9FF;
    	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#75A9FF), color-stop(100%,#ffc7af));
    	background: -webkit-linear-gradient(top,  #75A9FF 0%, #ffc7af 100%);
    	background: linear-gradient(to #75A9FF 0%, #ffc7af 100%);
    	color: #fff;
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

<h2>科目情報登録</h2>

<form action="SubjectCreateExecute.action" method="post">


        <label>科目コード</label><br>
        <input type="text" placeholder="科目コードを入力してください" name="cd"><br>
            <!-- 科目コードエラー -->
            <c:if test="${not empty error_subject_cd}">
                <div class="error-wrapper">
                    <div class="error-message">
                        <div class="error-arrow"></div>
                        <span class="error-icon">!</span>
                       		${error_subject_cd}
                       		<%--エラーメッセージ（このフィールドを入力してください） --%>
                    </div>
                </div>
            </c:if>

		<%--科目コードが3文字以外の場合エラー表示 --%>
		<c:if test="${not empty error_subject_cd_number}">
    		<p style="color:#FFCC33">${error_subject_cd_number}</p>
		</c:if>

		<%--科目が重複している場合エラー表示 --%>
		<c:if test="${not empty subject_duplication}">
    		<p style="color:#FFCC33">${subject_duplication}</p>
		</c:if>


        <label>科目名</label><br>
        <input type="text" placeholder="科目名を入力してください" name="name"><br>

            <!-- 氏名エラー -->
            <c:if test="${not empty error_subject_name}">
                <div class="error-wrapper">
                    <div class="error-message">
                        <div class="error-arrow"></div>
                        <span class="error-icon">!</span>
                       	${error_subject_name }<%--エラーメッセージ（このフィールドを入力してください） --%>
                    </div>
                </div>
            </c:if>

        <div id = "filter_filter">
            <button type="submit" name="end">登録して終了</button>
        </div>
</form>

<jsp:include page="../tool/footer.html" />