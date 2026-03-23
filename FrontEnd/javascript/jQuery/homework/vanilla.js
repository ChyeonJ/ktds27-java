window.onload = function () {
  //아이템의 내용은 "아이템 목록 아이템 개수" 형태로 추가되어야 한다.
  var addButton = document.querySelector(".add");
  addButton.addEventListener("click", function () {
    var listCount = document.querySelectorAll(".addlist li").length + 1;
    var add = document.createElement("li");
    var list = document.querySelector(".addlist");
    var footer = document.querySelector(".result");
    add.innerText = "아이템 목록 " + listCount;
    if (listCount < 10) {
      //클릭하면 3번 목록 가장 아래에 아이템이 추가되어야 한다.
      add.classList.add("delNum");
      list.appendChild(add);
      //추가할 때 마다 4번 항목은 "총 n개의 아이템이 등록되었습니다." 로 갱신되어야 한다.
      footer.innerText = "총 " + listCount + "개의 아이템이 등록되었습니다.";
    } else {
      //10개 이상의 아이템은 등록할 수 없다.
      //10개 이상의 아이템을 등록하려 하면 "더 이상 추가할 수 없습니다" 경고창이 나타난다.
      alert("더 이상 추가할 수 없습니다.");
    }
  });

  //클릭하면 3번 목록의 모든 아이템들이 제거된다.
  var delButton = document.querySelector(".del");
  delButton.addEventListener("click", function () {
    if (document.querySelectorAll(".addlist li").length == 0) {
      //3번 항목에 등록된 아이템이 없는 상태에서 클릭하면 "이미 모든 아이템이 제거되었습니다" 경고창이 나타난다.
      alert("이미 모든 아이템이 제거되었습니다.");
    } else {
      var list = document.querySelector(".addlist");
      var footer = document.querySelector(".result");
      list.innerHTML = "";
      //클릭하면 4번 항목은 "총 0개의 아이템이 등록되었습니다." 로 갱신되어야 한다.
      footer.innerText = "총 0개의 아이템이 등록되었습니다.";
    }
  });
};
