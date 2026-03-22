$().ready(function () {
    
    $(".add").on("click",function () {
         var listLen = $(".delNum").length;
         var result = $(".result");
        if(listLen < 10){
            //아이템의 내용은 "아이템 목록 아이템 개수" 형태로 추가되어야 한다.
            var newList = $("<li>").text("아이템 목록 " + parseInt(listLen + 1));
            //클릭하면 3번 목록 가장 아래에 아이템이 추가되어야 한다.
            $(".addlist").append(newList);
            newList.addClass("delNum");
        } else {
            //10개 이상의 아이템은 등록할 수 없다.
            //10개 이상의 아이템을 등록하려 하면 "더 이상 추가할 수 없습니다" 경고창이 나타난다.
            alert("더 이상 추가할 수 없습니다.");
        }
        //추가할 때 마다 4번 항목은 "총 n개의 아이템이 등록되었습니다." 로 갱신되어야 한다.
        result.text("총 " + parseInt(listLen + 1) + "개의 아이템이 등록되었습니다.");
    });

    //클릭하면 3번 목록의 모든 아이템들이 제거된다.
    $(".del").on("click", function () {
        if(parseInt($(".delNum").length) != 0){
            $(".delNum").remove();
            //클릭하면 4번 항목은 "총 0개의 아이템이 등록되었습니다." 로 갱신되어야 한다.
            $(".result").text("총 0개의 아이템이 등록되었습니다.");
        } else {
            //3번 항목에 등록된 아이템이 없는 상태에서 클릭하면 "이미 모든 아이템이 제거되었습니다" 경고창이 나타난다.
            alert("이미 모든 아이템이 제거되었습니다.");
        }
    });


    
});