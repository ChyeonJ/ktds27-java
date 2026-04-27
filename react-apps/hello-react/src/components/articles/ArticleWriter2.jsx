/** @format */

import { useRef } from "react";

const ArticleWriter2 = ({
  inputData: {
    subject,
    membersVO: { email, name },
    content,
  },
  onChangeSubject,
  onChangeEmail,
  onChangeName,
  onChangeContent,
  onSaveButton,
  onCancleButton,
}) => {
  const subjectRef = useRef;
  const emailRef = useRef();
  const nameRef = useRef();
  const contentRef = useRef();

  return (
    <div className="article-writer">
      <label>제목</label>
      <input
        id="subject"
        title="제목"
        ref={subjectRef}
        value={subject}
        onChange={onChangeSubject}
      />
      <label>이름</label>
      <input
        id="name"
        title="이름"
        value={name}
        ref={nameRef}
        onChange={onChangeName}
      />
      <label>이메일</label>
      <input
        id="email"
        title="이메일"
        value={email}
        ref={emailRef}
        onChange={onChangeEmail}
      />
      <label>내용</label>
      <textarea
        id="content"
        title="내용"
        ref={contentRef}
        value={content}
        onChange={onChangeContent}
      />

      <button type="button" className="positive-button" onClick={onSaveButton}>
        저장
      </button>
      <button
        type="button"
        className="negative-button"
        onClick={onCancleButton}
      >
        취소
      </button>
    </div>
  );
};
export default ArticleWriter2;
