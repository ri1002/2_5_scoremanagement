<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset='UTF-8'>
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
            padding: 0.5rem;
            font-size: small;
        }

        #subleft, #subright {
            margin: 0 0.5rem;
            margin-top: 0.5rem;
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
        #menu ul {
    		list-style: none;
		}

		#menu .menu-label {
    		font-weight: bold;
		    margin-top: 1rem;
		}

		#menu .submenu {
	    	margin-left: 1rem;
		    list-style: none;
		    padding-left: 1rem;
	    	border-left: 2px dotted #ccc;
		}

		#menu .submenu li {
	    	margin: 0.3rem 0;
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

        <div id="header">
            <div id="title"><h1>得点管理システム</h1></div>
            <div id="subInfo">
                <!-- ログイン状態の制御 -->
                <div id="subleft">
                    <c:if test="${not empty sessionScope.teacher}">
                        <span>${sessionScope.teacher.name} 様</span>
                    </c:if>
                    <c:if test="${empty sessionScope.teacher}">
                        <p>ログインしていません</p>
                    </c:if>
                </div>
                <!-- ログアウト・ログインの制御 -->
                <div id="subright">
                    <c:if test="${not empty sessionScope.teacher}">
                        <a href="../main/logout.jsp"><p>ログアウト</p></a>
                    </c:if>
                    <c:if test="${empty sessionScope.teacher}">
                        <a href="../login.jsp"><p>ログイン</p></a>
                    </c:if>
                </div>
            </div>
        </div>

</body>
</html>