<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Moive 등록</title>
<link rel="stylesheet" type="text/css" href="/css/tmdb-main.css"/>
</head>
<body>
    <h1>영화 등록</h1>
    <!-- action은 form 내부의 value를 전송할 엔드포인트 -->
    <form method="post" action="/update/${movie.movieId}">
        <label for="posterUrl">영화 아이디</label>
        <input
          type="text"
          id="posterUrl"
          name="posterUrl"
          readonly="readonly"
          value="${movie.movieId}"
        />
      <div class="grid write">
        <label for="posterUrl">포스터URL</label>
        <input
          type="text"
          id="posterUrl"
          name="posterUrl"
          placeholder="포스터 URL 입력"
          value="${movie.posterUrl}"
        />
        <label for="title">영화 제목</label>
        <input
          type="text"
          id="title"
          name="title"
          placeholder="영화 제목을 입력하세여"
          value="${movie.title}"
        />
        <label for="movieRating">관람 등급</label>
        <input
          type="text"
          id="movieRating"
          name="movieRating"
          placeholder="관람 등급을 입력하세여"
          value="${movie.movieRating}"
        />
        <label for="openDate">개봉날짜</label>
        <input
          type="date"
          id="openDate"
          name="openDate"
          placeholder="개봉날짜를 입력하세여"
          value="${movie.openDate}"
        />
        <label for="openCountry">개봉국가</label>
        <input
          type="text"
          id="openCountry"
          name="openCountry"
          placeholder="개봉국가를 입력하세여"
          maxlength="2"
          value="${movie.openCountry}"
        />
        <label for="runningTime">상영시간</label>
        <input
          type="number"
          id="runningTime"
          name="runningTime"
          placeholder="상영시간을 입력하세여"
          value="${movie.runningTime}"
        />
        <label for="introduce">소개글</label>
        <input
          type="text"
          id="introduce"
          name="introduce"
          placeholder="소개글을 입력하세여"
          value="${movie.introduce}"
        />
        <label for="synopis">개요</label>
        <input
          type="text"
          id="synopis"
          name="synopis"
          placeholder="개요를 입력하세여"
          value="${movie.synopis}"
        />
        <label for="originalTitle">원제</label>
        <input
          type="text"
          id="originalTitle"
          name="originalTitle"
          placeholder="원제를 입력하세여"
          value="${movie.originalTitle}"
        />
        <label for="state">상태</label>
        <input
          type="text"
          id="state"
          name="state"
          placeholder="상태를 입력하세여"
          maxlength="5"
          value="${movie.state}"
        />
        <label for="language">원어</label>
        <input
          type="text"
          id="language"
          name="language"
          placeholder="원어를 입력하세여"
          value="${movie.language}"
        />
        <label for="budget">제작비</label>
        <input
          type="number"
          id="budget"
          name="budget"
          placeholder="제작비를 입력하세여"
          value="${movie.budget}"
        />
        <label for="profit">수익</label>
        <input
          type="number"
          id="profit"
          name="profit"
          placeholder="수익을 입력하세여"
          value="${movie.profit}"
        />
        <div class="btn-group">
          <div class="right-align">
            <input type="submit" value="저장" />
          </div>
        </div>
      </div>
    </form>
</body>
</html>