/** @format */

import { useContext } from "react";
import TrendContext from "./contexts/TrendContext";

const TrendItem = ({ item }) => {
  const { componentName } = useContext(TrendContext);
  if (componentName !== "TrendList") {
    return <></>;
  }
  return (
    <div className="trend-item">
      <img src={item.poster} />
      <div>{item.name}</div>
      <div>{item.openDate}</div>
    </div>
  );
};
export default TrendItem;
