/** @format */

import { memo, useRef, useState } from "react";
import { Alert } from "../ui/Modal";
import { fetchAddTodo, fetchTodoList } from "./http/todo/fetchTodo";
import { useDispatch } from "react-redux";
import { todoAction } from "../../stores/toolkit/slices/todoSlice";

const TodoAppender = memo(() => {
  console.log("투두어펜더");

  //fetching 중이면 false fetching되면 true
  const [isFetching, setIsFetching] = useState(false);

  const todoRef = useRef();
  const dueDateRef = useRef();
  const priorityRef = useRef();

  const alertRef = useRef();

  const reactReduxDispatcher = useDispatch();

  const onSaveButtonClickHandler = async () => {
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
    // // 낙관적 업데이트 등록에 관련해서는 PK 떄문에 안된다 sequence 때문에
    // reactReduxDispatcher({
    //   type: "todo-add",
    //   payload: {
    //     task: todoRef.current.value,
    //     dueDateRef: dueDateRef.current.value,
    //     priority: priorityRef.current.value,
    //   },
    // });

    setIsFetching(true);

    const addResult = await fetchAddTodo(
      todoRef.current.value,
      dueDateRef.current.value,
      priorityRef.current.value,
    );

    if (addResult.errors) {
      alert(addResult.errors);
    }

    setIsFetching(false);

    const fetchResult = await fetchTodoList();
    reactReduxDispatcher(todoAction.refresh(fetchResult.body));

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
      <button
        type="button"
        disabled={isFetching} //버튼 활성화 비활성화
        onClick={onSaveButtonClickHandler}
      >
        {isFetching ? "저장중.." : "저장"}
      </button>
    </footer>
  );
});
export default TodoAppender;
