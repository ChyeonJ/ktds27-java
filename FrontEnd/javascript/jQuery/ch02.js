$().ready(function () {
  $("li").text("서울");
  //첫번째 꺼 .first()
  //마지막 꺼 .last()
  // 다음 거 .next()

  // 클래스가 promo인 것의 텍스트를 "부산"으로 변경한다
  $(".promo").text("붓산");

  // 아이디가 "destinations"인 태그의
  // 자식요소중 두번째 li텍스트를 "경주로 변경"
  $("#destinations > li:nth-child(2)").text("경주");
});

// window.onload = function () {
//   //모든 li태그를 가져와서 내용을 "서울"로 변경한다
//   // querySelector는 li의 값을 하나만 가지고 온다
//   var listItems = document.querySelectorALl("li");

//   for (var i = 0; i < listItems.length; i++) {
//     listItems[i].innerText = "서울";
//   }
// };
