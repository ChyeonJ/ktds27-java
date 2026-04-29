/** @format */

import { memo, useRef } from "react";
import { Alert } from "../ui/Modal";

const TodoAppender = memo(({ onSaveButtonClick }) => {
  console.log("투두어펜더");

  const todoRef = useRef();
  const dueDateRef = useRef();
  const priorityRef = useRef();

  const alertRef = useRef();

  const onSaveButtonClickHandler = () => {
    if (!todoRef.current.value) {
      alertRef.current.showModal("내용을 입력하세여!");
      return;
    } else if (!dueDateRef.current.value) {
      alertRef.current.showModal("날짜를 입력하세여!");
      return;
    } else if (!priorityRef.current.value) {
      alertRef.current.showModal("우선순위를 입력하세여!");
      return;
    }

    onSaveButtonClick(
      todoRef.current.value,
      dueDateRef.current.value,
      priorityRef.current.value,
    );
    todoRef.current.value = "";
    dueDateRef.current.value = "";
    priorityRef.current.value = "";
  };

  return (
    <footer>
      <Alert dialogRef={alertRef} />
      <input type="text" ref={todoRef} placeholder="Input new task" />
      <input type="date" ref={dueDateRef} />
      <select ref={priorityRef}>
        <option value={""}>우선순위</option>
        <option value="1">높음</option>
        <option value="2">보통</option>
        <option value="3">낮음</option>
      </select>
      <button type="button" onClick={onSaveButtonClickHandler}>
        Save
      </button>
    </footer>
  );
});
export default TodoAppender;
