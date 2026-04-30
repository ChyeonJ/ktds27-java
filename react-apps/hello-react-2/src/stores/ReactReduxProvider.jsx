import { createStore } from "redux";
import { Provider } from "react-redux";

// React-Redux Reducer 생성/
/**
 *
 * @param {*} store React-Redux가 관리하는 state 저장소
 * @param {*} action Store의 state를 변경할 객체 (type, action)로 이루어짐
 */
const reactReduxReducer = (
  store = {
    todo: [],
  },
  action,
) => {
  console.log(action);

  const { type, payload } = action;
  if (type === "todoRefresh") {
    //store를 펼쳐서 새로운 객체를 생성해라
    return { ...store, todo: payload };
  } else if (type === "todo-all-done") {
    // return {   ==> 아래 코드 풀이
    //   ...store,
    //   todo: store.todo.map((eachTodo) => {
    //     eachTodo.done = true;
    //     return eachTodo;
    //   }),
    // };
    return {
      ...store,
      todo: store.todo.map((eachTodo) => ({ ...eachTodo, done: true })),
    };
  } else if (type === "todo-done-item") {
    return {
      ...store,
      todo: store.todo.map((eachTodo) => {
        if (eachTodo.id === payload) {
          eachTodo.done = true;
        }
        return eachTodo;
      }),
    };
  }

  return store; //store를 반환하면 파라미터에 있는 store가 바뀐데
};

// React-Redux-Store 생성
const createReactReduxStore = () => {
  return createStore(reactReduxReducer);
};

// React-Redux-Provider 생성
export const ReactReduxProvider = ({ children }) => {
  const store = createReactReduxStore();

  return <Provider store={store}>{children}</Provider>;
};
