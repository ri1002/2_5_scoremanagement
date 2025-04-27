<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="java.time.LocalDate"%>
<jsp:include page="../tool/header.html" />
<jsp:include page="../tool/sidebar.html" />

<style>
	/*全体の要素外に上下左右10pxの余白*/
    #filtering {
        margin: 10px;
    }
    .name_class {
    	position: relative; /* これを追加するだけ！ */
	}

    /*氏名とクラスのテキストボックス*/
    .name_class input,
	.name_class select {
		width: 100%;			/*横幅いっぱいに広げる*/
    	height: 30px;			/*縦幅を30pxにする*/
    	margin-top: 5px;		/*上の要素外に5pxの余白*/
    	border-radius: 5px;		/*ボックスの角を丸くする*/
		border: 1px solid #ccc;	/*ボックスの線をつける*/
		padding: 0 5px;			/*要素内の上下は0、左右は5pxの余白を指定*/
		box-sizing: border-box; /*パディングやボーダーを含めて、高さに収める*/

    }
    /*それぞれの要素外の上に5pxの余白*/
    .input-container {
    	margin-top: 5px;
    }

    .class_margin {
    	margin-top: 30px;

    }

    /*入学年度と学生番号の値*/
    .student-value {
    	margin-top: 10px;	/*要素外の上に10px*/
    	margin-left: 30px;	/*要素外の左に30px*/
    }



	/* ボタンのスタイル */
	button{
  		padding: 0;		/*要素内の余白0*/
  		border: none;	/*線なし*/
  		outline: none;	/* フォーカス時のアウトラインをなくす */
  		appearance: none;/* デフォルトの見た目を解除 */
		background: none;/* 背景なし */
  		cursor: pointer;/* カーソルをポインターに変更 */
	}

	/* 変更ボタンのデザイン */
	#filtering button {
  		color: #FFFFFF;			/*文字の色「白」*/
  		background: #0066FF;	/*背景色「青」*/
 	 	display: inline-block;
 	 	width: 60px;
 	 	height: 35px;
  		margin: 10px;			/*要素外の上下左右に10px*/
  		border-radius:5px;		/* 角を丸く */
  		text-align: center;		/* テキスト中央揃え */
	}


	/* エラーメッセージのラップ部分 */
	.error-wrapper {
		position: relative; 	/*親要素を相対位置に設定*/
		display: flex;			/*フレックスボックスを使って中央揃え*/
		justify-content: center;/*中央揃えにする*/
		/*margin: 30px 0 0 0;*/			/*要素外の上下に10px*/
	}

	/* エラーメッセージのスタイル */
	.error-message {
		position: relative;
		text-align: center;			/*テキストを中央揃えにする*/
		border: solid 2px #ddd;		/* 灰色の枠線 */
		border-radius: 8px;			/* 角を丸く */
		box-shadow: 0px 2px 5px rgba(0,0,0,0.1);/* 影をつける */
		padding: 5px;				/*内部余白10px*/
		font-size: 14px;			/* フォントサイズ */
		max-width: 300px;			/* 最大幅300px */
		background: #FFFFFF;		/*背景色「白」*/
		z-index: 0;                 /* アイコンを矢印の上に持ってくるため、枠線の重なり順を0に */
	}

	/* エラーメッセージ内のアイコン */
	.error-icon {
		background: #FFC800;	/*背景色「黄色」*/
		color: 		#FFFFFF;	/*文字の色「白」*/
		font-weight: bold;		/*文字を太字に設定*/
		width: 20px;			/*横幅20px*/
		height: 20px;			/*縦幅20px*/
		display: inline-flex;
		align-items: center;	/*アイコン内で中央揃え*/
		justify-content: center;/*アイコン内で中央揃え*/
		margin-left: 5px;		/*要素外の左に余白5px*/
		font-size: 14px;		/*アイコンの文字サイズを14pxに設定*/
		position: relative;      /* アイコンに位置を設定 */
		z-index: 2;              /* アイコンを矢印より前に持ってくる */
	}

	/* エラーメッセージの矢印 */
	.error-arrow {
		position: absolute;
		top: -6px; 					/* エラーボックスの中に食い込ませる */
		transform: translateX(-50%) rotate(45deg);/* 左右中央に回転させる */
		width: 12px;				/*矢印の横幅12px*/
		height: 12px;				/*矢印の縦幅12px*/
		padding: 5px;
		border-top: 2px solid #ddd;	/*上辺の枠線 */
		border-left: 2px solid #ddd;/*左辺の枠線 */
		background: white;			/*背景色「白」*/
		left: 10px;                 /* 10px右に移動 */
		z-index: 1;                 /* 矢印の重なり順を1に */
		}



</style>


<h2>学生情報登録</h2>

<form action="StudentUpdateExecute.action" method="post">
<div id = "filtering">
	<div class = "input-container block">
		<label>入学年度</label>
		<input type="hidden" name="ent_year" value="${student.entYear}">
		<div class="student-value">
			${student.entYear}
		</div>
	</div>

	<div class = "input-container block">
		<label>学生番号</label>
		<input type="hidden" name="no" value="${student.no}">
		<div class="student-value">
			${student.no}
		</div>
	</div>

	<!-- 氏名とクラス -->
	<div class = "input-container block">
		<label>氏名</label>
		<div class = "name_class">
			<input type="text" name="name" placeholder="氏名を入力してください" value="${empty student_name ? student.name : ''}">
		</div>
	</div>

	<!-- エラーがある場合の処理 -->
	<c:if test="${not empty student_name}">
		<div class="error-wrapper">
			<div class="error-message">
			<div class="error-arrow"></div>
  				<span class="error-icon">!</span>
				${student_name}
  			</div>
  		</div>
	</c:if>

	<div class = "input-container block">
		<label class="class_margin">クラス</label>
		<div class = "name_class">
			<select name="class_num">
				<c:forEach var="c" items="${class_num_set}">
					<option value="${c}" <c:if test="${c == student.classNum}">selected</c:if>>${c}</option>
				</c:forEach>
			</select>
		</div>
	</div>

	<!-- チェックボックスの部分 -->
	<div class="input-container block">
		<label class="display-inline">在学中
			<input type="checkbox" name="is_attend" <c:if test="${student.attend}">checked</c:if>>
		</label>
	</div>

	<!-- 変更ボタン -->
	<div class="input-container block">
		<button type="submit" name="login">変更</button>
	</div>

	<!-- 戻るリンク -->
	<div class="input-container block">
		<a href="http://localhost:8080/scoremanagement/main/StudentList.action">戻る</a>
	</div>
</div>
</form>


<jsp:include page="../tool/footer.html" />