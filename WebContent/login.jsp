<%-- ログイン --%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<jsp:include page="/tool/header.html" />

<style>
  	#login {
    	width: 50%; /* 幅を設定 */
    	padding: 15px; /* 内側の余白 */
    	margin-left: auto;
    	margin-right: auto;
    }

    #login_border {
    	border: solid 2px #ddd; /* 枠線 */
    	padding: 20px; /* 内側の余白 */
    	border-radius: 10px; /* 角を丸くする */
    }

    #login h2 {
		background-color: #f5f5f5;
		padding: 10px;
		font-size: 24px; /* フォントサイズを大きく */
		text-align: center;
	}
	p {
    	margin-left:auto;
    	margin-right:auto;
    }

    #login input, #login button {
    	margin-top: 10px;
    	margin-left: auto;
    	margin-right: auto;
    	display: block; /* 中央寄せ */
    }

    #login input[type="text"] ,#login input[type="password"] {
	    width: 80%;
	    height: 40px;
    	background-color: #E3EDFF;  /* 背景色を変更 */
	    color: #333;  /* 文字色を変更 */
    	padding: 10px;
    	padding-top: 5px; /* 上部の余白を設定 */
    	padding-left: 5px; /* 左部の余白を設定 */
    	border: 1px solid #ccc;
    	border-radius: 5px;
    	text-align: left;  /* 左寄せ */
    	vertical-align: top; /* 上寄せ */
    }

    #login button {
    	width: 50%; /* 幅 */
    	height: 40px; /* 高さ */
    	background-color: dodgerblue; /* 背景色 */
    	color: white; /* 文字色 */
    	font-size: 18px; /* フォントサイズ */
    	border: none;
    	border-radius: 5px; /* 角を丸く */
    	cursor: pointer;
    }

    #login_text {
    	padding: 0 0 10px 0;
    	margin-left: auto;
    	margin-right: auto;
    	text-align: center;
    }

    label {
    	display: flex; /* フレックスボックスで配置 */
    	align-items: center; /* 垂直方向に中央寄せ */
    	gap: 5px; /* チェックボックスと文字の間隔を設定 */
    }

    label span {
        margin-left: 150px; /* 「パスワードを表示」の文字を少し左に移動 */
    }
</style>

<div id="login">
	<div id="login_border">
		<div id="login_text">
			<h2>ログイン</h2>
			<input type="text" placeholder="ID"><br>
			<input type="password" id="password" placeholder="パスワード"><br>
			<label style="display: flex; align-items: center;">
    <span style="margin-right: -400px;">パスワードを表示</span>
    <input type="checkbox" id="togglePassword">
</label>
			<br>
			<button>ログイン</button>
		</div>
	</div>
</div>

<script>
	// チェックボックスの状態に基づいてパスワードの表示を切り替える
	document.getElementById('togglePassword').addEventListener('change', function() {
		const passwordField = document.getElementById('password');
		// チェック時にはパスワードを表示（テキスト型に変更）
		if (this.checked) {
			passwordField.type = 'text';
		} else {
			// 未チェック時には非表示（パスワード型に戻す）
			passwordField.type = 'password';
		}
	});
</script>

<jsp:include page="/tool/footer.html" />