$().ready(function () {
  //   var listItems = document.querySelectorAll("#destinations");
  $("#destinations").children("li").text();
});

// window.onload = function () {
//   var listItems = document.querySelectorAll("#destinations > li");

//   for (var i = 0; i < listItems.length; i++) {
//     //event라는 파라미터 클릭한 마우스의 위치, 무엇을, 언제를 담아줌
//     listItems[i].addEventListener("click", function (event) {
//       console.dir(event.target);
//       console.log("클릭한 태그 내용 : ", event.target.innerText);
//       console.log(
//         "클릭한 태그 이전 태그의 내용 : ",
//         event.target.previousElementSibling.innerText,
//       );
//       console.log(
//         "클릭한 태그 이후의 태그 내용",
//         event.target.nextElementSibling.innerText,
//       );

//       console.log(
//         "클릭한 태그의 부모 태그의 내용",
//         event.target.parentElement.innerText,
//       );
//     });
//   }
// };
