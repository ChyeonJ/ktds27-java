/** @format */

import { useState } from "react";

const Counter = () => {
  const [count, setCount] = useState(0);

  const onButtonClickHandler = (event) => {
    const className = event.target.classList.value;

    setCount((prevCount) => {
      if (className.includes("decrease")) {
        if (prevCount === 0) {
          return prevCount;
        }
        return prevCount - 1;
      } else if (className.includes("increase")) {
        if (prevCount === 100) {
          return prevCount;
        }
        return prevCount + 1;
      }

      return prevCount;
    });
  };

  return (
    <div>
      <button
        type="button"
        className="decrease negative"
        onClick={onButtonClickHandler}
      >
        -
      </button>
      <div>{count}</div>
      <button type="button" className="increase" onClick={onButtonClickHandler}>
        +
      </button>
    </div>
  );
};
export default Counter;
