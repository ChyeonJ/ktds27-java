/** @format */

import TodoItem, { TodoItemForChildren } from "./TodoItem";

const TodoList = ({ todoDatas }) => {
  const priorities = ["없음", "높음", "보통", "낮음"];

  return (
    <>
      {todoDatas.map((todo) => (
        //<TodoItem todo={todo} priorities={priorities} />
        <TodoItemForChildren>
          <input id={todo.id} type="checkbox" />
          <label htmlFor={todo.id}>{todo.todo}</label>
          <span className="due-date">{todo.dueDate}</span>
          <span className="priority">{priorities[todo.priority]}</span>
        </TodoItemForChildren>
      ))}
    </>
  );
};
export default TodoList;
