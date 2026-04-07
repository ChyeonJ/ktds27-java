<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>${param.title}</title>
<script type="text/javascript" src="/js/jquery-4.0.0.slim.min.js"></script>
${param.scripts}
<link rel="stylesheet" type="text/css" href="/css/hello-spring.css">
</head>
<body>
	<div class="wraper">
		<div class="header">
			<!-- Session Scope안에 Login Data가 없다면 -->
			<c:choose>
				<c:when test="${empty sessionScope.__LOGIN_DATA__}">
					<!-- 로그인 안했을 때의 링크 시작 -->
					<a href="/regist">회원가입</a>
					<a href="/login">로그인</a>
					<!-- 로그인 안했을 때의 링크 끝 -->
				</c:when>
				<c:otherwise>
					<!-- 로그인 안했을 때의 링크 시작 -->
					<div>${sessionScope.__LOGIN_DATA__.name}
						${sessionScope.__LOGIN_DATA__.email}</div>
					<a href="/member/${sessionScope.__LOGIN_DATA__.email}">마이페이지</a>
					<a href="/logout">로그아웃</a>
					<!-- 로그인 안했을 때의 링크 끝 -->
				</c:otherwise>
			</c:choose>
		</div>