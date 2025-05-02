<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.time.LocalDate"%>
<%@page import="bean.ClassNum, java.util.*" %>
<%@page import="java.util.List" %>

	<p>学生情報</p>
		<label for="studentId">学生番号</label>
		<input type="text" id="studentId" name="f4" value="${param.studentId}" placeholder="学生番号を入力してください">
		<p class="error-message">${errorMessage}</p>