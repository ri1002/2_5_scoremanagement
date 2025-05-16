<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html; charset=UTF-8" %>
<jsp:include page="../tool/header.html" />
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

</style>

<h2>科目情報登録</h2>

<form action="subject_create" method="post">


        <label>科目コード</label><br>

        <input type="hidden" name="code"><br>

        <label>科目名</label><br>
        <input type="text" placeholder="科目名を入力してください" name="name"><br>

        <div id="create_end">
            <button type="submit" name="end">変更</button>
        </div>
    </div>
</form>

<jsp:include page="../tool/footer.html" />
