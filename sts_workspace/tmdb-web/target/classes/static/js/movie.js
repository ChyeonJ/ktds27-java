$().ready(function () {

    $(".insertMovieVO").on("submit", function (event) {

        event.preventDefault();

        $(this).find(".errorMessage").remove();

        var posterUrl = $("#posterUrl").val();
        var title = $("#title").val();
        var synopsis = $("#synopsis").val();
        var state = $("#state").val();
        var language = $("#language").val();

        if(!posterUrl){
            var posterUrlErrorMessage = $("<div>");
            posterUrlErrorMessage.addClass("errorMessage");
            posterUrlErrorMessage.text("포스터URL을 입력하세요");
        }

        if(!title){
            var titleErrorMessage = $("<div>");
            titleErrorMessage.addClass("errorMessage");
            titleErrorMessage.text("제목을 입력하세요");
        }

        if(!synopsis){
            var synopsisErrorMessage = $("<div>");
            synopsisErrorMessage.addClass("errorMessage");
            synopsisErrorMessage.text("개요를 입력하세요");
        }

        if(!state){
            var stateErrorMessage = $("<div>");
            stateErrorMessage.addClass("errorMessage");
            stateErrorMessage.text("개봉상태를 입력하세요")
        }

        if(!language){
            var languageErrorMessage = $("<div>");
            languageErrorMessage.addClass("errorMessage");
            languageErrorMessage.text("원어를 입력하세요")
        }

        if($(".errorMessage").length() === 0){
            this.submit();
        }

    });

});