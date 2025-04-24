<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../tool/header.html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ログイン</title>
    
   <style>
        #login1 {
        	background: #fff; 
        	font-size: 100%; 
        	padding-bottom:5px;
        	border-left: 1px #ccc solid;
        	border-right:1px #ccc solid;
        	border-top:1px #ccc solid;
        	padding-top:5px;
			text-align:center;
            background-color:whitesmoke;
            margin-right:200px;
            margin-left:200px;
            
            
        }
        #login2{
        	background: #fff;
        	font-size: 100%; 
        	padding-bottom:5px;
        	border-left: 1px #ccc solid;
        	border-right:1px #ccc solid;
        	border-bottom:1px #ccc solid;
        	padding-top:5px;
			text-align:center;
            margin-right:200px;
            margin-left:200px;
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
			    margin: 10px auto;			}
			#box{
			margin-top:20px;
			margin-bottom:20px;
			}
			#id{
				border-radius:3px;
			    display: inline-block;
			    width: 75%;
			    padding: 0.5em;
			    border: 1px solid #999;
			    box-sizing: border-box;
			    background: #f2f2f2;
			    margin: 0.5em 0;
}
			#password{
				border-radius:3px;
				display:
				inline-block;
			    width: 75%;
			    padding: 0.5em;
			    border: 1px solid #999;
			    box-sizing: border-box;
			    background: #f2f2f2;
			    margin: 0.5em 0;			}
			
    </style>
</head>
<body>
    <form action="LoginExecute.action" method="post">
    <div id="box">
    <div id="login1" >
    <h2>ログイン</h2>
    </div>
    <div id="login2">
        <label for="id">ID：</label>
        <input type="text" id="id" maxlength="10"placeholder="半角でご入力してください" size=30 required name="id"><br><br>
		
	    <label for="password">パスワード：</label>
	    <input type="password" id="password" maxlength="30" placeholder="30文字以内衣の半角英数字でご入力ください" required name="password"><br><br>

        <input type="checkbox" id="showPassword" onclick="togglePassword()">
        <label for="showPassword">パスワードを表示する</label><br><br>
        
      	<input id="login_button" type="submit" value="ログイン">
    	
    </div>
    
    </div>
    </form>

    <script>
        function togglePassword() {
            const passwordInput = document.getElementById("password");
            passwordInput.type = (passwordInput.type === "password") ? "text" : "password";
        }
    </script>
</body>
</html>


<%@include file ="../tool/footer.html" %>