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
    <form method="post" action="/member/update/${member.email}">
      <div class="grid write">
          <label for="email">이메일</label>
          <input
          readonly="readonly"
          id="email"
          name="email"
          value="${member.email}"
          />
          <label for="name">이름</label>
          <input
            type="text"
            id="name"
            name="name"
            placeholder="이름을 입력하세요"
            value="${member.name}"
          />
        <label for="password">패스워드</label>
        <input
          type="password"
          id="password"
          name="password"
          placeholder="패스워드를 입력하세요"
          value="${member.password}"
        ></input>
        </div>
        <div class="btn-group">
          <div class="right-align">
            <input type="submit" value="저장" />
          </div>
        </div>
        </form>
    </div>
</body>
</html>