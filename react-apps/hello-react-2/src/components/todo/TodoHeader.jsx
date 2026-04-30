/** @format */

import { useContext, useRef } from "react";
import { Confirm } from "../ui/Modal";
import TodoContext from "./contexts/TodoContext";
import { useDispatch, useSelector } from "react-redux";
import { memo } from "react";
import { fetchAllDoneTodo, fetchTodoList } from "./http/todo/fetchTodo";

// 컴포넌트가 메모 함수의 파라미터다
const TodoHeader = memo(() => {
  console.log("투두헤더");

  const confirmRef = useRef();
  const checkBoxRef = useRef();
  const reactReduxDispatcher = useDispatch();

  // react-redux store => todo 가져오기
  const todoList = useSelector((store) => store.todo);
  const count = {
    all: todoList.length,
    done: todoList.filter((todo) => todo.done).length,
    process: todoList.filter((todo) => !todo.done).length,
  };
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

  const onConfirmOkClickHandler = async () => {
    // all done에 대한 낙관적 업데이트 진행
    // 사용자가 all done을 요청했을 때, 요청 결과와 상관 없이 우선 all done이 된것 처럼 보여준다.
    // fetch 이후에 실패했을 경우, 원래 상태로 돌려준다.
    //             성공했을 경우, 변경된 상태 유지
    //             all done을 수행하는 중에 다른 사용자로 인해 데이터가 추가됐다면 불러올 필요가 있음
    reactReduxDispatcher({ type: "todo-all-done" }); //payload에 마땅히 보낼게 없으면 그냥 안보내면 된다

    const allDoneResult = await fetchAllDoneTodo();
    if (allDoneResult.errors) {
      alert(allDoneResult.errors);
    }
    //위에서 에러가 나면 다시 불러와라 => 그러면 원상복구가 될 것이다
    const fetchResult = await fetchTodoList();
    reactReduxDispatcher({ type: "todo-refresh", payload: fetchResult.body });
  };
  const onConfirmCloseClickHandler = () => {
    checkBoxRef.current.checked = !checkBoxRef.current.checked;
  };

  return (
    <>
      <li className="tasks-counter">
        <div>전체: {count.all}</div>
        <div>진행중:{count.process}</div>
        <div>완료:{count.done}</div>
      </li>
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
    </>
  );
});
export default TodoHeader;
