export const fetchLogin = async (id, pwd) => {
  try {
    const fetchResult = await fetch(
      "http://192.168.211.25:8080/api/authorization",
      {
        method: "post",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ email: id, password: pwd }),
      },
    );

    const loginResult = await fetchResult.json();
    return loginResult;
  } catch (e) {
    return {
      token: null,
      error: "서비스가 잠시 중단되었습니다. 잠시 후 다시 시도해주세요.",
    };
  }
};
