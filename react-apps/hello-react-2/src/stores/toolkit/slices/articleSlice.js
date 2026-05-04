import { createSlice } from "@reduxjs/toolkit";
// ReduxToolkit slice store 생성
// export const articleSlice = createSlice({});

export const articleSlice = createSlice({
  name: "article-slice",
  initialState: {
    list: { count: 0, result: [], pagination: { pageNo: 0, pageCount: 0 } },
    token: null,
  },
  reducers: {
    loadArticleList(store, action) {
      store.list = action.payload;
    },
    setJsonWebToken(store, action) {
      store.token = action.payload;
    },
  },
});

export const articleAction = articleSlice.actions;
