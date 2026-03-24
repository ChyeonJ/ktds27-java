var runMode = "jquery";

$().ready(function () {
  //runMode가 제이쿼리 일 때만 동작
  // checkbox 전체선택
  if (runMode === "jquery") {
    // vanilla ==> input, select의 value를 get하려면 element.value;
    // vanilla ==> input, select의 value를 set하려면 element.value=값;
    // jQuery ==> input, select의 value를 get하려면 element.val();
    // jQuery ==> input, select의 value를 set하려면 element.val(값);

    $("#checked-all").on("change", function () {
      $("input[type='checkbox'][name='favorate-genre']").prop(
        "checked",
        $(this).prop("checked"),
      );
    });

    $("input[type='checkbox'][name='favorate-genre']").on(
      "change",
      function () {
        // 체크 박스의 개수 세기
        var checkBox = $("input[type='checkbox'][name='favorate-genre']");
        var checkBoxCount = checkBox.length;

        // 체크한 체크박스의 개수 세기 :checked 하면 체크된 것만 가져옴
        //  .filter(":checked") 가능
        var checkedCount = $(
          "input[type='checkbox'][name='favorate-genre']:checked",
        ).length;

        $("#checked-all").prop("checked", checkBoxCount === checkedCount);
      },
    );

    // email 값
    var email = $("#email");
    console.log("변경전 : " + email.val()); //get
    // email 값 변경
    email.val("adad@naver.com"); //set
    console.log("변경 후 : " + email.val()); //get

    //직업 select
    var jobs = $("#jobs"); //돔 생성
    console.log("selected val = " + jobs.val()); //selected 기준 값 출력
    // 사용자가 select 태그에서 options을 변경 했을 때 값 출력
    jobs.on("change", function () {
      console.log("선택한 값 = " + $(this).val());
      console.log(
        "선택한 값의 결과 = " + $(this).children("option:selected").text(),
      );
    });

    // radio 버튼 값 출력
    var radioResult = $("input[type=radio][name=age]");
    radioResult.on("click", function () {
      console.log("최초 선택 : " + $(this).val(), $(this).is(":checked"));
    });

    var radioResult2 = $("input[type=radio][name=age]");
    radioResult2.on("change", function (resultIndex) {
      console.log("변경된 선택 : " + $(this).val(), $(this).is(":checked"));
    });
  }
});

window.onload = function () {
  //runMode가 바닐라 일 때만 동작
  if (runMode === "vanilla") {
    // event~~
    // 모든 form tag 공통
    var email = this.document.querySelector("#email").value;
    console.log(email);

    //벨류 변경
    this.document.querySelector("#email").value = "da@naver.com";

    var jobs = this.document.querySelector("#jobs").value;
    //selected가 없으면 0이 나오고 있으면 그 값이 나옴 value가 없으면 text를 value로 줌
    console.log(jobs);

    // select 태그에서 option의 value가 3인 것을 선택해라 라는 의미가 된다.
    this.document.querySelector("#jobs").value = "3";
    // select 태그에서 option의 value가 없는 것을 선택하면? 아무것도 나오지 않음
    this.document.querySelector("#jobs").value = "999";

    // 사용자가 select 태그에서 option을 변경 했을 때 해당 값을 출력해라
    this.document
      .querySelector("#jobs")
      .addEventListener("change", function () {
        console.log(this.value);
        console.log(
          this.querySelector("option[value ='" + this.value + "']").innerText,
        );
      });
    // radio event (click) ==> radio를 클릭 할 때
    // this.document
    //   .querySelector("input[type='radio'][name='age']")
    //   .addEventListener("click", function () {
    //     //
    //     console.log(this.value, this.checked);
    //   });
    var radios = this.document.querySelectorAll(
      "input[type='radio'][name='age']",
    );

    for (var i = 0; i < radios.length; i++) {
      radios[i].addEventListener("click", function () {
        // 클릭한 radio의 선택 상태를 콘솔에 출력
        console.log(this.value, this.checked);
      });
    }

    // radio event (change) ==> radio가 선택될 때
    for (var i = 0; i < radios.length; i++) {
      radios[i].addEventListener("change", function () {
        // 선택상태가 변경된 radio의 선택 상태를 콘솔에 출력
        console.log(this.value, this.checked);
      });
    }

    //checkbox 값 추출 checkbox 선택 될 때
    var checkboaxes = this.document.querySelectorAll(
      "input[type='checkbox'][name='favorate-genre']",
    );
    // 전체 선택버튼
    var checkedAll = this.document.querySelector("#checked-all");
    checkedAll.addEventListener("change", function () {
      for (var i = 0; i < checkboaxes.length; i++) {
        checkboaxes[i].checked = this.checked;
      }
    });

    for (var i in checkboaxes) {
      // i라는 값이 숫자라면~ 숫자가 아닌 것이 아니라면 ! isNaN
      if (!isNaN(i)) {
        // console.log(i, checkboaxes[i]);
        checkboaxes[i].addEventListener("change", function () {
          // 체크박스의 선택 상태가 변경될 때 마다 체크된 체크박스의 개수를 조회하여 출력한다.
          //checkboaxes 다시 한 번 반복
          var checkedCount = 0;
          for (var index = 0; index < checkboaxes.length; index++) {
            if (checkboaxes[index].checked) {
              checkedCount++;
            }
          }
          console.log(checkedCount, "개의 체크박스가 선택됨.");

          //all이 활성화 되면 = length가 4개가 되면         체크박수의 개수가 4개
          // 동일하면 체크박스를 활성화 => 동일하지 않으면 비활성화
          checkedAll.checked = checkboaxes.length === checkedCount;

          //체크 된 것만 출력 (value)
          if (this.checked) {
            console.log(this.value, this.checked);
          }
        });
      }
    }
  }
};
