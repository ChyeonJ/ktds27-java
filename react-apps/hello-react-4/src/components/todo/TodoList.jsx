/** @format */

import { useContext } from "react";
import TodoContext from "./contexts/TodoContext";
import TodoItem, { TodoItemForChildren } from "./TodoItem";

const TodoList = ({ children }) => {
  console.log("TodoList");

  const { componentName } = useContext(TodoContext);
  if (!componentName || componentName !== "TodoGrid") {
    return <></>;
  }

  const providerProps = {
    componentName: "TodoList",
  };

  return (
    <TodoContext.Provider value={providerProps}>
      {children}
    </TodoContext.Provider>
  );
};
export default TodoList;
