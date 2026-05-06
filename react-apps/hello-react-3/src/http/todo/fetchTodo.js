/** @format */

export const fetchTodoList = async () => {
  try {
    const todoResponse = await fetch("http://192.168.211.25:8080/api/v1/task");
    console.log(todoResponse);

    const todoList = await todoResponse.json();
    console.log(todoList);

    return todoList;
  } catch (e) {
    return {
      status: 500,
      statusMessage: "Internal Server Error",
      pages: 0,
      next: false,
      errors: "서비스가 잠시 중단되었습니다. 잠시 후 다시 시도해주세요.",
      count: 0,
      body: [],
    };
  }
};

export const fetchDoneTodo = async (todoId) => {
  try {
    const fetchResult = await fetch(
      `http://192.168.211.25:8080/api/v1/task/${todoId}`,
      { method: "put" },
    );

    const doneResult = await fetchResult.json();

    return doneResult;
  } catch (e) {
    return {
      status: 500,
      statusMessage: "Internal Server Error",
      pages: 0,
      next: false,
      errors: "서비스가 잠시 중단되었습니다. 잠시 후 다시 시도해주세요.",
      count: 0,
      body: "",
    };
  }
};

export const fetchAllDoneTodo = async () => {
  try {
    const fetchResult = await fetch("http://192.168.211.25:8080/api/v1/task", {
      method: "put",
    });

    const allDoneResult = await fetchResult.json();
    return allDoneResult;
  } catch (e) {
    return {
      status: 500,
      statusMessage: "Internal Server Error",
      pages: 0,
      next: false,
      errors: "서비스가 잠시 중단되었습니다. 잠시 후 다시 시도해주세요.",
      count: 0,
      body: null,
    };
  }
};

export const fetchAddTodo = async (todo, dueDate, priority) => {
  try {
    const fetchResult = await fetch("http://192.168.211.25:8080/api/v1/task", {
      method: "post",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ task: todo, dueDate, priority, isDone: false }),
    });

    const addResult = await fetchResult.json();
    return addResult;
  } catch (e) {
    return {
      status: 500,
      statusMessage: "Internal Server Error",
      pages: 0,
      next: false,
      errors: "서비스가 잠시 중단되었습니다. 잠시 후 다시 시도해주세요.",
      count: 0,
      body: {},
    };
  }
};
