import { useRef, useState } from "react";
import { isString } from "../utils/type";
import getValidationResult from "../utils/errorHandler.js";
import { useDispatch } from "react-redux";
import { articleAction } from "../../stores/toolkit/slices/articleSlice.js";
import { fetchJsonWebToken } from "../todo/http/todo/articles/fetchArticles";
import { fetchLogin } from "../todo/http/todo/articles/fetchLogin.js";

const ArticleLogin = () => {
  const emailRef = useRef();
  const pwdRef = useRef();

  // const [token, setToken] = useState(); store 삭제
  const [loginErrors, setLoginErrors] = useState();
  const articleStoreDispathcer = useDispatch();

  const onLoginDataHandler = async () => {
    const tokenResult = await fetchLogin(
      emailRef.current.value,
      pwdRef.current.value,
    );

    if (tokenResult.error) {
      if (isString(tokenResult.error)) {
        setLoginErrors(tokenResult.error);
      } else {
        setLoginErrors(getValidationResult(tokenResult.error));
      }
    }

    // 토큰 받아옴
    articleStoreDispathcer(articleAction.setJsonWebToken(tokenResult.token));
  };

  console.log("asdasdasdasdasdasdasdasdasd");
  console.log(loginErrors);

  return (
    <>
      {isString(loginErrors) && <div>{loginErrors}</div>}

      <label>ID</label>
      <input type="text" ref={emailRef} placeholder="아이디를 입력하세요" />
      {loginErrors?.email && <div>{loginErrors.email}</div>}
      <label>PWD</label>
      <input type="password" ref={pwdRef} placeholder="비밀번호를 입력하세요" />
      {loginErrors?.password && <div>{loginErrors.password}</div>}
      <button type="submit" onClick={onLoginDataHandler}>
        로그인
      </button>
    </>
  );
};

export default ArticleLogin;
