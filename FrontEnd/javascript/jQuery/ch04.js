$().ready(function () {
  //처음부터 존재했던 ".pakage-button-area" dom을 통해서  03.23 event 대상
  //새롭게 생성된 "p.white-color" 에게 click 이벤트를 할당한다.
  $(".package-button-area").on("click", "p.white-color", function () {
    alert($(this).text());
  });

  var contact = $(".contact").on("click", function () {
    // console.log($(this).prev().find(".package-deal-comment").text());
    $(this)
      .prev()
      .find(".package-deal-comment")
      .each(function () {
        console.log($(this).text());
      });
  });
  $(".package-green-button").on("click", function () {
    //price라고 하면 dataprice값을 가져온다
    // var price = $(this).parent().parent().parent().data("price");
    var price = $(this).closest(".package").data("price");

    // Event가 적용되는 대상 때 추가된 코드 03/23
    var priceP = $("<p>");
    priceP.text("From $" + price);

    // priceP.on("click", function () { 03/23
    //   alert($(this).text());
    // });

    var newP = $("<p>").text("From $" + price);

    // 새롭게 만든 p 태그에게 inline style을 부여한다.
    // newP.css({ color: "#fff" }); 권장X
    // 새롭게 만든 p 태그에게 ".white-color" 클래스를 부여한다.
    newP.addClass("white-color");

    $(this).after(newP);
    // 버튼 찍고 삭제
    $(this).remove();
    //remove => 메모리 완전 삭제
    // detach => 메모리는 있지만 랜더링은 하지 말아라 잘 안씀
  });
});
