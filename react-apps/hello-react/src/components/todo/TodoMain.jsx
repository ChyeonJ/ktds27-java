//function (fat arrow function)
// const : 상수를 정의하는 키워드
// (parameter) => {function body} : fat arrow function
// const abc = () => {};

import { useState } from "react";
import { StateTest } from "./StateTest.jsx";
import TodoAppender from "./TodoAppender.jsx";
import TodoHeader from "./TodoHeader.jsx";
import TodoList from "./TodoList.jsx";

// function과 far arrow function의 기능적 차이
// function => 함수를 호출한 대상을 this 객체로 알 수 있다.
// fat arrow function => this 키워드 사용불가
//          함수를 호출한 대상을 알 수 없다? event 파라미터로만 알 수 있음

// export default 이후에 const 키워드가 나타날 수 없음
const TodoMain = () => {
  // const 상수
  // let 변수
  // TODO JSON DATA
  const todoDatas = [
    {
      id: "todo_1",
      todo: "React Component Master",
      dueDate: "2026-04-22",
      priority: 1,
      //완료된 TODO냐
      isDone: true,
    },
    {
      id: "todo_2",
      todo: "React Component Master2",
      dueDate: "2026-04-23",
      priority: 2,
      isDone: false,
    },
    {
      id: "todo_3",
      todo: "React Component Master3",
      dueDate: "2026-04-24",
      priority: 3,
      isDone: false,
    },
  ];

  const [cachedData, setCachedData] = useState(todoDatas);

  //함수를 만들어서 대입 해줌
  const onTaskKeyUpHandler = (event) => {
    console.log(event.target.value);
  };

  const onDateChangeHandler = (event) => {
    console.log(event.target.value);
  };

  const onSaveButtonClickHandler = () => {
    console.log("저장합니다");
  };

  const onPrioritySelectChangeHandler = (event) => {
    var selectVal = event.target.value;
    console.log(selectVal);
  };

  // 특정 todo의 isDone 값을 반전시키는 함수
  // 이 함수를 TodoList에게 props로 전달
  // TodoList는 TodoItem에게 함수를 props 전달
  const onDoneChangeHandler = (todoId) => {
    setCachedData((prevData) => {
      const newStateMemory = [...prevData]; //메모리 복제

      //java의 for each와 같은 형태의 반복문 원래는 이렇게 사용하지 않음
      for (const todo of newStateMemory) {
        if (todo.id === todoId) {
          todo.isDone = true;
          break;
        }
      }
      return newStateMemory;
    });
    console.log(todoId, todoDatas);
  };

  // 컴포넌트가 만들어줄 HTML Tag set을 반환
  return (
    <div className="wrapper">
      {/* <StateTest /> */}
      <header>React Todo</header>
      <ul className="tasks">
        <TodoHeader />
        <TodoList todoDatas={cachedData} onDoneChange={onDoneChangeHandler} />
      </ul>
      <TodoAppender
        onTaskKeyUp={onTaskKeyUpHandler}
        onDateChange={onDateChangeHandler}
        onPrioritySelectChange={onPrioritySelectChangeHandler}
        onSaveButtonClick={onSaveButtonClickHandler}
      />
    </div>
  );
};

export default TodoMain;
