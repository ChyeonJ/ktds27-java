/** @format */

import { useRef, useState } from "react";

const ArticleWriter2 = ({ onAddArticleClick }) => {
  console.log("ArticleWriter2");

  const subjectRef = useRef();
  const nameRef = useRef();
  const emailRef = useRef();
  const contentRef = useRef();

  const [viewMode, setViewMode] = useState("button");

  // 저장을 클릭하면 입력했던 값을 가져와 출력한다.
  const onSaveButtonClickHandler = () => {
    console.log("subjectRef", subjectRef.current.value);
    console.log("nameRef", nameRef.current.value);
    console.log("emailRef", emailRef.current.value);
    console.log("contentRef", contentRef.current.value);
    onAddArticleClick(
      subjectRef.current.value,
      nameRef.current.value,
      emailRef.current.value,
      contentRef.current.value,
    );

    subjectRef.current.value = "";
    nameRef.current.value = "";
    emailRef.current.value = "";
    contentRef.current.value = "";
  };

  const onViewChangeButtonClickHandler = (viewName) => {
    setViewMode(viewName);
    // if (viewName === "button") {
    // }
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
          <div className="input-field">
            <label htmlFor="subject">제목</label>
            <input type="text" id="subject" ref={subjectRef} />
          </div>

          <div className="input-field">
            <label htmlFor="name">이름</label>
            <input type="text" id="name" ref={nameRef} />
          </div>

          <div className="input-field">
            <label htmlFor="email">이메일</label>
            <input type="text" id="email" ref={emailRef} />
          </div>

          <div className="input-field">
            <label htmlFor="content">내용</label>
            <textarea id="content" ref={contentRef}></textarea>
          </div>

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
export default ArticleWriter2;
