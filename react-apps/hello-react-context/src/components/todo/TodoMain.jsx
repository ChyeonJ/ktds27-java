/** @format */

import { StateTest } from "./StateTest.jsx";
import TodoAppender from "./TodoAppender.jsx";
import TodoHeader from "./TodoHeader.jsx";
import TodoList from "./TodoList.jsx";
import TodoContextProvider from "./contexts/TodoContext.jsx";

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
  // 컴포넌트가 만들어줄 HTML Tag set를 반환.
  return (
    <div className="wrapper">
      {/* <StateTest /> */}

      <header>React Todo</header>
      <TodoContextProvider>
        <ul className="tasks">
          <TodoHeader />
          <TodoList />
        </ul>
        <TodoAppender />
      </TodoContextProvider>
    </div>
  );
};

export default TodoMain;
