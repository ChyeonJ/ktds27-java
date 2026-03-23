//https://jsonplaceholder.typicode.com/comments

$().ready(function () {
  $(".load-comments").on("click", function () {
    $(".spinner").show();
    // 비동기로 url 호출
    // window.fetch(url); ==> Promise를 반환 우리에게 돌려줌
    // (서버에서 아직 데이터가 전달되지 않은 상태.)
    var fetchProm = fetch("https://jsonplaceholder.typicode.com/comments");
    // pending ==> 서버에세 Http 요청을 진행중인 상태
    // fulfilled ==> 서버가 fetch에게 HTTP 요청에 대한 값을 반환 완료한 상태.
    // => fulfilled는 서버로부터 정상적으로 반환되었다 => fetchProm.then() 처리
    // => 서버로부터 에러가 반환되었다 => fetchProm.catch() 처리

    // 서버로 보낸 HTTP 요청이 서버로부터 정상적으로 처리되어 값을 반환시킨 경우.
    // 서버가 반환시킨 값을  console로 출력해본다
    fetchProm
      .then(function (jsonResponse /* 서버가 fetch에게 반환시킨 값. */) {
        //json()이라는 함수를 적어줘야 값이 보인다
        // jsonResponse.json() ==> 비동기 처리 (Promise가 반환되었으므로.)
        //   console.log(jsonResponse.json());
        return jsonResponse.json();
      })
      .then(function (body /* jsonRespnse.json()이 반환시킨 값 */) {
        for (var i = 0; i < body.length; i++) {
          var comment = body[i];
          var bodyComment = comment.body;

          var li = $("<li>").text(bodyComment);
          $(".comments").append(li);
        }
        $(".spinner").hide();
      });

    // 일정 주기로 함수를 실행하는 함수 -> 계속 반복한다 끝없이
    // setInterval(function () {}, 1000) ==> 1초 주기로 함수를 실행
    // 0.1초 단위로 fetchProm을 확인한다
    setInterval(function () {
      //body ==> [{postUd, id, name, email, body}, {}, {}, {},....]
      console.log(fetchProm);
    }, 100);
  });
});

// window.onload = function () {
//   //함수를 호출한 녀석 window / .this
//   var button = this.document.querySelector(".call-promise");

//   button.addEventListener("click", function () {
//     var delay = parseInt(Math.random() * 10000);

//     // 비동기 함수가 처리되는 곳  성공 시 resolv, 실패 시 result
//     var prom = new Promise(function (resolv, result) {
//       //비동기 코드 실행
//       setTimeout(function () {
//         if (delay % 2 === 0) {
//           reject(delay);
//         } else {
//           resolv(delay);
//         }
//       }, delay);
//     });
//     prom
//       .then(function (delay) {
//         //resolve
//         alert(delay);
//       })
//       .catch(function (delay) {
//         //reject
//         console.log("에러 발생!", delay);
//       });
//   });
// };
