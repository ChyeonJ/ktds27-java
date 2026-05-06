/** @format */

import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { articleAction } from "../../stores/toolkit/slices/articleSlice";
import { fetchArticleList } from "../../http/articles/fetchArticles";

const ArticleTable = ({ children }) => {
  const [viewPageNo, setViewPageNo] = useState(0);

  const onPaginationButtonClickHandler = (nextPageNo) => {
    setViewPageNo(nextPageNo);
  };

  const {
    pagination: { pageNo = 0, pageCount = 0 },
  } = useSelector((store) => store.article);

  const toolkitDispathcer = useDispatch();

  const refreshArticleList = async () => {
    const articleList = await fetchArticleList(viewPageNo);
    const {
      result: { count, result },
      pagination,
    } = articleList;

    toolkitDispathcer(articleAction.refresh({ count, result, pagination }));

    if (articleList.error) {
      alert(articleList.error);
    }
  };

  useEffect(() => {
    refreshArticleList();
  }, [viewPageNo]);

  return (
    <>
      <table>{children}</table>

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
    </>
  );
};
export default ArticleTable;
