const Todoitem = ({ todo, priorities, onDoneChange }) => {
  //   const { id, todo: todoTask, dueDate, priority } = todo;
  // chekced true라고하면 React는 checked를 추가한다라고 판단함 isDone 체크하면서 했던 내용

  const doneClass = todo.isDone ? "done" : "";

  const onDoneChangeHandler = () => {
    onDoneChange(todo.id);
  };

  return (
    <li className="tasks-item">
      <input
        id="{todo.id}"
        type="checkbox"
        checked={todo.isDone}
        onChange={onDoneChangeHandler}
      />
      <label className={doneClass} htmlFor="{todo.id}">
        {todo.todo}
      </label>
      <span className={`due-date ${doneClass}`}>{todo.dueDate}</span>
      <span className={`priority ${doneClass}`}>
        {priorities[todo.priority]}
      </span>
    </li>
  );
};
export default Todoitem;

export const TodoItemForChildren = ({ children }) => {
  return <li className="tasks-item">{children}</li>;
};
