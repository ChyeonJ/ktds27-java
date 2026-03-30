<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie List</title>
<link rel="stylesheet" type="text/css" href="/css/tmdb-main.css"/>
</head>
<body>
    <h1>Movie 목록</h1>
    <div>총 ${searchCount}개의 게시글이 검색 됨</div>
    <div>아이디 : ${movieList[0].movieId}</div>
    <div>포스터 URL : ${movieList[0].posterUrl}</div>
    <div>영화 제목 : ${movieList[0].title}</div>
    <div>관람등급 : ${movieList[0].movieRating}</div>
    <div>개봉날짜 : ${movieList[0].openDate}</div>
    <div>개봉국가 : ${movieList[0].openCountry}</div>
    <div>상영시간 : ${movieList[0].runningTime} </div>
    <div>소개글 : ${movieList[0].introduce} </div>
    <div>개요 : ${movieList[0].synopis}</div>
    <div>원제 : ${movieList[0].originalTitle}</div>
    <div>상태 : ${movieList[0].state}</div>
    <div>원어 : ${movieList[0].language}</div>
    <div>제작비 : ${movieList[0].budget}</div>
    <div>수익 : ${movieList[0].profit}</div>
</body>
</html>