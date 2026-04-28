/** @format */

import { useContext, useRef } from "react";
import { Confirm } from "../ui/Modal";
import TodoContext from "./contexts/TodoContext";

const TodoItem = ({ todo, onDoneChange }) => {
  const priorities = ["없음", "높음", "보통", "낮음"];

  // useRef(), useState()가 에러나는 이유는 함수가 동작 되기전에 리턴이 되면 안된다.
  // 즉 리턴이 일어나기 전에 use로 시작하는 함수는 위로 올라와 있어야 한다.
  const useDoneConfirmRef = useRef();
  const checkBoxRef = useRef();

  const { componentName } = useContext(TodoContext);

  console.log("TodoItem: " + componentName);

  // componentName이 존재하지 않음
  if (!componentName || componentName !== "TodoList") {
    // 잘못된 위치에 있다면 아무것도 보내지 말아라 라는뜻
    return <></>;
  }

  //        props todo의 이름과 todo.todo의 이름이 같아 객체 구조 분해 불가.
  //        todo.todo의 이름을 todoTask로 변경해 할당.
  const { id, todo: todoTask, dueDate, priority } = todo;

  const doneClass = todo.isDone ? "done" : "";

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
