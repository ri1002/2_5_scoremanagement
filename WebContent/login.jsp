<%-- ログイン --%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<jsp:include page="tool/login-header.html" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
	#login_button{
				border-radius: 5px;
			    display: block;
			    width: 200px;
			    padding: 15px;
			    box-sizing: border-box;
			    background: #6fa1ff;
			    color: #FFF;
			    text-decoration: none;
			    text-align: center;
			    margin: 10px auto;
			    }
	/* チェックボックスのデフォルトの見た目を隠す */
			#showPassword {
			    display: none;
			}

			/* チェックボックスラベルのスタイル */
			#showPassword + label {
			    position: relative;
			    padding-left: 20px; /* チェックボックスの左側の余白 */
			    cursor: pointer;
			    font-size: 16px;
			}

			/* チェックボックスのカスタム見た目 */
			#showPassword + label::before {
			    content: '';
			    position: absolute;
			    left: 0;
			    top: 0;
			    width: 15px;
			    height: 15px;
			    border: 2px solid #ccc;
			    border-radius: 4px;
			    background-color: white;
			    transition: all 0.3s ease;
			}

			/* チェックボックスがチェックされた時のカスタム見た目 */
			#showPassword:checked + label::before {
			    background-color: #1E90FF; /* チェック時の背景色 */
			    border-color:#1E90FF; /* チェック時の境界線色 */
			}

			/* チェックボックスがチェックされた時に中にチェックマークを表示 */
			#showPassword:checked + label::after {
			    content: '✔';
			    position: absolute;
			    left: 2px;
			    top: 1px;
			    color: white;
			}



</style>

<div id= "login">
	<div id = "login_border">
		<div id = "login_text">
			<h2>ログイン</h2>
		    <form action="${pageContext.request.contextPath}/main/LoginExecute.action" method="post">
		    	 <b>
		   			 <small>
		    			<%-- エラーメッセージの表示 --%>
						<%
						    String errorMessage = (String) session.getAttribute("error");
						    if (errorMessage != null) {
						        out.println("<p style='color:red;'>" + errorMessage + "</p>");
						        session.removeAttribute("error"); // メッセージを表示後にセッションから削除
						    }
						%>
					</small>
				</b>

				<input type="text" placeholder="ID" name="id" required id="id" maxlength="10"><br>
				<input type="password" placeholder="パスワード" name="password" required id="password" maxlength="30"><br>



				<input type="checkbox" id="showPassword" onclick="togglePassword()" class="accented">
        		<label for="showPassword">パスワードを表示する</label><br><br>

			<br>
	      	<input id="login_button" type="submit" value="ログイン">
	      	</form>
		</div>
	</div>
</div>
<script>
        function togglePassword() {
            const passwordInput = document.getElementById("password");
            passwordInput.type = (passwordInput.type === "password") ? "text" : "password";
        }
    </script>
<jsp:include page="tool/footer.html" />
