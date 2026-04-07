$().ready(function () {
    
    var sessionTimeout = 60*1000;//30 * 60 * 1000;

    setTimeout(function () {
        alert("세션 만료");
        location.href = "/login";
    }, sessionTimeout);
    
  //".add-file"을 클릭하면
  // 새로운 파일 인풋과 버튼을
  // ".attacg-files" 아래에 추가한다.
  $(".attach-files").on("click", ".add-file", function () {
    //$(".add-file").on("click", function () {
    // 새로운 파일이 추가될 때 마다 기존의 "add-file"을 del-file로 변경
    // 텍스트는 "+"에서 "-"로 변경한다.
    $(this)
      .closest(".attach-files")
      .children(".add-file")
      .removeClass("add-file")
      .addClass("del-file")
      .text("-")
      .off("click") // 할당 되어있던 이벤트를 제거한다
      .on("click", function () {
        // 버튼 왼쪽에 있는 인풋 태그 삭제.
        $(this).prev().remove();
        // 버튼도 삭제
        $(this).remove();
      }); // 새로운 이벤트 추가

    var fileInput = $("<input/>");
    fileInput.attr({
      type: "file",
      name: "attachFile",
    });

    var addButton = $("<button />");
    addButton.attr("type", "button").addClass("add-file").text("+");

    $(".attach-files").append(fileInput).append(addButton);
  });

  $("#writeVO").on("submit", function (event) {
    event.preventDefault();

    $(this).find(".validation-error").remove();

    var subject = $("#subject").val();
    var subjectSize = subject ? subject.length : 0;
    if (!subject || subjectSize <= 2) {
      var subjectErrorMessage = $("<div>");
      subjectErrorMessage.addClass("validation-error");
      subjectErrorMessage.text("제목을 3글자 이상 입력하세요");

      $("#subject").after(subjectErrorMessage);
    }

    var email = $("#email").val();
    if (!email) {
      var emailErrorMessage = $("<div>");
      emailErrorMessage.addClass("validation-error");
      emailErrorMessage.text("이메일을 입력하세요");
      $("#email").after(emailErrorMessage);
    }
    
    if($(".validation-error").length === 0) {
        this.submit();
    }
  });
});
