import { createSlice } from "@reduxjs/toolkit";

// ReduxToolkit slice store 생성
export const todoSlice = createSlice({
  name: "todo-slice", //action의 type으로 사용되는 이름
  initialState: {
    list: [],
  }, // todo-slice가 사용할 초기 state 값
  reducers: {
    refresh(store, action) {
      // toolkit이 가지고 있는 제한점
      // store의 메모리가 바뀌지 않음, 단 리스트의 메모리만 바꾼다
      // toolkit이 요구하는 요구사항
      store.list = action.payload;
    },
    doneItem(store, action) {
      //action ==> done 처리할 todo의 ID가 전달된다.
      // store.list에서 id가 action과 같은 todo의 인덱스를 찾아온다.
      const index = store.list.findIndex((todo) => todo.id === action.payload);
      store.list[index].done = true;
    },
    allDone(store) {
      store.list = store.list.map((todo) => ({ ...todo, done: true }));
    },
  },
});

//Reducer가 Reducers에 있는 것을 가져다 쓰게함 actions
export const todoAction = todoSlice.actions;
console.log("asdasdasdasdasfsdfsdjfhhdsfkj");
console.log(todoAction);
