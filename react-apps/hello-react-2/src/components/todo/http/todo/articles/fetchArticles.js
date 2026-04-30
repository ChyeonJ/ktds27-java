export const fetchArticleList = async (pageNo = 0, listSize = 10) => {
  try {
    const articleResponse = await fetch(
      //http://192.168.211.11:8080/?pageNo=0&listSize=10&searchType=&searchKeyword
      `http://192.168.211.25:8080/api/articles?pageNo=${pageNo}&listSize=${listSize}&searchType=&searchKeyword`,
    );

    const articleList = await articleResponse.json();
    console.log(articleList);

    return articleList;
  } catch (e) {
    return {
      result: { count: 0, result: [] },
      pagination: {},
      error: "서비스가 잠시 중단됨",
    };
  }
};

export const fetchJsonWebToken = async (email, password) => {
  try {
    const loginResult = await fetch(
      "http://192.168.211.25:8080/api/authorization",
      {
        method: "post",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          email: email,
          password: password,
        }),
      },
    );
    return loginResult.json();
  } catch (e) {
    return;
  }
};

// 인증 정보 필요
export const fetchAddArticle = () => {};
