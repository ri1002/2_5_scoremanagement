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

	#filter_filter button {
  		color: #FFFFFF;
  		background: #F7B2CF;
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
  		background: #F7D1E1;
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

<h2>科目情報登録</h2>

<form action="SubjectDeleteExecute.action" method="post">


        <label>${subject.name}を削除してもよろしいですか</label><br>
        <input type="hidden" name="subject_cd" value="${subject.cd}">
		<input type="hidden" name="subject_name" value="${subject.name}">

        <div id = "filter_filter">
            <button type="submit" name="end">削除</button>
        </div>
</form>

<td><a href="http://localhost:8080/scoremanagement/main/SubjectList.action">戻る</a></td>



<jsp:include page="../tool/footer.html" />
