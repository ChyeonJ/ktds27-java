/** @format */

import { useRef } from "react";
import { Confirm } from "../ui/Modal";

const TodoItem = ({ todo, priorities, onDoneChange }) => {
  //        props todo의 이름과 todo.todo의 이름이 같아 객체 구조 분해 불가.
  //        todo.todo의 이름을 todoTask로 변경해 할당.
  const { id, todo: todoTask, dueDate, priority } = todo;

  const doneClass = todo.isDone ? "done" : "";

  const useDoneConfirmRef = useRef();
  const checkBoxRef = useRef();

  const onsaveClickHandler = () => {
    const chekced = checkBoxRef.current.checked;
    let message = "";
    if (chekced) {
      message = `${todoTask} 을 완료 하시겠습니까?`;
    } else {
      message = `${todoTask} 을 미완료 하시겠습니까?`;
    }

    useDoneConfirmRef.current.showConfirm(message);
  };

  const onConfirmOkClickHandler = () => {
    onDoneChange(todo.id, !todo.isDone);
  };

  const onConfirmCloseClickHandler = () => {};

  return (
    <li className="tasks-item">
      <Confirm
        dialogRef={useDoneConfirmRef}
        onOkClick={onConfirmOkClickHandler}
        onCloseClick={onConfirmCloseClickHandler}
      />
      <input
        id={id}
        type="checkbox"
        checked={todo.isDone}
        ref={checkBoxRef}
        onChange={onsaveClickHandler}
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
