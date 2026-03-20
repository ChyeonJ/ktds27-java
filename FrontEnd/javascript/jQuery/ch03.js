$().ready(function () {
  //   var dest = document.querySelectorAll("#destinations");
  //   console.log(dest);
  //   var listItems = dest.children;

  // #destinaions > li
  var listItems = $("#destinations").children("li");
  console.log(listItems);

  //listItems[i].addEventListener("click", function (event) {});
  listItems.on("click", function () {
    //       console.log("클릭한 태그의 내용", event.target.innerText);
    console.log("클릭한 태그의 내용", $(this).text());
    //console.log(
    //         "클릭한 태그 이전 태그의 내용",
    //         event.target.previousElementSibling.innerText,
    //       ); $(this) jquery 객체로 바꿈
    console.log("클릭한 태그 이전 태그의 내용", $(this).prev().text());
    //       console.log(
    //         "클릭한 태그 이후의 태그의 내용",
    //         event.target.nextElementSibling.innerText,
    //       );
    console.log("클릭한 태그 이후의 태그의 내용", $(this).next().text());
    //       console.log(
    //         "클릭한 태그의 부모 태그의 내용",
    //         event.target.parentElement.innerText,
    //       );
    console.log("클릭한 태그의 부모 태그의 내용", $(this).parent().text());
  });
});

// window.onload = function () {
//   var dest = document.querySelectorAll("#destinations");
//   console.log(dest);

//   var listItems = dest.children;

//   console.log(listItems);

//   for (var i = 0; i < listItems.length; i++) {
//     listItems[i].addEventListener("click", function (event) {
//       console.dir(event.target);

//       console.log("클릭한 태그의 내용", event.target.innerText);
//       console.log(
//         "클릭한 태그 이전 태그의 내용",
//         event.target.previousElementSibling.innerText,
//       );
//       console.log(
//         "클릭한 태그 이후의 태그의 내용",
//         event.target.nextElementSibling.innerText,
//       );

//       console.log(
//         "클릭한 태그의 부모 태그의 내용",
//         event.target.parentElement.innerText,
//       );
//     });
//   }
// };
