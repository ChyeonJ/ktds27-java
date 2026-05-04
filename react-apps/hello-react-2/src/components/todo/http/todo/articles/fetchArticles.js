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
      pagination: { pageNo: 0, pageCount: 0 },
      error: "서비스가 잠시 중단됨",
    };
  }
};

export const fetchJsonWebToken = async (email, password) => {
  try {
    const fetchResult = await fetch(
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
    const loginResult = await fetchResult.json();
    return loginResult;
  } catch (e) {
    return {
      token: null,
      error: "서비스가 잠시 중단되었습니다. 잠시 후 다시 시도해주세요.",
    };
  }
};

// 인증 정보 필요
export const fetchAddArticle = async (jwt, subject, content, attachfile) => {
  try {
    const formData = new FormData();
    formData.append("subject", subject);
    formData.append("content", content);

    // attachFile ==> FileList 배열
    // FileList내에 존재하는 파일 객체들을 attachFile로 하나씩 할당
    for (const file of attachfile) {
      formData.append("attachFile", file);
    }

    const articleResponse = await fetch(
      `http://192.168.211.25:8080/api/articles`,
      {
        method: "post",
        headers: {
          Authorization: jwt,
        },
        body: formData,
      },
    );

    const addResult = await articleResponse.json();
    console.log(addResult);

    return addResult;
  } catch (e) {
    return {
      result: false,
      error: "서비스가 잠시 중단됨",
    };
  }
};
