/** @format */

import { createContext, useState } from "react";

// CommonJS
// const { createContext } = require("react");

export const TodoContext = createContext({
  todos: [],
  done(todoId, doneStatus) {},
  allDone(doneStatus) {},
  addTodo(taskName, dueDate, priority) {},
  getTodo(todoId) {},
});

const TodoContextProvider = ({ children }) => {
  // TodoContext를 제공하는 컴포넌트

  const todoDatas = [
    {
      id: "todo_1",
      todo: "React Component Master",
      dueDate: "2026-04-22",
      priority: 1,
      isDone: true,
    },
    {
      id: "todo_2",
      todo: "React Component Master 2",
      dueDate: "2026-04-23",
      priority: 2,
      isDone: false,
    },
    {
      id: "todo_3",
      todo: "React Component Master 3",
      dueDate: "2026-04-24",
      priority: 3,
      isDone: false,
    },
  ];

  const [cachedData, setCachedData] = useState(todoDatas);

  const todoContextProps = {
    todos: cachedData,
    done(todoId, doneStatus) {
      setCachedData((prevData) => {
        // const newStateMemory = [...prevData];
        // java for each
        // for (const todo of newStateMemory) {
        //   if (todo.id === todoId) {
        //     todo.isDone = doneStatus;
        //     break;
        //   }
        // }
        const newStateMemory = prevData.map((todo) => {
          if (todo.id === todoId) {
            todo.isDone = doneStatus;
          }
          return todo;
        });
        return newStateMemory;
      });
    },
    allDone(doneStatus) {
      setCachedData((prevData) => {
        // cachedData를 반복하면서 모든 isDone의 값을 변경한다.
        const newData = prevData.map((todo) => ({
          ...todo,
          isDone: doneStatus,
        }));
        // 변경된 결과를 반환한다.
        return newData;
      });
    },
    addTodo(taskName, dueDate, priority) {
      setCachedData((prevData) => [
        ...prevData,
        {
          id: prevData.length + 1,
          todo: taskName,
          dueDate,
          priority,
          isDone: false,
        },
      ]);
    },
    getTodo(todoId) {
      const todo = cachedData.find((eachTodo) => eachTodo.id === todoId);
      return todo;
    },
  };

  // Context의 Provider 값을 공유받을 수 있는 컴포넌트는
  // Context.Provider의 자식 컴포넌트만 대상.
  return (
    <TodoContext.Provider value={todoContextProps}>
      {children}
    </TodoContext.Provider>
  );
};

export default TodoContextProvider;
