import Todoitem, { TodoItemForChildren } from "./TodoItem";

const TodoList = ({ todoDatas, onDoneChange }) => {
  const priorities = ["없음", "높음", "보통", "낮음"];
  return (
    <>
      {todoDatas.map((todo) => (
        <Todoitem
          todo={todo}
          priorities={priorities}
          onDoneChange={onDoneChange}
        />
        // <TodoItemForChildren>
        //   <input id="{todo.id}" type="checkbox" />
        //   <label htmlFor="{todo.id}">{todo.todo}</label>
        //   <span className="due-date">{todo.dueDate}</span>
        //   <span className="priority">{priorities[todo.priority]}</span>
        // </TodoItemForChildren>
      ))}
    </>
  );
};
export default TodoList;
