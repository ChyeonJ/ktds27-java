/** @format */
// articles.json 파일 불러오기
import ArticleTable from "./ArticleTable.jsx";
import ArticleHeader from "./ArticleHeader.jsx";
import ArticleList from "./ArticleList.jsx";
import ArticleWriter from "./ArticleWriter.jsx";
import ArticleWriter2 from "./ArticleWriter2.jsx";
import { useSelector } from "react-redux";
import Login from "../user/Login.jsx";

const ArticleMain = () => {
  // state를 변경했다!
  // 컴포넌트가 재실행된다. (props의 전달 여부 관계 없이.)
  console.log("ArticleMain");

  const { count } = useSelector((store) => store.article);

  return (
    <div className="wrapper">
      <Login />
      <div>{count}개의 게시글이 검색되었습니다.</div>
      <ArticleTable>
        <ArticleHeader />
        <ArticleList />
      </ArticleTable>
      <ArticleWriter />
    </div>
  );
};
export default ArticleMain;
