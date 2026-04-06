$().ready(function() {

    $("#email").on("keyup", function() {
        var emailValue = $(this).val();

        var emailPattern =
            /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/;

        if (emailPattern.test(emailValue)) {
            fetch("/join/check/" + emailValue)
                .then(function(fetchResult) {
                    return fetchResult.json();
                })
                .then(function(json) {
                    var duplicateResult = $("#email")
                        .closest(".input-div")
                        .children(".validation-ok, .validation-error");

                    if (duplicateResult.length === 0) {
                        duplicateResult = $("#email")
                            .closest("input-div")
                            .children(".validation-ok");
                    }
                    if (duplicateResult.length === 0) {
                        var duplicateResult = $("<div>");
                        $("#email").after(duplicateResult);
                    }

                    if (!json.check) {
                        duplicateResult.removeClass("validation-error");
                        duplicateResult.addClass("validation-ok");
                        duplicateResult.text(json.email + "은 사용 가능합니다.");
                    } else {
                        duplicateResult.removeClass("validation-ok");
                        duplicateResult.addClass("validation-error");
                        duplicateResult.text(json.email + "은 이미 사용중입니다.");
                    }
                });
        } else {
            $(this)
                .closest(".input-div")
                .children(".validation-ok, .validation-error")
                .remove();
        }
    });

    $("#confirm-password, #password").on("keyup", function() {
        var confirmPasswordValue = $("#confirm-password").val();
        var passwordValue = $("#password").val();

        $("#password, #confirm-password")
            .closest(".input-div")
            .children(".validation-error")
            .remove();

        if (confirmPasswordValue !== passwordValue) {
            var passwordErrorMessage = $("<div>");
            passwordErrorMessage.addClass("validation-error");
            passwordErrorMessage.text("비밀번호가 일치 하지 않습니다");

            var confirmPasswordErrorMessage = $("<div>");
            confirmPasswordErrorMessage.addClass("validation-error");
            confirmPasswordErrorMessage.text("비밀번호가 일치 하지 않습니다");

            $("#password").after(passwordErrorMessage);
            $("#confirm-password").after(confirmPasswordErrorMessage);
        }
    });

    $("#show-password").on("change", function() {
        var checked = $(this).prop("checked");
        if (checked) {
            $("#password").attr("type", "text");
        } else {
            $("#password").attr("type", "password");
        }
    });

    $("#memberVO").on("submit", function(event) {
        event.preventDefault();
        $(this).find(".validation-error").remove();

        $("#password").trigger("keyup");

        var email = $("#email").val();
        if (!email) {
            var emailErrorMessage = $("<div>");
            emailErrorMessage.addClass("validation-error");
            emailErrorMessage.text("이메일 형태가 아닙니다.");

            $("#email").after(emailErrorMessage);
        }

        var name = $("#name").val();
        if (!name) {
            var nameErrorMessage = $("<div>");
            nameErrorMessage.addClass("validation-error");
            nameErrorMessage.text("이름을 입력하세요");

            $("#name").after(nameErrorMessage);
        }

        var passwordPattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/;
        var password = $("#password").val();
        if (!passwordPattern.test(password)) {
            var passwordErrorMessage = $("<div>");
            passwordErrorMessage.addClass("validation-error");
            passwordErrorMessage.text(
                "비밀번호는 영소문자, 영대문자, 숫자 최소 1개를 포함하여 8글자 이상으로 입력하세요.",
            );

            $("#password").after(passwordErrorMessage);
        }
        if ($(".validation-error").length === 0) {
            this.submit();
        }
    });
});