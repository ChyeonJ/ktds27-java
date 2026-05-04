/** @format */
// articles.json 파일 불러오기
import { useEffect, useRef, useState } from "react";
import ArticleHeader from "./ArticleHeader.jsx";
import ArticleList from "./ArticleList.jsx";
import ArticleWriter from "./ArticleWriter.jsx";
import {
  fetchAddArticle,
  fetchArticleList,
} from "../todo/http/todo/articles/fetchArticles.js";
import ArticleLogin from "./ArticleLogin.jsx";
import { useDispatch, useSelector } from "react-redux";
import { articleAction } from "../../stores/toolkit/slices/articleSlice.js";

const ArticleMain = () => {
  // state를 변경했다!
  // 컴포넌트가 재실행된다. (props의 전달 여부 관계 없이.)
  console.log("ArticleMain");

  const token = useSelector((store) => store.article.token);
  const {
    count,
    result: articles,
    pagination: { pageNo = 0, pageCount = 0 },
  } = useSelector((store) => store.article.list);

  const articleStoreDispathcer = useDispatch();

  const [viewPageNo, setViewPageNo] = useState(0);

  const onPaginationButtonClickHandler = (nextPageNo) => {
    console.log("asdasdasd");
    console.log(nextPageNo);
    setViewPageNo(nextPageNo);
  };

  const articleList = async () => {
    const articleListResult = await fetchArticleList(viewPageNo);

    if (articleListResult.error) {
      alert(articleListResult.error);
    }

    const {
      result: { count, result },
      pagination,
    } = articleListResult;

    articleStoreDispathcer(
      articleAction.loadArticleList({ count, result, pagination }),
    );
  };
  useEffect(() => {
    articleList();
  }, [viewPageNo]);

  console.log("★Token:", token);

  const writeRef = useRef();

  const onAddArticleClickHandler = async (subject, content, attachFile) => {
    const addResult = await fetchAddArticle(
      token,
      subject,
      content,
      attachFile,
    );

    if (addResult.error) {
      writeRef.current.setResponseError(addResult.error);
    } else {
      articleList();
    }

    articleList();
  };

  return (
    <div className="wrapper">
      {token == null ? <ArticleLogin /> : <></>}
      <div>{count}개의 게시글이 검색되었습니다.</div>
      <table>
        <ArticleHeader />
        <ArticleList contents={articles} />
      </table>
      <div>
        {pageNo > 0 && (
          <button
            onClick={onPaginationButtonClickHandler.bind(this, pageNo - 1)}
          >
            이전
          </button>
        )}
        {pageNo === 0 && pageCount - 1 > pageNo && (
          <button
            onClick={onPaginationButtonClickHandler.bind(this, pageNo + 1)}
          >
            다음
          </button>
        )}
      </div>
      <ArticleWriter
        onAddArticleClick={onAddArticleClickHandler}
        errorHandleRef={writeRef}
      />
    </div>
  );
};
export default ArticleMain;
