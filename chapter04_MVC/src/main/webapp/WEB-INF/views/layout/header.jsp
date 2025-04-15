<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<<<<<<< HEAD
    
=======
<%@ taglib uri = "http://www.springframework.org/security/tags" prefix = "sec" %>  
>>>>>>> 443ad65 (추가)
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="#">
<link rel="stylesheet" href="/resources/css/header.css">
<title>MemberBoard</title>
</head>
<body>
	<div class="wrap">
		<div class="header">
			<div class="header-title">
<<<<<<< HEAD
				<a href="mainPage">MemberBoard</a>
			</div>
			<div class="header-member">
				<button type="button" class="header-btn" onclick="loginPage()">로그인</button>
				<button type="button" class="header-btn" onclick="joinPage()">회원가입</button>
=======
				<a href="/">MemberBoard</a>
			</div>
			<div class="header-member">
			<sec:authorize access="!isAuthenticated()">
				<button type="button" class="header-btn" onclick="loginPage()">로그인</button>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<button type="button" class="header-btn" onclick="logoutPage()">로그아웃</button>
			</sec:authorize>
				<button type="button" class="header-btn" onclick="joinPage()">회원가입</button>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<button type="button" class="header-btn" onclick="adminPage()">관리자 페이지</button>
			</sec:authorize>
>>>>>>> 443ad65 (추가)
			</div>
			<div class="menu">
				<a href="boardList">게시판</a>
			</div>
		</div>
<<<<<<< HEAD
	<div class="main">
=======
	<div class="main">
	<script type="text/javascript" src="/resources/js/header.js"></script> 
>>>>>>> 443ad65 (추가)
