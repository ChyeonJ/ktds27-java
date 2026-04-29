/** @format */

import { Component, useContext } from "react";
import TodoContext from "./contexts/TodoContext";
import TodoItem, { TodoItemForChildren } from "./TodoItem";

const TodoList = ({ children }) => {
  console.log("투두리스트");

  const { componentName } = useContext(TodoContext);

  if (!componentName || componentName !== "TodoGrid") {
    return <></>;
  }

  //Context를 활용한 컴포넌트의 합성
  const providerProps = {
    // 현재 컴포넌트의 이름이 TodoList라고 명시
    componentName: "TodoList",
  };

  return (
    <TodoContext.Provider value={providerProps}>
      {children}
    </TodoContext.Provider>
  );
};
export default TodoList;
