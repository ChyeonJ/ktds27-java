// window.onload에 대응되는 코드
// jQuery(document).ready(function () {
//   alert("랜더링 준비 끝");
// });

// shorten 표현식
// $(document).ready(function () {
//   alert("랜더링준비끝");
// });

// $().ready(function () {
//   alert("랜더링 준비 끝!");
// });

$().ready(function () {
  //var h1 = document.querySelector("h1"); 대체하는 코드
  var h1 = $("h1");

  //console.log(h1.innerText); 대체한ㄴ 콛
  console.log(h1.text());

  //h1.innerText = "어디로 갈라우";
  h1.text("어디로갈라우");

  // p태그의 내용을 가져와서
  var p = $(".ppp");

  // alert으로 출력한다.
  alert(p.text());

  // p태그의 내용ㄹ 가져와서 alert으로 출력한다
  alert($("p").text());

  // p태그의 내용을 "다음 여행을 계획해보세요" 라고 변경한다.
  p.text("담 여행 계획 ㄱㄱ");

  $(".ppp").text("담여행 ㄱ");
});

/* window.onload = function () {
    var h1 = document.querySelector("h1");
    
    h1.innerText = "어디로 갈라우";
}; */
