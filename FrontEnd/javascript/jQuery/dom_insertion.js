$().ready(function () {
  //새로운 p 태그를 만든다.내용은 after라고 한다
  //새로운 p 태그는 wrapper 바깥 아래쪽에 위치한다.
  var newP1 = $("<p></p>");
  newP1.text("after");

  $(".wrapper").after(newP1);

  //새로운 p 태그를 만든다..내용은 before라고 한다
  //새로운 p 태그는 wrapper 바깥 위쪽에 위치한다.
  var newP2 = $("<p></p>");
  newP2.text("before");
  $(".wrapper").before(newP2);
  //새로운 p 태그를 만든다..내용은 prepend라고 한다
  //새로운 p 태그는 wrapper 안쪽 위에 위치한다.
  var newP3 = $("<p></p>");
  newP3.text("prepend");
  $(".wrapper").prepend(newP3);
  //새로운 p 태그를 만든다..내용은 append라고 한다
  //새로운 p 태그는 wrapper 안쪽 아래에 위치한다.
  var newP4 = $("<p></p>");
  newP4.text("append");
  $(".wrapper").append(newP4);
  /*-----------------------------------------------*/
  //새로운 div 태그를 만든다."newDiv"로 한다
  //새로운 div 태그는 ".a" 바깥 아래쪽 위치
  var newDiv1 = $("<div>");
  newDiv1.text("1번 문제");
  $(".a").after(newDiv1);

  //새로운 div 태그를 만든다."newDiv"로 한다
  //새로운 div 태그는 ".c" 바깥 위쪽 위치
  var newDiv2 = $("<div>");
  newDiv2.text("2번 문제");
  $(".c").before(newDiv2);

  //새로운 span 태그를 만든다."newSpan"로 한다
  //새로운 span 태그는 ".b" 안쪽 아래 위치
  var newSpan = $("<span>");
  newSpan.text("3번문제");
  $(".b").append(newSpan);
});
