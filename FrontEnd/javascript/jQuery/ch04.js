$().ready(function () {
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

    var newP = $("<p>").text("From $" + price);

    // 새롭게 만든 p 태그에게 inline style을 부여한다.
    // newP.css({ color: "#fff" }); 권장X

    $(this).after(newP);
    // 버튼 찍고 삭제
    $(this).remove();
    //remove => 메모리 완전 삭제
    // detach => 메모리는 있지만 랜더링은 하지 말아라 잘 안씀
  });
});
