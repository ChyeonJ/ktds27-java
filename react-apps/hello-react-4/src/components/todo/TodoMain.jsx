/** @format */

import { useEffect } from "react";
import { StateTest } from "./StateTest.jsx";
import TodoAppender from "./TodoAppender.jsx";
import TodoHeader from "./TodoHeader.jsx";
import TodoList from "./TodoList.jsx";
import TodoItem from "./TodoItem.jsx";
import TodoGrid from "./TodoGrid.jsx";
import AddCalculator from "./AddCalculator.jsx";
import { fetchTodoList } from "../../http/todo/fetchTodo.js";
import { useDispatch, useSelector } from "react-redux";
import { todoAction } from "../../stores/toolkit/slices/todoSlice.js";

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
  console.log("TodoMain");

  // const [cachedData, setCachedData] = useState([]);
  // ReactRedux Store에서 todo state를 가져온다.
  const { list: todoList } = useSelector((store) => store.todo);
  console.log("TodoList State", todoList);

  const storeDispatcher = useDispatch();

  const refreshTodoList = async () => {
    const fetchResult = await fetchTodoList();
    //setCachedData(todoList.body);
    // storeDispatcher({ type: "todo-slice/refresh", payload: fetchResult.body });
    storeDispatcher(todoAction.refresh(fetchResult.body));

    if (fetchResult.errors) {
      alert(fetchResult.errors);
    }
  };

  useEffect(() => {
    refreshTodoList();
  }, []);

  // 컴포넌트가 만들어줄 HTML Tag set를 반환.
  return (
    <div className="wrapper">
      <header>React Todo</header>
      <TodoGrid>
        <TodoHeader />
        <TodoList>
          {todoList.map((todo) => (
            <TodoItem key={todo.id} todo={todo} />
          ))}
        </TodoList>
      </TodoGrid>
      <TodoAppender />
    </div>
  );
};

export default TodoMain;
