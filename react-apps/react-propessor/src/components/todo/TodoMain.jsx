/** @format */

import TodoAppender from "./TodoAppender.jsx";
import TodoHeader from "./TodoHeader.jsx";
import TodoList from "./TodoList.jsx";

// ecma function (fat arrow function)
// const: 상수를 정의하는 키워드.
// (parameter) => {function body} : fat arrow function
// const abc = () => {};

// function과 fat arrow function의 기능적 차이.
// function => 함수를 호출한 대상을 this 객체로 알 수 있다.
// fat arrow function => this 키워드 사용 불가.
//         함수를 호출한 대상을 알 수 없다? event 파라미터로만 알 수 있음.

// export default 이후에 const 키워드가 나타날 수 없음.
const TodoMain = () => {
  // const ==> 상수 정의
  // let ==> 변수 정의
  // TODO JSON DATA
  const todoDatas = [
    {
      id: "todo_1",
      todo: "React Component Master",
      dueDate: "2026-04-22",
      priority: 1,
    },
    {
      id: "todo_2",
      todo: "React Component Master 2",
      dueDate: "2026-04-23",
      priority: 2,
    },
    {
      id: "todo_3",
      todo: "React Component Master 3",
      dueDate: "2026-04-24",
      priority: 3,
    },
  ];

  const onTaskKeyUpHandler = (event) => {
    console.log(event.target.value);
  };

  const onDateChangeHandler = (event) => {
    console.log(event.target.value);
  };

  const onSaveButtonClickHandler = () => {
    console.log("저장합니다.");
  };

  const onPrioritySelectChangeHandler = (event) => {
    console.log(event.target.value);
  };

  // 컴포넌트가 만들어줄 HTML Tag set를 반환.
  return (
    <div className="wrapper">
      <header>React Todo</header>
      <ul className="tasks">
        <TodoHeader />
        <TodoList todoDatas={todoDatas} />
      </ul>
      <TodoAppender
        onDateChange={onDateChangeHandler}
        onPrioritySelectChange={onPrioritySelectChangeHandler}
        onTaskKeyUp={onTaskKeyUpHandler}
        onSaveButtonClick={onSaveButtonClickHandler}
      />
    </div>
  );
};

export default TodoMain;
