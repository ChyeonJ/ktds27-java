import { createSlice } from "@reduxjs/toolkit";
// ReduxToolkit slice store 생성
// export const articleSlice = createSlice({});

export const articleSlice = createSlice({
  name: "article-slice",
  initialState: {
    list: [],
  },
  reducers: {
    laodArticleList(store, action) {
      store.list = action.payload;
    },
    jsonWebToken(store, action) {
      store.list = action.payload;
    },
    addArticle(store, action) {
      store.list = action.payload;
    },
  },
});
