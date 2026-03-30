<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Members SelectOne</title>
<link rel="stylesheet" type="text/css" href="/css/hello-spring.css"/>
</head>
<body>
    <h1>회원 한 명 조회</h1>
    <div class="grid view">
      <span>이메일</span>
      <div>${member.email}</div>

      <span>이름</span>
      <div>${member.name}</div>

      <span>비밀번호</span>
      <div>${member.password}</div>
      </form>
      <div class="btn-group">
        <div class="right-align">
            <a href="/member/update/${member.email}">수정</a>
            <a href="/member/delete?id=${member.email}">삭제</a>
        </div>
      </div>
    </div>
</body>
</html>