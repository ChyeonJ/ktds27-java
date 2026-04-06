<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Moive 등록</title>
<script type="text/javascript" src="/js/jquery-4.0.0.slim.min.js"></script>
<script type="text/javascript" src="/js/movie.js"></script>
<link rel="stylesheet" type="text/css" href="/css/tmdb-main.css"/>
</head>
<body>
    <h1>영화 등록</h1>
    <!-- action은 form 내부의 value를 전송할 엔드포인트 -->
    <form:form modelAttribute="insertMovieVO" method="post" action="/write" enctype="multipart/form-data">
      <div class="grid write">
        <label for=posterUrl>포스터URL</label>
        <div class="input-div">
        <input type="text"
               id="posterUrl"
               name="posterUrl"
               placeholder="포스터URL 입력해주세요"
               value="${errorData.posterUrl}"/>
        <form:errors path="posterUrl" cssClass="errorMessage" element="div"></form:errors>
         </div>
        <label for="attach-files">첨부파일</label>
        <div id="attach-files" class="attach-files">
            <input type="file" name="readyFile" />
       <!-- <button type="button" class="add-file">+</button>-->
        </div>
        
        <label for="title">영화 제목</label>
        <div class="input-div">
        <input
          type="text"
          id="title"
          name="title"
          placeholder="영화 제목을 입력하세여"
          value="${errorData.title}"
        />
        <form:errors path="title" cssClass="errorMessage" element="div"></form:errors>
        </div>
        
        <label for="movieRating">관람 등급</label>
        <input
          type="text"
          id="movieRating"
          name="movieRating"
          placeholder="관람 등급을 입력하세여"
          value="${errorData.movieRating}"
        />
        
        <label for="openDate">개봉날짜</label>
        <input
          type="date"
          id="openDate"
          name="openDate"
          placeholder="개봉날짜를 입력하세여"
          value="${errorData.openDate}"
        />
        
        <label for="openCountry">개봉국가</label>
        <input
          type="text"
          id="openCountry"
          name="openCountry"
          placeholder="개봉국가를 입력하세여"
          maxlength="2"
          value="${errorData.openCountry}"
        />
        
        <label for="runningTime">상영시간</label>
        <input
          type="number"
          id="runningTime"
          name="runningTime"
          placeholder="상영시간을 입력하세여"
          value="${errorData.runningTime}"
        />
        
        <label for="introduce">소개글</label>
        <input
          type="text"
          id="introduce"
          name="introduce"
          placeholder="소개글을 입력하세여"
          value="${errorData.introduce}"
        />
        
        <label for="synopsis">개요</label>
        <div class="input-div">
        <input
          type="text"
          id="synopsis"
          name="synopsis"
          placeholder="개요를 입력하세여"
          value="${errorData.synopsis}"
        />
        <form:errors path="synopsis" cssClass="errorMessage" element="div"></form:errors>
        </div>
        
        <label for="originalTitle">원제</label>
        <input
          type="text"
          id="originalTitle"
          name="originalTitle"
          placeholder="원제를 입력하세여"
          value="${errorData.originalTitle}"
        />
        
        <label for="state">상태</label>
        <div class="input-div">
        <input
          type="text"
          id="state"
          name="state"
          placeholder="상태를 입력하세여"
          maxlength="5"
          value="${errorData.state}"
        />
        <form:errors path="state" cssClass="errorMessage" element="div"></form:errors>
        </div>
        
        <label for="language">원어</label>
        <div class="input-div">
        <input
          type="text"
          id="language"
          name="language"
          placeholder="원어를 입력하세여"
          value="${errorData.language}"
        />
        <form:errors path="language" cssClass="errorMessage" element="div"></form:errors>
        </div>
        
        <label for="budget">제작비</label>
        <input
          type="number"
          id="budget"
          name="budget"
          placeholder="제작비를 입력하세여"
          value="${errorData.budget}"
        />
        
        <label for="profit">수익</label>
        <input
          type="number"
          id="profit"
          name="profit"
          placeholder="수익을 입력하세여"
          value="${errorData.profit}"
        />
        
        <div class="btn-group">
          <div class="right-align">
            <input type="submit" value="저장" />
          </div>
        </div>
      </div>
    </form:form>
</body>
</html>