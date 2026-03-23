$().ready(function () {
  $("#package-ticket-count")
    .on("keyup", function () {
      var price = $(this).closest(".package").data("price");
      //input에 적은 값을 가져오는 것 val()
      var count = $(this).val();
      var amount = price * count;
      $("#amount").text(amount);
    })
    .on("change", function () {
      console.log("change");
      $(this).trigger("keyup");
    });
});
