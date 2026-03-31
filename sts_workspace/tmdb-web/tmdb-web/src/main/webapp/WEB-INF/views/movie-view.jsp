<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화 ${movie.movieId}</title>
<link rel="stylesheet" type="text/css" href="/css/tmdb-main.css" />
</head>
<body>
    <h1>게시글 내용 조회</h1>
    <div class="grid view">
      <span>영화 ID</span>
      <div>${movie.movieId}</div>

      <span>포스터 URL</span>
      <div>${movie.posterUrl}</div>

      <span>영화 제목</span>
      <div>${movie.title}</div>

      <span>관람등급</span>
      <div>${movie.movieRating}</div>

      <span>개봉날짜</span>
      <div>${movie.openDate}</div>

      <span>개봉국가</span>
      <div>${movie.openCountry}</div>

      <span>상영시간</span>
      <div>${movie.runningTime}</div>

      <span>소개글</span>
      <!-- <pre> ==> Presentation -->
      <pre>${movie.introduce}</pre>

      <span>개요</span>
      <!-- <pre> ==> Presentation -->
      <pre>${movie.synopsis}</pre>

      <span>원제</span>
      <!-- <pre> ==> Presentation -->
      <pre>${movie.originalTitle}</pre>

      <span>상태</span>
      <!-- <pre> ==> Presentation -->
      <pre>${movie.state}</pre>

      <span>원어</span>
      <!-- <pre> ==> Presentation -->
      <pre>${movie.language}</pre>

      <span>제작비</span>
      <!-- <pre> ==> Presentation -->
      <pre>${movie.budget}</pre>

      <span>수익</span>
      <!-- <pre> ==> Presentation -->
      <pre>${movie.profit}</pre>

      <div class="btn-group">
        <div class="right-align">
            <a href="/update/${movie.movieId}">수정</a>
            <a href="/delete?id=${movie.movieId}">삭제</a>
        </div>
      </div>
    </div>
</body>
</html>