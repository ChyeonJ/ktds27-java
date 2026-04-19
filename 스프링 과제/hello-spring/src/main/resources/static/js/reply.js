$().ready(function () {
  var loginEmail = $(".member-info").data("email");
  var refreshReplies = function () {
    // 게시글 아이디
    var articleId = $(".view").data("article-id");

    fetch("/api/replies/" + articleId)
      .then(function (response) {
        return response.json();
      })
      .then(function (json) {
        console.log(json);
        var count = json.count;
        $(".replies-count").children(".count").text(count);

        var replies = json.result;
        for (var i = 0; i < replies.length; i++) {
          var reply = replies[i];

          var replyTemplate = $(".reply-item-template").html();
          replyTemplate = replyTemplate
            .replace("#replyId#", reply.id)
            .replace("#name#", reply.membersVO.name)
            .replace("#email#", reply.email)
            .replace("#createDate#", reply.crtDt)
            .replace("#modifyDate#", reply.mdfyDt)
            .replace("#recomendCount#", reply.recommendCnt)
            .replace("#content#", reply.reply);
          var replyDom = $(replyTemplate);
          //  로그인한 회원이 작성한 댓글인 경우
          //  추천하기 노출 X
          if (!loginEmail || loginEmail === reply.email) {
            replyDom.find(".links-recommend").remove();
          }
          //  로그인한 회원이 작성하지 않은 댓글인 경우
          // 수정, 삭제 - 노출 하지 않음
          if (loginEmail !== reply.email) {
            replyDom.find(".links-update, .links-delete").remove();
          }
          //  첨부파일이 있을 경우 첨부파일 목록 보여주기.
          if (!reply.fileGroupId) {
            replyDom.find(".reply-attach-files").remove();
          } else {
            replyDom
              .find(".reply-attach-files")
              //reply.files가 객체의 배열 타입인데 .stringify를
              //  사용하면 String타입으로 넘겨주게 된다.
              .data("files", JSON.stringify(reply.files));

            //reply.files를 반복하면서 a태그를 ".reply-attach-files:추가
            for (var j = 0; j < reply.files.length; j++) {
              var file = reply.files[j];
              var fileSize = file.fileLength; // bytes
              var capaType = "byte";
              if (fileSize > 1024) {
                // kb
                capaType = "kb";
                fileSize = Math.ceil(fileSize / 1024);
              }
              if (fileSize > 1024) {
                // mb
                capaType = "mb";
                fileSize = Math.ceil(fileSize / 1024);
              }

              var fileAnchor = $("<a>");
              fileAnchor.text(
                file.displayName + " (" + fileSize + capaType + ")",
              );
              fileAnchor.attr({
                href: "/file/" + file.fileGroupId + "/" + file.fileNum,
              });
              replyDom.find(".reply-attach-files").append(fileAnchor);
            }
          }
          //  수정날짜가 없을 떄는 수정날짜 노출 X
          if (!reply.mdfyDt) {
            replyDom.find(".modify-date").remove();
          }
          // TODO 댓글 추천 수 노출
          // TODO 댓글 추천, 수정, 삭제.
          // TODO 댓글 추천.
          //      추천하기를 클릭하면, 추천수가 증가한다.
          replyDom.find(".links-recommend").on("click", function () {
            // API 호출 (Endpoint => /api/replies/recommend/{댓글아이디})
            var replyId = $(this).closest(".reply-item").data("reply-id");
            var recommendPath = $(this)
              .closest(".reply-item")
              .find(".recommend-count");
            fetch("/api/replies/recommend/" + replyId)
              // 호출 결과 ==> {replyId: "RP-20260410-000001" recommendCount: 13}
              .then(function (response) {
                return response.json();
              })
              .then(function (json) {
                console.log(json);
                var recommendCnt = json.recommendCnt;
                recommendPath.text(recommendCnt);
              });
            // ".recommend-count" 텍스트를 추천 결과 값으로 변경
          });
          // 강사님 코드 시작
          // TODO 댓글 수정.
          //      수정하기를 클릭하면 , 댓글을 수정할 수 있는 폼이 완성된다.
          replyDom.find(".links-update").on("click", function () {
            $(".update-form").remove();

            //file=> attachFile을 가져오는 코드
            // .stringify한 코드를 가져옴
            var replyAttachFiles = $(this)
              .closest(".reply-item")
              .find(".reply-attach-files")
              .data("files");

            //파일들의 목록이 있으면, 그 파일들을 가지고 와라
            if (replyAttachFiles) {
              replyAttachFiles = JSON.parse(replyAttachFiles);
            }

            // links-update에서 시작해서 가장인접한 부모를 가지고 온후 content를 찾아서 넣어라
            var content = $(this)
              .closest(".reply-item")
              .find(".content")
              .text();

            //updateForm에 데이터를 넣어라
            var updateTemplate = $(".reply-item-update-template").html();
            var updateFormDom = $(updateTemplate);
            updateFormDom.find("textarea").val(content);

            //취소 버튼을 클릭하면 updateForm을 지워라
            updateFormDom.find(".update-cancel").on("click", function () {
              $(".update-form").remove();
            });

            updateFormDom.find(".update-save").on("click", function () {
              console.log("저장버튼을 클릭했습니다.");

              // 수정을 위해 필요한 데이터
              // 1. 수정하려는 댓글의 아이디
              var replyId = $(this).closest(".reply-item").data("reply-id");
              // 2. 수정하려는 댓글의 내용.
              var updateContent = $(this)
                .closest(".update-form")
                .find("textarea")
                .val();
              // 3. 삭제하려는 파일의 fileNum (복수)
              var deleteFilesNum = $(this)
                .closest(".update-form")
                .find(".update-file-list")
                .find("input[type='checkbox']:checked");
              // 4. 추가하려는 파일 (복수)
              var newAttachFiles = $(this)
                .closest(".update-form")
                .find(".reply-update-attach-file")[0].files;

              var formData = new FormData();
              formData.append("content", updateContent);
              // 삭제할 파일이 있으면 formData에 추가한다.
              deleteFilesNum.each(function () {
                formData.append("delFileNum", $(this).val());
              });
              // 추가하려는 파일이 있으면 formData에 추가한다.
              for (var j = 0; j < newAttachFiles.length; j++) {
                formData.append("newAttachFile", newAttachFiles[j]);
              }

              fetch("/api/replies/" + replyId, {
                method: "POST",
                body: formData,
              })
                .then(function (response) {
                  return response.json();
                })
                .then(function (json) {
                  //replies의 html tag들을 다 지워버려라
                  $(".replies").html("");
                  refreshReplies();
                });
            });

            // 이 댓글에 첨부된 파일을 가져옴
            if (replyAttachFiles) {
              // 있다면? .reply-item-update-files가져오고 html을 가져와라
              var replyItemsTemplate = $(".reply-item-update-files").html();
              for (var j = 0; j < replyAttachFiles.length; j++) {
                var replyItemFile = replyAttachFiles[j];

                var fileTemplate = replyItemsTemplate
                  .replaceAll("#fileGroupId#", replyItemFile.fileGroupId)
                  .replaceAll("#fileNum#", replyItemFile.fileNum)
                  .replaceAll("#fileDisplayName#", replyItemFile.displayName);

                updateFormDom.find(".update-file-list").append($(fileTemplate));
              }
            }

            $(this)
              .closest(".reply-item")
              .find(".content")
              .after(updateFormDom);
          });
          // 강사님 코드 끝
          // TODO 댓글 삭제
          //      삭제를 클릭하면, "삭제하시겠습니까?"를 물어보고 "확인"을 클릭했을 때 삭젤ㄹ 하고
          //      이후 댓글 목록을 새로 고친다.
          replyDom.find(".links-delete").on("click", function () {
            // "삭제하시겠습니까?" 를 사용자에게 물어본다. (Endpoint => /api/replies/delete/{댓글아이디})
            // "확인"을 클릭하면 삭제 API를 호출한다.
            //  호출 결과 ==> {replyId: "RP-20260410-000001"}
            //  결과의 replyId 댓글을 목록에서 제거한다 (댓글 다시 불러오기 X)
            //  "취소"를 클릭하면 아무일도 일어나지 않는다.
            var replyId = $(this).closest(".reply-item").data("reply-id");
            var deletePath = $(this).closest(".reply-item");

            if (confirm("삭제 할거임?")) {
              fetch("/api/replies/delete/" + replyId)
                .then(function (response) {
                  return response.json();
                })
                .then(function (json) {
                  console.log(json);
                  deletePath.remove();
                  $(".replies-count")
                    .children(".count")
                    .text(parseInt(count) - 1);
                });
            } else {
              return;
            }
          });

          replyDom.css({ "margin-left": (reply.level - 1) * 32 + "px" });

          replyDom.find(".links-write").on("click", function () {
            var replyId = $(this).closest(".reply-item").data("reply-id");
            console.log("Click! - " + replyId);

            $(".reply-form").children(".parent-reply-id").val(replyId);
            $(".reply-content").focus();
          });

          $(".replies").append(replyDom);
        }
      });
  };
  refreshReplies();

  $(".reply-save").on("click", function () {
    var replyContent = $(".reply-content").val();
    var articleId = $(this).data("article-id");
    var parentReplyId = $(".parent-reply-id").val();
    var files = $(".reply-attach-file")[0];
    console.log(files.files);

    var formData = new FormData();
    formData.append("reply", replyContent);
    formData.append("articleId", articleId);
    formData.append("parentReplyId", parentReplyId);

    if (files.files.length > 0) {
      for (var i = 0; i < files.files.length; i++) {
        formData.append("attachFile", files.files[i]);
      }
    }

    //POST 방식으로 보내기에 {} 객체를 만듦
    fetch("/api/replies-with-file", {
      method: "post",
      body: formData,
    })
      .then(function (response) {
        return response.json();
      })
      .then(function (json) {
        console.log(json);

        // 댓글 등록하기 후 처리
        $(".reply-form").children(".parent-reply-id").val("");
        $(".reply-content").val("");
        $(".replies").html("");
        refreshReplies();
      });

    console.log(replyContent, articleId, parentReplyId);
  });
});
