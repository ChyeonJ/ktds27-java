import { useRef } from "react";

const ArticleLogin = ({ onLoginData }) => {
  const emailRef = useRef();
  const pwdRef = useRef();

  const onLoginPageButtonClick = () => {
    onLoginData(emailRef.current.value, pwdRef.current.value);
  };

  return (
    <>
      <label>ID</label>
      <input type="text" ref={emailRef} placeholder="아이디를 입력하세요" />
      <label>PWD</label>
      <input type="password" ref={pwdRef} placeholder="비밀번호를 입력하세요" />
      <button type="submit" onClick={onLoginPageButtonClick}>
        로그인
      </button>
    </>
  );
};

export default ArticleLogin;
