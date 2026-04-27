/** @format */

import { useState } from "react";

export const StateTest = () => {
  console.log("StateTest component(함수) 실행 됨.");

  // 변경 가능한 상수를 생성 (state)
  const [value, setValue] = useState("Initiate Value");

  const onTextKeyUpHandler = (event) => {
    console.log(event.target.value);
    setValue(event.target.value);
  };

  return <StateTestItem text={value} onTextKeyUp={onTextKeyUpHandler} />;
};

const StateTestItem = ({ text, onTextKeyUp }) => {
  console.log("StateTestItem component(함수) 실행 됨.");

  return (
    <div>
      {text}
      <div>
        <input type="text" onKeyUp={onTextKeyUp} />
      </div>
    </div>
  );
};
