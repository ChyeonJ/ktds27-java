/** @format */

import { useRef } from "react";
import { Confirm } from "../ui/Modal";

const TodoHeader = ({ onAllDoneChange }) => {
  const confirmRef = useRef();
  const checkBoxRef = useRef();
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
