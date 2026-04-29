/** @format */

import { useCallback, useEffect, useMemo, useState } from "react";
import { StateTest } from "./StateTest.jsx";
import TodoAppender from "./TodoAppender.jsx";
import TodoHeader from "./TodoHeader.jsx";
import TodoList from "./TodoList.jsx";
import TodoGrid from "./TodoGrid.jsx";
import TodoItem from "./TodoItem.jsx";
import AddCalcurator from "./AddCalcurator.jsx";

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
  console.log("투두메인");

  const [cachedData, setCachedData] = useState([]);

  // 비동기 함수는 promise를 반환시킨다.
  const fetchTodoList = async () => {
    const todoResponse = await fetch("http://localhost:8888/api/v1/task");
    console.log(todoResponse);

    const todoList = await todoResponse.json();
    console.log(todoList);

    setCachedData(todoList.body);
  };

  //비동기 json 데이터 함수 실행
  // 랜더링 되면서 => 재호출 하며 무한루프가 돈다
  // Side Effects => 백엔드 => 다른 코드에 영향이 있냐 없냐
  //              => 프론트 => 어떤 함수가 실행됨으로써 화면의 변화가 생기는 거
  // useEffect로 막을 수 있다
  // 특별한 상황일 때만 state가 변경되도록한다
  useEffect(() => {
    fetchTodoList();
  }, []);

  //반환 되는 데이터가 캐싱 됨
  const todoCount = useMemo(() => {
    return {
      all: cachedData.length,
      done: cachedData.filter((todo) => todo.done).length,
      process: cachedData.filter((todo) => !todo.done).length,
    };
  }, [cachedData]);

  //파라미터 첫번째는 함수, 두번째는 deps(DependencyList)
  const onAllDoneChangeHandler = useCallback(async () => {
    const fetchResult = await fetch("http://localhost:8888/api/v1/task", {
      method: "put",
    });
    console.log(fetchResult);

    fetchTodoList();
    const allDoneResult = await fetchResult.json();
    console.log(allDoneResult);
  }, []);

  // 특정 todo의 done 값을 반전시키는 함수.
  // 이 함수를 TodoList에게 props로 전달.
  // TodoList는 TodoItem에게 함수를 props 전달.
  const onDoneChangeHandler = async (todoId) => {
    const fetchResult = await fetch(
      `http://localhost:8888/api/v1/task/${todoId}`,
      { method: "put" },
    );
    fetchTodoList();
    console.log(fetchResult);

    const doneResult = await fetchResult.json();
    console.log(doneResult);
  };

  const onAddClickButtonHandler = useCallback((todo, dueDate, priority) => {
    console.log("저장합니다.");
    // fetch --> 서버에게 todo를 등록하게 한다.

    const fetchAddTodo = async () => {
      const fetchResult = await fetch("http://localhost:8888/api/v1/task", {
        method: "post",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          task: todo,
          dueDate,
          priority,
          isDone: false,
        }),
      });
      // fetch가 된 이후에 fetchTodoList실행
      // 비동기 함수는 비동기 함수 내부에 넣는게 순서상 맞다
      fetchTodoList();

      const addResult = await fetchResult.json();
      console.log(addResult);
    };

    fetchAddTodo();
  }, []);

  // 컴포넌트가 만들어줄 HTML Tag set를 반환.
  return (
    <div className="wrapper">
      {/* <StateTest /> */}

      <header>React Todo</header>
      <TodoGrid>
        <TodoHeader
          onAllDoneChange={onAllDoneChangeHandler}
          count={todoCount}
        />
        <TodoList>
          {cachedData.map((todo) => (
            <TodoItem
              key={todo.id}
              todo={todo}
              onDoneChange={onDoneChangeHandler}
            />
            // <TodoItemForChildren>
            //   <input id={todo.id} type="checkbox" />
            //   <label htmlFor={todo.id}>{todo.todo}</label>
            //   <span className="due-date">{todo.dueDate}</span>
            //   <span className="priority">{priorities[todo.priority]}</span>
            // </TodoItemForChildren>
          ))}
        </TodoList>
      </TodoGrid>
      <TodoAppender onSaveButtonClick={onAddClickButtonHandler} />
    </div>
  );
};

export default TodoMain;
