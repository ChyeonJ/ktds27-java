$().ready(function () {
    //"08_ajax.html" 파일을 복사해 "본인이름.html" 로 붙여넣습니다.
    //파일 내 ".load-git-users" 를 클릭하면 위 URL을 fetch 로 호출해
    $(".load-git-users").on("click", function () {
        var fetchLink = fetch("https://api.github.com/users");
        
        // 반환되는 데이터 중 객체 한개 마다 ".posts"의 li로 추가합니다.
        fetchLink
        .then(function (jsonResponse) {
            return jsonResponse.json();
        })
        .then(function (body) {
            //  반환되는 데이터를 console 로 출력하도록 합니다.
            console.log(body);
            for(var i = 0; i < body.length; i++){
                var content = body[i];
                var avatarImg = content.avatar_url;
                var loginVal = content.login;
                var htmlUrl = content.html_url;

                //반환되는 데이터 중 "avatar_url" 의 값을 <img src="" /> 에 추가하고 이미지가 나오도록 합니다. (이미지는 완전한 동그라미로 나오도록 해보세요)
                //반환되는 데이터 중 "login"의 값을 <div></div>에 추가하고 이름이 나오도록 합니다.
                var listImg = $("<img>").attr('src', avatarImg).addClass("index");
                var listDiv = $("<div>").text(loginVal).attr('data-price',htmlUrl).addClass("info");
                var listItem = $("<li>").append(listImg).append(listDiv);
                $(".posts").append(listItem);
            }
        });
        //구글링 새롭게 생성한 태그에서 이벤트 할당하기 위해서
        // document에서 .info의 클래스를 상시적으로 확인해서 이벤트를 활성화 시켜줌
        $(document).on("click",".info",function() {
            var htmlUrl = $(this).data('price');
            // console.log(htmlUrl);
            //반횐되는 데이터 중 "html_url"의 값을 5에서 만든 div를 클릭했을 때 이동하도록 이벤트를 생성합니다.
            window.location.href = htmlUrl;
        });
    });

 

});