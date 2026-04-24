import { useState } from "react";

const CounterBox = () => {
  const [number, setNumber] = useState(0);

  const onButtonClickHandler = (event) => {
    const className = event.target.classList.value;

    setNumber((prevCount) => {
      if (className.includes("minus")) {
        if (prevCount === 0) {
          return 0;
        }
        return prevCount - 1;
      } else if (className.includes("plus")) {
        if (prevCount === 100) {
          return 100;
        }
        return prevCount + 1;
      }
      return prevCount;
    });
  };

  return (
    <div className="countbox">
      <div className="minus-button">
        <button id="minus" className="minus" onClick={onButtonClickHandler}>
          -
        </button>
      </div>
      <div className="numnber-box">{number}</div>
      <div className="plus-button">
        <button id="plus" className="plus" onClick={onButtonClickHandler}>
          +
        </button>
      </div>
    </div>
  );
};

export default CounterBox;
