import { useState } from "react";

const CounterBox = () => {
  const [number, setNumber] = useState(0);

  const plusButtonClickHandler = () => {
    // ???? + 1;
    if (number >= 100) {
      return number;
    }
    setNumber(number + 1);
  };

  const minusButtonClickHandler = () => {
    // ??? - 1;
    if (number == 0) {
      return 0;
    }
    setNumber(number - 1);
  };

  return (
    <div className="countbox">
      <div className="minus-button">
        <button id="minus" onClick={minusButtonClickHandler}>
          -
        </button>
      </div>
      <div className="numnber-box">{number}</div>
      <div className="plus-button">
        <button id="plus" onClick={plusButtonClickHandler}>
          +
        </button>
      </div>
    </div>
  );
};

export default CounterBox;
