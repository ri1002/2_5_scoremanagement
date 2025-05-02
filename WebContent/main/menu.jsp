<%@page contentType="text/html; charset=UTF-8" %>
<jsp:include page="../tool/header.html" />
<jsp:include page="../tool/sidebar.html" />

<style>
	#main_menu {
		padding: 10px;
				display: flex;
		justify-content: space-between;  /* 横に並べる & 間隔を調整 */
	}
	#main_menu_student, #main_menu_grades, #main_menu_subject {
		flex: 1;                         /* 横幅を均等に */
		margin: 0 5px;                   /* 間に隙間を追加 */
		padding: 10px;
		font-size: 20px;
		text-align: center;
		display: flex;                   /* 中の文字を上下中央揃え */
		flex-direction: column;
		justify-content: center;
		align-items: center;
		min-height: 150px;               /* 縦の最低サイズを揃える */
		box-sizing: border-box;          /* paddingを含めて幅を計算 */
	}
	#main_menu_student {
		background: #DDBBBB;
		}
	#main_menu_grades {
		background: #BBDDBB;
	}
	#main_menu_subject {
		background: #BBBBDD;
	}
</style>

<h2>メニュー</h2>
<div id="main_menu">
	<div id="main_menu_student">
		<a href="http://localhost:8080/scoremanagement/main/StudentList.action">学生管理</a>
	</div>

	<div id="main_menu_grades">
		成績管理<br>
		<a href="http://localhost:8080/scoremanagement/main/TestRegist.action">成績登録</a><br>
		<a href="http://localhost:8080/scoremanagement/main/TestList.action">成績参照</a>
	</div>
	<div id="main_menu_subject">
		<a href="todo">科目管理</a>
	</div>
	<hr>
</div>
<jsp:include page="../tool/footer.html" />