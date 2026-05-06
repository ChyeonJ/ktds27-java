/** @format */

import { useEffect, useRef } from "react";
import { isString } from "../../utils/type";
import { fetchMyInfo } from "../../http/articles/fetchLogin";
import { useDispatch, useSelector } from "react-redux";
import { userAction, userThunks } from "../../stores/toolkit/slices/userSlice";

const Login = () => {
  const emailRef = useRef();
  const passwordRef = useRef();

  const {
    token,
    info,
    error: loginErrors,
  } = useSelector((store) => store.user);
  const toolkitProvider = useDispatch();

  useEffect(() => {
    toolkitProvider(userAction.autoLogin());
    const loadMyInfo = async () => {
      // /api/member/me 호출.
      // token이 있을 때만 수행
      // 타이밍 이슈 때문에 바로 가져오는 코드 추가
      const sessionToken = sessionStorage.getItem("token");
      const myInfo = await fetchMyInfo(sessionToken);
      if (myInfo.error) {
        // token이 변조 되었거나, 만료 기간이 도래한 경우
        sessionStorage.removeItem("token");
        //sliceStore에서도 제거.
        toolkitProvider(userAction.logout());
      } else {
        toolkitProvider(userAction.loadMyInfo(myInfo));
      }
    };
    loadMyInfo();
  }, [token]);

  if (token) {
    const onLogoutButtonClickHandler = () => {
      sessionStorage.removeItem("token");
      toolkitProvider(userAction.logout());
    };
    return (
      <div>
        {info?.name}({info?.email})
        <button onClick={onLogoutButtonClickHandler}>Logout</button>
      </div>
    );
  }

  const onLoginButtonClickHandler = () => {
    toolkitProvider(
      userThunks.login(emailRef.current.value, passwordRef.current.value),
    );
  };

  return (
    <div>
      {isString(loginErrors) && <div>{loginErrors}</div>}

      <div>
        <label htmlFor="email">EMAIL</label>
        <input type="email" id="email" ref={emailRef} />
        {loginErrors?.email && <div>{loginErrors.email}</div>}
      </div>
      <div>
        <label htmlFor="password">PWD</label>
        <input type="password" id="password" ref={passwordRef} />
        {loginErrors?.password && <div>{loginErrors.password}</div>}
      </div>
      <button type="button" onClick={onLoginButtonClickHandler}>
        로그인
      </button>
    </div>
  );
};
export default Login;
