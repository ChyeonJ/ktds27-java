import { useState } from "react";

const Calc = () => {
  //   const [firstNum, setFirstNum] = useState(0);
  //   const [SecondNum, setSecondNum] = useState(0);
  //   const [resultNum, setResultNum] = useState(0);

  const [{ firstNum, SecondNum, resultNum }, setNums] = useState({
    firstNum: 10,
    SecondNum: 20,
    resultNum: 30,
  });

  const onFirstKey = (event) => {
    setNums((prevNums) => {
      const newNums = { ...prevNums, firstNum: parseInt(event.target.value) };
      return newNums;
    });
  };
  const onSecondKey = (event) => {
    console.log(event);
    setNums((prevNums) => {
      console.log(prevNums);
      const newNums = { ...prevNums, SecondNum: parseInt(event.target.value) };
      return newNums;
    });
  };

  const onCalcButtonClickHandler = (operator, event) => {
    console.log(event, operator);
    let resultNum = 0;

    if (operator === "+") {
      resultNum = firstNum + SecondNum;
    } else if (operator === "*") {
      resultNum = firstNum + SecondNum;
    } else if (operator === "/") {
      resultNum = firstNum + SecondNum;
    } else if (operator === "-") {
      resultNum = firstNum + SecondNum;
    }

    setNums((prevNums) => {
      const newNums = { ...prevNums, resultNum: resultNum };
      return newNums;
    });
  };

  return (
    <div>
      <input type="number" onChange={onFirstKey} />
      {/* function.bind(this, "value")
          bind가 하는 역할 Function Call Delegater(위임자) => Function을 호출하는 위임자*/}
      <input
        type="button"
        onClick={onCalcButtonClickHandler.bind(this, "+")}
        value="+"
      ></input>
      <input
        type="button"
        onClick={onCalcButtonClickHandler.bind(this, "-")}
        value="-"
      ></input>
      <input
        type="button"
        onClick={onCalcButtonClickHandler.bind(this, "*")}
        value="*"
      ></input>
      <input
        type="button"
        onClick={onCalcButtonClickHandler.bind(this, "/")}
        value="/"
      ></input>
      <input type="number" onChange={onSecondKey} />
      <div>{resultNum}</div>
    </div>
  );
};

export default Calc;
