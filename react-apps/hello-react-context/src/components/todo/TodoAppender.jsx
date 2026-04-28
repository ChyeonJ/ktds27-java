/** @format */

import { useContext, useRef } from "react";
import { Alert } from "../ui/Modals.jsx";
import { TodoContext } from "./contexts/TodoContext.jsx";

const TodoAppender = () => {
  const { addTodo } = useContext(TodoContext);
  const todoAlertRef = useRef();

  const taskRef = useRef();
  const dueDateRef = useRef();
  const priorityRef = useRef();

  const onSaveButtonClickHandler = () => {
    if (!taskRef.current.value) {
      todoAlertRef.current.showModal("TODO를 입력하세요.");
      return;
    }
    if (!dueDateRef.current.value) {
      todoAlertRef.current.showModal("완료 날짜를 선택하세요.");
      return;
    }
    if (!priorityRef.current.value) {
      todoAlertRef.current.showModal("우선순위를 선택하세요.");
      return;
    }

    addTodo(
      taskRef.current.value,
      dueDateRef.current.value,
      priorityRef.current.value,
    );

    taskRef.current.value = "";
    dueDateRef.current.value = "";
    priorityRef.current.value = "";
  };

  return (
    <footer>
      <Alert dialogRef={todoAlertRef} />
      <input type="text" placeholder="Input new task" ref={taskRef} />
      <input type="date" ref={dueDateRef} />
      <select ref={priorityRef}>
        <option value="">우선순위</option>
        <option value="1">높음</option>
        <option value="2">보통</option>
        <option value="3">낮음</option>
      </select>
      <button type="button" onClick={onSaveButtonClickHandler}>
        Save
      </button>
    </footer>
  );
};
export default TodoAppender;
