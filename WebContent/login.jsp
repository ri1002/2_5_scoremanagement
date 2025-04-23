<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<jsp:include page="/tool/header.html" />


<%-- ログイン --%>

<style>
  	#login{
    	width: 50%;
    	padding: 15px;
    	margin-left:auto;
    	margin-right:auto;
    }

    #login_border {
    	border: solid 2px #ddd;
    }

    #login h2 {
		background-color: #f5f5f5;
	}
    p {
    	margin-left:auto;
    	margin-right:auto;
    }
    #login input, #login button {
    	margin-top: 10px;
    	margin-left:auto;
    	margin-right:auto;
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
    #login_text {
    	padding: 0 0 10px 0;
    	margin-left:auto;
    	margin-right:auto;
    	text-align: center;
    }

</style>

<div id= "login">
	<div id = "login_border">
		<div id = "login_text">
			<h2>ログイン</h2>

			<input type="text" placeholder="ID"><br>
			<input type="password" placeholder="パスワード"><br>
			<label>
				<input type="checkbox"value="ID">パスワードを表示
			</label>
		<br>
			<button>ログイン</button>
		</div>
	</div>
</div>
<jsp:include page="/tool/footer.html" />