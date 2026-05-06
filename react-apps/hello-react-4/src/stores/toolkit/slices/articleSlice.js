/** @format */
import { createSlice } from "@reduxjs/toolkit";
import {
  fetchAddArticle,
  fetchArticleList,
} from "../../../http/articles/fetchArticles";
import { isString } from "../../../utils/type";
import { getValidationResult } from "../../../utils/errorHandler";

// ReduxToolkit slice store 생성.
export const articleSlice = createSlice({
  name: "article-slice",
  initialState: {
    list: [],
    pagination: { pageNo: 0, pageCount: 0 },
    count: 0,
    error: {
      list: null,
      write: null,
    },
  },
  reducers: {
    refresh(store, action) {
      store.list = action.payload.result;
      store.pagination = action.payload.pagination;
      store.count = action.payload.count;
      store.error.list = null;
    },
    listError(store, action) {
      store.error.list = action.payload;
    },
    writeError(store, action) {
      if (isString(action.payload)) {
        store.error.write = action.payload;
      } else {
        store.error.write = getValidationResult(action.payload);
      }
    },
    clearWriteError(store) {
      store.error.write = null;
    },
  },
});

export const articleAction = articleSlice.actions;

export const articleThunks = {
  refresh(pageNo) {
    return async (dispatcher) => {
      const articleList = await fetchArticleList(pageNo);
      const {
        result: { count, result },
        pagination,
      } = articleList;

      dispatcher(articleAction.refresh({ count, result, pagination }));

      if (articleList.error) {
        dispatcher(articleAction.listError(articleList.error));
      }
    };
  },
  write(subject, content, attachFiles) {
    return async (dispatcher) => {
      const addResult = await fetchAddArticle(
        sessionStorage.getItem("token"),
        subject,
        content,
        attachFiles,
      );

      if (addResult.error) {
        dispatcher(articleAction.writeError(addResult.error));
      } else {
        dispatcher(articleAction.clearWriteError());
        dispatcher(articleThunks.refresh(0));
      }
    };
  },
};
