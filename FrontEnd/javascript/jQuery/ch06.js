$().ready(function () {
  $(".vacation-title")
    .children("img")
    .on("mouseenter", function () {
      $(".ticket").show();
    })
    .on("mouseleave", function () {
      $(".ticket").hide();
    });

  $(".mouse-over-event-exam")
    .css({
      padding: "10px",
      color: "#fff",
    })
    .on("mouseenter", function () {
      var listItem = $("<li>");

      listItem.text($(this).children("ul").children("li").length + 1);

      $(this).children("ul").append(listItem);
    })
    .on("mouseleave", function () {
      $(this).children("ul").children("li").last().remove();
    });
});
