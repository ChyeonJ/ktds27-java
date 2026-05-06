/** @format */

import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { articleThunks } from "../../stores/toolkit/slices/articleSlice";

const ArticleTable = ({ children }) => {
  const [viewPageNo, setViewPageNo] = useState(0);

  const onPaginationButtonClickHandler = (nextPageNo) => {
    setViewPageNo(nextPageNo);
  };

  const {
    pagination: { pageNo = 0, pageCount = 0 },
    error: { list },
  } = useSelector((store) => store.article);

  if (list) {
    alert(list);
  }

  const toolkitDispathcer = useDispatch();
  useEffect(() => {
    toolkitDispathcer(articleThunks.refresh(viewPageNo));
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
        {pageCount - 1 > pageNo && (
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
