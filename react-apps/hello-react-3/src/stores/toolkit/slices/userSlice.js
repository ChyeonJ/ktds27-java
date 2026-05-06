/** @format */
import { createSlice } from "@reduxjs/toolkit";
import { fetchLogin } from "../../../http/articles/fetchLogin";
import { isString } from "../../../utils/type";
import { getValidationResult } from "../../../utils/errorHandler";

// ReduxToolkit slice store 생성.
export const userSlice = createSlice({
  name: "user-slice",
  initialState: {
    token: null,
    info: null,
    error: null,
  },
  reducers: {
    login(store, action) {
      store.token = action.payload;
      store.error = null;
    },
    autoLogin(store) {
      //session storage에 있는 token을 가져와서 userSlice에 등록한다.
      const token = sessionStorage.getItem("token");
      if (token) {
        store.token = token;
      }
    },
    logout(store) {
      store.token = null;
      store.info = null;
    },
    loadMyInfo(store, payload) {
      store.info = payload.payload;
    },
    error(store, action) {
      if (isString(action.payload)) {
        store.error = action.payload;
      } else {
        store.error = getValidationResult(action.payload);
      }
    },
  },
});

export const userAction = userSlice.actions;

// toolkit slice store에 대한 custom action(reducer) ==> fetch + dispatch 생성
export const userThunks = {
  login(email, password) {
    //dispatch => useDispatch()의 결과가 파라미터로 전달
    return async (dispatcher) => {
      //fetch
      const loginResult = await fetchLogin(email, password);
      //dispatch
      if (!loginResult.error) {
        sessionStorage.setItem("token", loginResult.token);
        dispatcher(userAction.login(loginResult.token));
      } else {
        dispatcher(userAction.error(loginResult.error));
      }
    };
  },
  loadMyInfo() {},
};
