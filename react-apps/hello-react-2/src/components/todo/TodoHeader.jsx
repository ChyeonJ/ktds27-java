/** @format */

import { useContext, useRef } from "react";
import { Confirm } from "../ui/Modal";
import TodoContext from "./contexts/TodoContext";

const TodoHeader = ({ onAllDoneChange }) => {
  console.log("투두헤더");

  const confirmRef = useRef();
  const checkBoxRef = useRef();

  //use가 사용된 이후에 컴포넌트 합성 진행
  const { componentName } = useContext(TodoContext);

  if (!componentName || componentName !== "TodoGrid") {
    return <></>;
  }

  const onAllDoneChangeHandler = () => {
    const chekced = checkBoxRef.current.checked;
    let message = "";
    if (chekced) {
      message = "모든 Item들을 '완료' 하시겠습니까?";
    } else {
      message = "모든 Item들을 '미완료' 하시겠습니까?";
    }

    confirmRef.current.showConfirm(message);
  };

  const onConfirmOkClickHandler = () => {
    onAllDoneChange(checkBoxRef.current.checked);
  };
  const onConfirmCloseClickHandler = () => {
    checkBoxRef.current.checked = !checkBoxRef.current.checked;
  };

  return (
    <li className="tasks-header">
      <Confirm
        dialogRef={confirmRef}
        onOkClick={onConfirmOkClickHandler}
        onCloseClick={onConfirmCloseClickHandler}
      />
      <input
        id="checkall"
        type="checkbox"
        onChange={onAllDoneChangeHandler}
        ref={checkBoxRef}
      />
      <label>Task</label>
      <span className="due-date">Due Date</span>
      <span className="priority">Priority</span>
    </li>
  );
};
export default TodoHeader;
