/** @format */

import TrendContext from "./contexts/TrendContext";
import TrendHeader from "./TrendHeader";
import TrendItem from "./TrendItem";
import TrendList from "./TrendList";
import TrendSelector from "./TrendSelector";

const TrendBox = ({ children }) => {
  const providerProps = {
    componentName: "TrendBox",
  };
  return (
    <TrendContext.Provider value={providerProps}>
      <div className="trend-box">{children}</div>
    </TrendContext.Provider>
  );
};
export default TrendBox;
