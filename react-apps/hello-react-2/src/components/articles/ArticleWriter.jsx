/** @format */

import { useImperativeHandle, useRef, useState } from "react";
import { Alert } from "../ui/Modal";
import { isString } from "../utils/type";
import getValidationResult from "../utils/errorHandler";

const Input = ({ id, title, type = "text", ref, ...props }) => {
  console.log("Input");
  return (
    <div className="input-field">
      <label htmlFor={id}>{title}</label>
      <input type={type} id={id} ref={ref} {...props} />
    </div>
  );
};

const Textarea = ({ id, title, ref, ...props }) => {
  console.log("Textarea");
  return (
    <div className="input-field">
      <label htmlFor={id}>{title}</label>
      <textarea id={id} ref={ref} {...props}></textarea>
    </div>
  );
};

const ArticleWriter = ({ onAddArticleClick, errorHandleRef }) => {
  console.log("ArticleWriter");

  const [addError, setAddError] = useState();

  useImperativeHandle(errorHandleRef, () => {
    return {
      setResponseError(fetchError) {
        if (isString(fetchError)) {
          setAddError(fetchError);
        } else {
          setAddError(getValidationResult(fetchError));
        }
      },
    };
  });

  const [viewMode, setViewMode] = useState("button");

  const subjectRef = useRef();
  const contentRef = useRef();
  const attachFileRef = useRef();

  //dialog를 제어할 Ref
  const alertRef = useRef();

  // 저장을 클릭하면 입력했던 값을 가져와 출력한다.
  const onSaveButtonClickHandler = () => {
    console.log(alertRef);

    if (!subjectRef.current.value) {
      //dialog가 붙어 있으니 호출 가능
      alertRef.current.showModal("제목을 입력해주세요");
      return;
    } else if (!attachFileRef.current.value) {
      alertRef.current.showModal("ㅁㄴㅇ 입력해주세여");
      return;
    } else if (!contentRef.current.value) {
      alertRef.current.showModal("내용을 입력해주세요");
      return;
    }

    onAddArticleClick(
      subjectRef.current.value,
      contentRef.current.value,
      //file을 여러개 넣기 위해서 files 사용
      attachFileRef.current.files,
    );

    subjectRef.current.value = "";
    attachFileRef.current.value = "";
    contentRef.current.value = "";
  };

  const onViewChangeButtonClickHandler = (viewName) => {
    setViewMode(viewName);
  };

  return (
    <div className="article-writer">
      {viewMode === "button" && (
        <button
          type="button"
          onClick={onViewChangeButtonClickHandler.bind(this, "form")}
        >
          글쓰기
        </button>
      )}
      {viewMode === "form" && (
        <>
          <Alert dialogRef={alertRef} />
          {isString(addError) && <div>{addError}</div>}
          <Input id="subject" title="제목" ref={subjectRef} />
          <Textarea id="content" title="내용" ref={contentRef} />
          <Input
            type="file"
            id="file"
            title="첨부파일"
            ref={attachFileRef}
            multiple
          />

          <button
            type="button"
            className="positive-button"
            onClick={onSaveButtonClickHandler}
          >
            저장
          </button>
          <button
            type="button"
            className="negative-button"
            onClick={onViewChangeButtonClickHandler.bind(this, "button")}
          >
            취소
          </button>
        </>
      )}
    </div>
  );
};
export default ArticleWriter;
