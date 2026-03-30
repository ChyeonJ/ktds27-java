<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie List</title>
<link rel="stylesheet" type="text/css" href="/css/tmdb-main.css"/>
</head>
<body>
    <h1>Movie 목록</h1>
    <div>총 ${searchCount}개의 게시글이 검색 되었습니다.</div>

    <table class="grid list">
       <thead>
        <tr>
           <th>아이디</th>
           <th>포스터 URL</th>
           <th>영화 제목</th>
           <th>관람등급</th>
           <th>개봉날짜</th>
           <th>개봉국가</th>
           <th>상영시간</th>
           <th>소개글</th>
           <th>개요</th>
           <th>원제</th>
           <th>상태</th>
           <th>원어</th>
           <th>제작비</th>
           <th>수익</th>
        </tr>
       </thead>
    <c:choose>
        <c:when test="${not empty movieList}">
         <c:forEach items="${movieList}"  var="movie">
        <tbody>
           <tr>
            <td>
            <a href="/view/${movie.movieId}">
            ${movie.movieId}</a>
            </td>
            <td>${movie.posterUrl}</td>
            <td>${movie.title}</td>
            <td>${movie.movieRating}</td>
            <td>${movie.openDate}</td>
            <td>${movie.openCountry}</td>
            <td>${movie.runningTime}</td>
            <td>${movie.introduce}</td>
            <td>${movie.synopis}</td>
            <td>${movie.originalTitle}</td>
            <td>${movie.state}</td>
            <td>${movie.language}</td>
            <td>${movie.budget}</td>
            <td>${movie.profit}</td>
           </tr>
        </tbody>
        </c:forEach>
        </c:when>
        <c:otherwise>
            <tbody>
                <tr>
                    <td colspan="14">등록된 영화가 없습니다</td>
                </tr>
            </tbody>
        </c:otherwise>
    </c:choose>
    </table>
    <div class="btn-group">
        <div class="right-align">
          <a href="/write">영화 등록</a>
        </div>
    </div>
</body>
</html>