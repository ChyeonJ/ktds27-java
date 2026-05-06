/** @format */

import { useContext } from "react";
import TrendContext from "./contexts/TrendContext";

const TrendHeader = ({ children }) => {
  const { componentName } = useContext(TrendContext);
  if (componentName !== "TrendBox") {
    return <></>;
  }

  const providerProps = {
    componentName: "TrendHeader",
  };

  return (
    <TrendContext.Provider value={providerProps}>
      <div className="trend-header">{children}</div>
    </TrendContext.Provider>
  );
};
export default TrendHeader;
