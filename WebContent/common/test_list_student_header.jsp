<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.time.LocalDate"%>
<%@page import="bean.ClassNum, java.util.*" %>
<%@page import="java.util.List" %>

<div class="student-info-header">
    <label for="studentId">学生番号</label>
    <input type="text" id="studentId" name="f4" value="${param.studentId}" placeholder="学生番号を入力してください" maxlength="10">
    <c:if test="${not empty error_student_no}">
        <div class="error-wrapper">
            <div class="error-message">
                <div class="error-arrow"></div>
                <span class="error-icon">!</span>
                ${error_student_no }
            </div>
        </div>
    </c:if>
</div>