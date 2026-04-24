/** @format */

const TodoItem = ({ todo, priorities, onDoneChange }) => {
  //        props todo의 이름과 todo.todo의 이름이 같아 객체 구조 분해 불가.
  //        todo.todo의 이름을 todoTask로 변경해 할당.
  const { id, todo: todoTask, dueDate, priority } = todo;

  const doneClass = todo.isDone ? "done" : "";

  const onDoneChangeHandler = () => {
    onDoneChange(todo.id, !todo.isDone);
  };

  return (
    <li className="tasks-item">
      <input
        id={id}
        type="checkbox"
        checked={todo.isDone}
        onChange={onDoneChangeHandler}
      />
      <label className={doneClass} htmlFor={id}>
        {todoTask}
      </label>
      <span className={`due-date ${doneClass}`}>{dueDate}</span>
      <span className={`priority ${doneClass}`}>{priorities[priority]}</span>
    </li>
  );
};
export default TodoItem;

export const TodoItemForChildren = ({ children }) => {
  return <li className="tasks-item">{children}</li>;
};

export const abc = "123123";
