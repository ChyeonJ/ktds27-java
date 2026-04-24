/** @format */
// articles.json 파일 불러오기
import { useState } from "react";
import ArticleHeader from "./ArticleHeader.jsx";
import ArticleList from "./ArticleList.jsx";
import articleData from "./articles.json";
import ArticleWriter from "./ArticleWriter.jsx";

const ArticleMain = () => {
  // articles 객체 state 저장
  const [articlesData, setArticleData] = useState(articleData.articles);

  const [
    {
      subject,
      membersVO: { email, name },
      content,
    },
    setInputData,
  ] = useState({
    subject: "",
    membersVO: { email: "", name: "" },
    content: "",
  });

  const onChangeSubjectHandler = (event) => {
    setInputData((prevData) => ({ ...prevData, subject: event.target.value }));
  };
  const onChangeNameHandler = (event) => {
    setInputData((prevData) => ({
      ...prevData,
      membersVO: { ...prevData.membersVO, name: event.target.value },
    }));
  };
  const onChangeEmailHandler = (event) => {
    console.log(event.target.value);
    setInputData((prevData) => ({
      ...prevData,
      membersVO: { ...prevData.membersVO, email: event.target.value },
    }));
  };
  const onChangeContentHandler = (event) => {
    setInputData((prevData) => ({ ...prevData, content: event.target.value }));
  };

  const onSaveButtonHandler = () => {
    setArticleData((prevData) => [
      ...prevData,
      { id: prevData.length + 1, subject, name, email, content },
    ]);
    setInputData(() => ({
      subject: "",
      membersVO: { email: "", name: "" },
      content: "",
    }));
  };

  const onCancleButtonHandler = () => {
    setInputData(() => ({
      subject: "",
      email: "",
      name: "",
      content: "",
    }));
  };

  return (
    <div className="wrapper">
      <div>{articlesData.length}개의 게시글이 검색되었습니다.</div>
      <table>
        <ArticleHeader />
        <ArticleList contents={articlesData} />
      </table>
      <ArticleWriter
        inputData={{ subject, membersVO: { email, name }, content }}
        onChangeSubject={onChangeSubjectHandler}
        onChangeEmail={onChangeEmailHandler}
        onChangeName={onChangeNameHandler}
        onChangeContent={onChangeContentHandler}
        onSaveButton={onSaveButtonHandler}
        onCancleButton={onCancleButtonHandler}
      />
    </div>
  );
};
export default ArticleMain;
