<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/templates/header.jsp">
    <jsp:param value="탈퇴 완료" name="title" />
</jsp:include>

    <input type="text"
           readonly="readonly"
           style = "width: 100%"
           placeholder="탈퇴가 완료되었습니다. 다음에 또 만나요"/>

<jsp:include page="/WEB-INF/views/templates/footer.jsp"></jsp:include>