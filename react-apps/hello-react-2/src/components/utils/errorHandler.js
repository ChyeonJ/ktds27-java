import { isArray, isObject } from "./type";

const getValidationResult = (error) => {
  //배열이냐?
  if (isArray(error)) {
    const message = {};

    //맞으면 반복
    for (let eachError of error) {
      //객체냐?
      if (isObject(eachError)) {
        //객체안에 이 Key가 있니?
        if (eachError.filed && eachError.defaultMessage) {
          //{email : "email을 입력해주세여.", password : "비밀번호를 입력해주세요"}
          message[eachError.filed] = eachError.defaultMessage;
        } else {
          // 위의 값이 없다면? undefind 반환
          return undefined;
        }
      } else {
        // 객체가 아니면 undefind 반환 => 객체가 반환 되면 안된다
        return undefined;
      }
    }
    return message;
  }
};

export default getValidationResult;
