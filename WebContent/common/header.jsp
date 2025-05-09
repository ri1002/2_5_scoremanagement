<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
			<meta charset = 'UTF-8'>
			<meta name="viewport" content="width=device-width, initial-scale=1.0">
			<title>score manager</title>
		<style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        #wrapper {
            display: flex;
            flex-direction: column;
            height: 100vh;
            max-width: 1200px; /* 横幅を最大1200pxに制限 */
    		margin: 0 auto;
        }

        #header {
            height: 15%;
            background-color: #E3EDFF;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 1rem 3rem;
        }

        #title h2 {
            font-size: xx-large;
        }

        #subInfo {
            display: flex;
            align-items: center;
            background-color: lemonchiffon;
            padding: 0.5rem;
            font-size: small;
        }

        #subleft, #subright {
            margin: 0 0.5rem;
        }

        #bodyArea {
            display: flex;
            flex: 1;
            padding: 1rem;
        }

        #menu {
            width: 200px;
            padding: 1rem;
            border-right: 2px solid #ddd;
        }

        #menu div {
            margin: 1rem 0;
        }

        #menu a {
            text-decoration: none;
            font-size: 1.1rem;
        }
        #score_menu {
        	padding: 0 0 0 10px;
        }

        #main_screen {
            flex: 1;
            padding: 1rem;
            background-color: #fff;
        }
        #main_screen h2 {
        	background-color: #f5f5f5;
        	padding-left: 20px;
        }

        #footer {
            background-color: whitesmoke;
            text-align: center;
            padding: 1rem;
        }
    </style>
	</head>
<body>
	<div id = "wrapper">
		<div id = "header">
			<div id = "title"><h2>得点管理システム</h2></div>
			<div id = "subInfo">
				<!-- ログイン?顧客名※(todo)JSPで出力制御 -->
				<div id = "subleft">
						<c:if test="${not empty sessionScope.teacher}">
    						<p>${sessionScope.teacher.name} さん</p>
						</c:if>
						<c:if test="${empty sessionScope.teacher}">
						    <p>ログインしていません</p>
						</c:if>
				</div>
					<!-- ログアウト?非表示※(todo)JSPで出力制御 -->
					<div id = "subright">
						<a href="../login.jsp"><p>ログ</p></a></div>
			</div>
		</div>
