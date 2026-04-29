export const fetchTodoList = async () => {
  try {
    const todoResponse = await fetch("http://localhost:8888/api/v1/task");

    console.log("asdkjhaskfjhadlkjfhlsdiufliudshfliusdgfliukdf");
    console.log(todoResponse);

    const todoList = await todoResponse.json();
    console.log(todoList);

    return todoList;
  } catch (e) {
    // 객체를 돌려주면 됨 e라는 이름을 정하면 e:{}를 가지게 됨
    return {
      status: 500,
      statusMessage: "Internal Server Error",
      pages: 0, //pagenation할 때 사용
      next: false,
      errors: "서비스가 잠시 중단 되었습니다. 잠시 후 다시 시도해주세요",
      count: 0,
      body: [], //null하면 에러가 많아서 배열처리
    };
  }
};

export const fetchAllDoneTodo = async () => {
  try {
    const fetchResult = await fetch("http://localhost:8888/api/v1/task", {
      method: "put",
    });
    console.log(fetchResult);

    const allDoneResult = await fetchResult.json();
    console.log(allDoneResult);
    return allDoneResult();
  } catch (e) {
    return {
      status: 500,
      statusMessage: "Internal Server Error",
      pages: 0, //pagenation할 때 사용
      next: false,
      errors: "서비스가 잠시 중단 되었습니다. 잠시 후 다시 시도해주세요",
      count: 0,
      body: "",
    };
  }
};

export const fetchDoneTodo = async (todoId) => {
  try {
    const fetchResult = await fetch(
      `http://localhost:8888/api/v1/task/${todoId}`,
      { method: "put" },
    );
    console.log(fetchResult);

    const doneResult = await fetchResult.json();
    console.log(doneResult);
    return doneResult;
  } catch (e) {
    return {
      status: 500,
      statusMessage: "Internal Server Error",
      pages: 0, //pagenation할 때 사용
      next: false,
      errors: "서비스가 잠시 중단 되었습니다. 잠시 후 다시 시도해주세요",
      count: 0,
      body: null,
    };
  }
};

export const fetchAddTodo = async (todo, dueDate, priority) => {
  try {
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
    const addResult = await fetchResult.json();
    console.log(addResult);

    return addResult;
  } catch (e) {
    return {
      status: 500,
      statusMessage: "Internal Server Error",
      pages: 0, //pagenation할 때 사용
      next: false,
      errors: "서비스가 잠시 중단 되었습니다. 잠시 후 다시 시도해주세요",
      count: 0,
      body: {},
    };
  }
};
