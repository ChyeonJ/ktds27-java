/** @format */

import { useContext } from "react";
import TrendContext from "./contexts/TrendContext";

const TrendSelector = ({
  selectors,
  translatedSelectors,
  active,
  onSelectChange,
}) => {
  const { componentName } = useContext(TrendContext);
  if (componentName !== "TrendHeader") {
    return <></>;
  }

  return (
    <div className="trend-selectors">
      {selectors.map((select, index) => (
        <div key={select} className={active === select ? "active" : ""}>
          <label htmlFor={select}>{translatedSelectors[index]}</label>
          <input
            id={select}
            type="radio"
            name="selectors"
            checked={active === select}
            value={select}
            onChange={onSelectChange}
          />
        </div>
      ))}
    </div>
  );
};
export default TrendSelector;
