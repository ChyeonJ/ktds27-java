import { useRef } from "react";
import { isString } from "../utils/type";

const ArticleLogin = ({ onLoginData, loginError }) => {
  const emailRef = useRef();
  const pwdRef = useRef();

  const onLoginPageButtonClick = () => {
    onLoginData(emailRef.current.value, pwdRef.current.value);
  };

  console.log("asdasdasdasdasdasdasdasdasd");
  console.log(loginError);

  return (
    <>
      {isString(loginError) && <div>{loginError}</div>}

      <label>ID</label>
      <input type="text" ref={emailRef} placeholder="아이디를 입력하세요" />
      {loginError?.email && <div>{loginError.email}</div>}
      <label>PWD</label>
      <input type="password" ref={pwdRef} placeholder="비밀번호를 입력하세요" />
      {loginError?.password && <div>{loginError.password}</div>}
      <button type="submit" onClick={onLoginPageButtonClick}>
        로그인
      </button>
    </>
  );
};

export default ArticleLogin;
