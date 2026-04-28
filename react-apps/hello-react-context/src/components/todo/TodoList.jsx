/** @format */

import { useContext } from "react";
import TodoItem, { TodoItemForChildren } from "./TodoItem.jsx";
import { TodoContext } from "./contexts/TodoContext.jsx";

const TodoList = () => {
  const priorities = ["없음", "높음", "보통", "낮음"];

  const { todos } = useContext(TodoContext);

  return (
    <>
      {todos.map(({ id }) => (
        <TodoItem key={id} id={id} priorities={priorities} />
        // <TodoItemForChildren>
        //   <input id={todo.id} type="checkbox" />
        //   <label htmlFor={todo.id}>{todo.todo}</label>
        //   <span className="due-date">{todo.dueDate}</span>
        //   <span className="priority">{priorities[todo.priority]}</span>
        // </TodoItemForChildren>
      ))}
    </>
  );
};
export default TodoList;
