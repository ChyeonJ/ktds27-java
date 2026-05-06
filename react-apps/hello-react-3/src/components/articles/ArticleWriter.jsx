/** @format */

import { useRef, useState } from "react";
import { Alert } from "../ui/Modals";
import { isString } from "../../utils/type";
import { getValidationResult } from "../../utils/errorHandler";
import {
  fetchAddArticle,
  fetchArticleList,
} from "../../http/articles/fetchArticles";
import { useDispatch, useSelector } from "react-redux";
import { articleAction } from "../../stores/toolkit/slices/articleSlice";

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

const ArticleWriter = () => {
  console.log("ArticleWriter");

  const { token } = useSelector((store) => store.user);

  const [addError, setAddError] = useState();
  const [viewMode, setViewMode] = useState("button");

  const subjectRef = useRef();
  const contentRef = useRef();
  const attachFileRef = useRef();

  const alertRef = useRef();

  const toolkitDispatcher = useDispatch();

  if (!token) {
    return <></>;
  }

  // 저장을 클릭하면 입력했던 값을 가져와 출력한다.
  const onSaveButtonClickHandler = async () => {
    console.log(alertRef);

    if (!subjectRef.current.value) {
      alertRef.current.showModal("제목을 입력해주세요.");
      return;
    }
    if (!contentRef.current.value) {
      alertRef.current.showModal("내용을 입력해주세요.");
      return;
    }

    const addResult = await fetchAddArticle(
      token,
      subjectRef.current.value,
      contentRef.current.value,
      attachFileRef.current.files,
    );

    if (addResult.error) {
      if (isString(addResult.error)) {
        setAddError(addResult.error);
      } else {
        setAddError(getValidationResult(addResult.error));
      }
    }

    const fetchResult = await fetchArticleList();
    const {
      result: { count, result },
      pagination,
    } = fetchResult;
    toolkitDispatcher(articleAction.refresh({ count, result, pagination }));

    subjectRef.current.value = "";
    contentRef.current.value = "";
    attachFileRef.current.value = "";
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
